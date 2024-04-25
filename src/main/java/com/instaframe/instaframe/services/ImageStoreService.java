package com.instaframe.instaframe.services;

import com.instaframe.instaframe.dtos.general.FileDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImageStoreService {
    @Value("${upload.dir}")
    private String uploadDir;

    public String storeImage(MultipartFile file) {
        try {
            return saveImage(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String saveImage(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        return fileName;
    }

    public Optional<FileDTO> getFile(String fileName) {
        if (fileName == null) return Optional.empty();

        Optional<FileDTO> fileDTO = Optional.empty();

        try {
            Path uploadPath = Paths.get(uploadDir);
            Path filePath = uploadPath.resolve(fileName);

            File file = filePath.toFile();

            String mimeType = Files.probeContentType(file.toPath());


            return Optional.of(new FileDTO(
                    file.getName(),
                    file.length(),
                    mimeType,
                    "images/" + fileName
            ));

        } catch (IOException e) {
            return fileDTO;
        }
    }
}
