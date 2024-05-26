package org.example.deliver.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "deliver")
@AllArgsConstructor
@NoArgsConstructor
public class Deliver {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long deliverId;

}
