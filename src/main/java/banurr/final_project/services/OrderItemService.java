package banurr.final_project.services;

import banurr.final_project.models.OrderItem;
import banurr.final_project.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService
{
    @Autowired
    private OrderItemRepository orderItemRepository;

    public void addOrderItem(OrderItem orderItem)
    {
        orderItemRepository.save(orderItem);
    }
}
