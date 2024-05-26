package org.order.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "orderdb")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private long orderId;
    private String orderName;

}
