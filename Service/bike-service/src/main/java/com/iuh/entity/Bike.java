package com.iuh.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Bike {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long bikeId;
    private String bikeName;
    private long price;
}
