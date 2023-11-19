package banurr.final_project.repositories;

import banurr.final_project.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>
{
    @Override
    @Query("SELECT c FROM Category c ORDER BY c.id")
    List<Category> findAll();
}
