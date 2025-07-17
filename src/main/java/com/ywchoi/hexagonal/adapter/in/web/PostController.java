package com.ywchoi.hexagonal.adapter.in.web;

import com.ywchoi.hexagonal.adapter.in.web.dto.PostRequest;
import com.ywchoi.hexagonal.adapter.in.web.dto.PostUpdateRequest;
import com.ywchoi.hexagonal.domain.model.Post;
import com.ywchoi.hexagonal.application.port.in.PostUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostUseCase postUseCase;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostRequest request) {
        Post post = postUseCase.createPost(
                request.getTitle(),
                request.getContent(),
                request.getAuthor()
        );
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postUseCase.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return postUseCase.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<Post> updatePost(
            @PathVariable Long id,
            @RequestBody PostUpdateRequest request) {
        Post updatedPost = postUseCase.updatePost(id, request.getTitle(), request.getContent());
        return ResponseEntity.ok(updatedPost);
    }

    @PostMapping("/{id}/delete")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postUseCase.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}