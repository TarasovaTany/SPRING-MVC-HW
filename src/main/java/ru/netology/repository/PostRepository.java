package ru.netology.repository;
import ru.netology.model.Post;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
    private final AtomicLong countId = new AtomicLong(1);
    private final ConcurrentMap<Long, Post> allPosts;

    public PostRepository() {
        this.allPosts = new ConcurrentHashMap<>();
    }
    public List<Post> all() {
        if (allPosts.isEmpty()) {
            return Collections.emptyList();
        }
        return new ArrayList<>(allPosts.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(allPosts.get(id));
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            long id = countId.getAndIncrement();
            post.setId(id);
            allPosts.put(id, post);
        }
        allPosts.put(post.getId(), post);
        return post;
    }

    public void removeById(long id) {
        allPosts.remove(id);
    }
}
