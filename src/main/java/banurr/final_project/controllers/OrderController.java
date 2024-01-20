package banurr.final_project.controllers;
import banurr.final_project.models.BasketItem;
import banurr.final_project.models.Order;
import banurr.final_project.models.OrderItem;
import banurr.final_project.models.OrderStatus;
import banurr.final_project.services.OrderItemService;
import banurr.final_project.services.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
@RequestMapping("/orders")
public class OrderController
{
    @Autowired
    private HttpSession httpSession;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public RedirectView createOrder()
    {
        ArrayList<BasketItem> basket = (ArrayList<BasketItem>) httpSession.getAttribute("basket");
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        for(BasketItem basketItem : basket)
        {
            OrderItem orderItem = OrderItem.builder()
                    .product(basketItem.getProduct())
                    .quantity(basketItem.getQuantity())
                    .build();
            orderItems.add(orderItem);
            orderItemService.addOrderItem(orderItem);
        }
        Order order = Order.builder()
                .orderItems(orderItems)
                .orderStatus(OrderStatus.COMPLETED)
                .dateTime(LocalDateTime.now())
                .build();
        orderService.createOrder(order);
        httpSession.setAttribute("basket",new ArrayList<BasketItem>());
        return new RedirectView("/");
    }
}
