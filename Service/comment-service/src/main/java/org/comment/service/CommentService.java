package org.comment.service;

import org.comment.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    void addComment(Comment comment);
    List<Comment> getAllComment();
    Optional<Comment> getCommentById(long commentId);
    Comment updateComment(long commentId, Comment commentUpdate);
    void deleteComment(long commentId);

//    List<Comment> getAllByBikeId(long bikeId);
}
