package banurr.final_project.services;

import banurr.final_project.models.Basket;
import banurr.final_project.repositories.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketService
{
    @Autowired
    private BasketRepository basketRepository;

    public void addBasket(Basket basket)
    {
        basketRepository.save(basket);
    }
}
