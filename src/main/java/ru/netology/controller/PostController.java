package ru.netology.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping
    public List<Post> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public Post getById(@PathVariable long id) throws NotFoundException {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<Post> save(@RequestBody Post post) {
    Post savedPost = service.save(post);
    return ResponseEntity.ok(savedPost);
}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeById(@PathVariable long id) {
        service.removeById(id);
        return ResponseEntity.noContent().build();
    }
}
