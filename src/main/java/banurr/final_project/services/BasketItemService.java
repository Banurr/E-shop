package banurr.final_project.services;

import banurr.final_project.models.BasketItem;
import banurr.final_project.repositories.BasketItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketItemService
{
    @Autowired
    private BasketItemRepository basketItemRepository;

    public BasketItem findBasketItem(Long id)
    {
        return basketItemRepository.findById(id).orElse(null);
    }

    public void addBasketItem(BasketItem basketItem)
    {
        basketItemRepository.save(basketItem);
    }

    public void plusBasketItemQuantity(Long id)
    {
        basketItemRepository.plusBasketItemQuantity(id);
    }

    public void minusBasketItemQuantity(Long id)
    {
        basketItemRepository.minusBasketItemQuantity(id);
    }

    public void updateBasketItem(BasketItem basketItem)
    {
        basketItemRepository.save(basketItem);
    }

    public void deleteBasketItem(BasketItem basketItem)
    {
        basketItemRepository.delete(basketItem);
    }
}
