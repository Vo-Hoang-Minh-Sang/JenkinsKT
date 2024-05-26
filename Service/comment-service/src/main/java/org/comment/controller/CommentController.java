package org.comment.controller;

import lombok.RequiredArgsConstructor;
import org.comment.config.RestTemplateConfig;
import org.comment.entity.Comment;
import org.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    @Autowired
    RestTemplateConfig restTemplate;
    @Autowired
    CommentService commentService;

    @GetMapping
    public List<Comment> getAllComment(){
        return commentService.getAllComment();
    }

    @PostMapping("/add")
    public String createComment(@RequestBody Comment comment){
        commentService.addComment(comment);
        return "them comment thanh cong";
    }

    @GetMapping("/{commentId}")
    @Cacheable(value= "comment", key="#commentId")
    public ResponseEntity<Comment> getCommentById(@PathVariable long commentId){
        Optional<Comment> comment = commentService.getCommentById(commentId);
        return comment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{commentId}")
    @CachePut(value = "comment",key = "#commentId")
    public ResponseEntity<Comment> updateComment(@PathVariable long commentId, @RequestBody Comment commentUpdate) {
        try {
            Comment updateComment = commentService.updateComment(commentId, commentUpdate);
            return ResponseEntity.ok(updateComment);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{commentId}")
    @CacheEvict(value = "comment", allEntries = true)
    public ResponseEntity<Void> deleteComment(@PathVariable long commentId) {
        try {
            commentService.deleteComment(commentId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/customer")
    public ResponseEntity<Object> getCommentAndCustomer() {
        Optional<Comment> commentOptional = commentService.getCommentById(1L);

        if (commentOptional.isPresent()) {
            String customerUrl = "http://localhost:8081/customer";
            Object customer = restTemplate.getForObject(customerUrl, Object.class);

            Map<String, Object> result = new HashMap<>();
            result.put("comment", commentOptional.get());
            result.put("customer", customer);

            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/bike")
    public ResponseEntity<Object> getCommentAndBike() {
        Optional<Comment> commentOptional = commentService.getCommentById(1L);

        if (commentOptional.isPresent()) {
            String bikeUrl = "http://localhost:8083/bike";
            Object bike = restTemplate.getForObject(bikeUrl, Object.class);

            Map<String, Object> result = new HashMap<>();
            result.put("comment", commentOptional.get());
            result.put("bike", bike);

            return ResponseEntity.ok().body(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping("/bike/{bikeId}")
//    public List<Comment> getAllCommentByBikeId(@PathVariable long bikeId) {
//        return commentService.getAllByBikeId(bikeId);
//    }
}
