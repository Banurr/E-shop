package banurr.final_project.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="baskets")
@Builder
public class Basket
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "basket",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<BasketItem> items;
}
