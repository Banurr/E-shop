package banurr.final_project.repositories;

import banurr.final_project.models.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketItemRepository extends JpaRepository<BasketItem, Long>
{

    @Modifying
    @Query("UPDATE BasketItem b SET b.quantity = b.quantity + 1 WHERE b.id = :id")
    void plusBasketItemQuantity(Long id);

    @Modifying
    @Query("UPDATE BasketItem b SET b.quantity = b.quantity - 1 WHERE b.id = :id")
    void minusBasketItemQuantity(Long id);
}
