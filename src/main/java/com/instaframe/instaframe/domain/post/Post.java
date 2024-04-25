package com.instaframe.instaframe.domain.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.instaframe.instaframe.dtos.post.CreatePostDTO;
import com.instaframe.instaframe.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "posts")
@Entity(name = "posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private Boolean active;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "created_by_id")
    private User createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }

    public Post(CreatePostDTO request) {
        this.active = true;
        this.title = request.title();
        this.description = request.description();
    }

}
