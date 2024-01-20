package banurr.final_project.services;

import banurr.final_project.models.*;
import banurr.final_project.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class OrderService
{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemService orderItemService;


    public void createOrder(ArrayList<BasketItem> basket)
    {
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        for(BasketItem basketItem : basket)
        {
            OrderItem orderItem = OrderItem.builder()
                    .product(basketItem.getProduct())
                    .quantity(basketItem.getQuantity())
                    .build();
            orderItems.add(orderItem);
            Product product = basketItem.getProduct();
            product.setQuantity(product.getQuantity()-basketItem.getQuantity());
            productService.updateProduct(product);
            orderItemService.addOrderItem(orderItem);
        }
        Order order = Order.builder()
                .orderItems(orderItems)
                .orderStatus(OrderStatus.CREATED)
                .dateTime(LocalDateTime.now())
                .build();
        addOrder(order);
    }

    public void addOrder(Order order)
    {
        orderRepository.save(order);
    }
}
