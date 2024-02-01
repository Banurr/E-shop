package banurr.final_project.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
@Builder
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    private LocalDateTime dateTime;

    private OrderStatus orderStatus;

    public double orderTotal()
    {
        double total = 0;
        if(orderItems == null) return total;
        for(OrderItem orderItem : orderItems)
        {
            total += orderItem.getQuantity()*orderItem.getProduct().getPrice();
        }
        return total;
    }
}
