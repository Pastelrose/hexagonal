package com.ywchoi.hexagonal.application.service;

import com.ywchoi.hexagonal.domain.model.Post;
import com.ywchoi.hexagonal.port.in.PostUseCase;
import com.ywchoi.hexagonal.port.out.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService implements PostUseCase {

    private final PostRepository postRepository;

    @Override
    public Post createPost(String title, String content, String author) {
        Post post = Post.builder()
                .title(title)
                .content(content)
                .author(author)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        
        return postRepository.save(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post updatePost(Long id, String title, String content) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));
        
        Post updatedPost = post.updateContent(title, content);
        return postRepository.save(updatedPost);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}