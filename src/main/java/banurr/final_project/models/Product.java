package banurr.final_project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products")
@Builder
public class Product
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private Long price;

    private int quantity;

    private String picture;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @ManyToMany(cascade = CascadeType.REMOVE)
    private Set<Feature> features;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    public String getRating()
    {
        double rating = 0.0;
        if(comments.size()==0) return String.valueOf(rating);
        for(Comment c : comments) rating += c.getRate();
        return String.valueOf(rating / comments.size());

    }
}
