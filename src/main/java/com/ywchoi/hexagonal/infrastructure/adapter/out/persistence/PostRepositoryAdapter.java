package com.ywchoi.hexagonal.infrastructure.adapter.out.persistence;

import com.ywchoi.hexagonal.domain.Post;
import com.ywchoi.hexagonal.application.port.out.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostRepositoryAdapter implements PostRepository {

    private final PostJpaRepository postJpaRepository;

    @Override
    public Post save(Post post) {
        PostJpaEntity entity = mapToEntity(post);
        PostJpaEntity savedEntity = postJpaRepository.save(entity);
        return mapToDomain(savedEntity);
    }

    @Override
    public List<Post> findAll() {
        return postJpaRepository.findAll().stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postJpaRepository.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public void deleteById(Long id) {
        postJpaRepository.deleteById(id);
    }

    private PostJpaEntity mapToEntity(Post post) {
        return PostJpaEntity.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    private Post mapToDomain(PostJpaEntity entity) {
        return Post.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .author(entity.getAuthor())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}