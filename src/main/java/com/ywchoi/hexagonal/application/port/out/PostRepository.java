package com.ywchoi.hexagonal.application.port.out;

import com.ywchoi.hexagonal.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(Post post);
    List<Post> findAll();
    Optional<Post> findById(Long id);
    void deleteById(Long id);
}