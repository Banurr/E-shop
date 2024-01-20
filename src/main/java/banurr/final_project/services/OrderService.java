package banurr.final_project.services;

import banurr.final_project.models.Order;
import banurr.final_project.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService
{
    @Autowired
    private OrderRepository orderRepository;

    public void createOrder(Order order)
    {
        orderRepository.save(order);
    }
}
