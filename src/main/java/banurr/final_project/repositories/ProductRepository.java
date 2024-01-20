package banurr.final_project.repositories;

import banurr.final_project.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
    @Override
    @Query("SELECT p FROM Product p ORDER BY p.id")
    List<Product> findAll();
}
