package banurr.final_project.repositories;

import banurr.final_project.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category, Long>
{
}
