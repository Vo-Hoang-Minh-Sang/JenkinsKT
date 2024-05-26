package org.comment.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "comment")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long commentId;
    private String descripstion;


}
