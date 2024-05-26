package org.comment.service;

import org.comment.entity.Comment;
import org.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public void addComment(Comment comment) {
    commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllComment() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> getCommentById(long commentId){
        return commentRepository.findByCommentId(commentId);
    }

    @Override
    public Comment updateComment(long commentId, Comment commentUpdate){
        Comment comment = commentRepository.findByCommentId(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));
        comment.setDescripstion(commentUpdate.getDescripstion());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(long commentId){
        Comment comment = commentRepository.findByCommentId(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + commentId));
        commentRepository.delete(comment);
    }



//    @Override
//    public List<Comment> getAllByBikeId(long bikeId) {
//        return commentRepository.findAllByBikeId(bikeId);
//    }
}
