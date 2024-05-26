package org.customer.entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Customer {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long customerId;
    private String customerName;
    private String customerAddress;

}
