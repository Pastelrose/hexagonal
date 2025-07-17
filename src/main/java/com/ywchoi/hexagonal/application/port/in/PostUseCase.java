package com.ywchoi.hexagonal.application.port.in;

import com.ywchoi.hexagonal.domain.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostUseCase {
    Post createPost(String title, String content, String author);
    List<Post> getAllPosts();
    Optional<Post> getPostById(Long id);
    Post updatePost(Long id, String title, String content);
    void deletePost(Long id);
}