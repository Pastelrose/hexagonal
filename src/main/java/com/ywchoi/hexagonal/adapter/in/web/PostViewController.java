package com.ywchoi.hexagonal.adapter.in.web;

import com.ywchoi.hexagonal.adapter.in.web.dto.PostRequest;
import com.ywchoi.hexagonal.adapter.in.web.dto.PostUpdateRequest;
import com.ywchoi.hexagonal.domain.model.Post;
import com.ywchoi.hexagonal.application.port.in.PostUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostViewController {

    private final PostUseCase postUseCase;

    @GetMapping
    public String listPosts(Model model) {
        model.addAttribute("posts", postUseCase.getAllPosts());
        return "posts/list";
    }

    @GetMapping("/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postUseCase.getPostById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));
        model.addAttribute("post", post);
        return "posts/view";
    }

    @GetMapping("/new")
    public String newPostForm(Model model) {
        model.addAttribute("postRequest", new PostRequest());
        return "posts/form";
    }

    @PostMapping
    public String createPost(@ModelAttribute PostRequest postRequest) {
        Post post = postUseCase.createPost(
                postRequest.getTitle(),
                postRequest.getContent(),
                postRequest.getAuthor()
        );
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/{id}/edit")
    public String editPostForm(@PathVariable Long id, Model model) {
        Post post = postUseCase.getPostById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found with id: " + id));
        
        PostUpdateRequest updateRequest = new PostUpdateRequest();
        updateRequest.setTitle(post.getTitle());
        updateRequest.setContent(post.getContent());
        
        model.addAttribute("post", post);
        model.addAttribute("updateRequest", updateRequest);
        return "posts/edit";
    }

    @PostMapping("/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute PostUpdateRequest updateRequest) {
        postUseCase.updatePost(id, updateRequest.getTitle(), updateRequest.getContent());
        return "redirect:/posts/" + id;
    }

    @PostMapping("/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postUseCase.deletePost(id);
        return "redirect:/posts";
    }
}