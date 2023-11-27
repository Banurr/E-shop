package banurr.final_project.repositories;

import banurr.final_project.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
//    @Modifying
//    @Transactional
//    @Query("delete from Product p where p.category.id=:id")
//    void deleteProductbyCategoryId(Long id);

//    @Query("select p from Product p where p.category.id=:id")
//    List<Product> ProductwithCategoryId(Long id);
}
