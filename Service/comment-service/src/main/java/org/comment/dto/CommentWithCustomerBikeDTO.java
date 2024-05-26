package org.comment.dto;

import org.comment.entity.Comment;

public class CommentWithCustomerBikeDTO {
    private Comment comment;
    private ExternalCustomerDTO customer;
    private ExternalBikeDTO bike;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public ExternalCustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(ExternalCustomerDTO customer) {
        this.customer = customer;
    }

    public ExternalBikeDTO getBike() {
        return bike;
    }

    public void setBike(ExternalBikeDTO bike) {
        this.bike = bike;
    }
}
