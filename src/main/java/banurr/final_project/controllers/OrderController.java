package banurr.final_project.controllers;
import banurr.final_project.models.*;
import banurr.final_project.services.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import java.util.ArrayList;

@RestController
@RequestMapping("/orders")
public class OrderController
{
    @Autowired
    private HttpSession httpSession;

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public RedirectView createOrder()
    {
        ArrayList<BasketItem> basket = (ArrayList<BasketItem>) httpSession.getAttribute("basket");
        orderService.createOrder(basket);
        httpSession.setAttribute("basket",new ArrayList<BasketItem>());
        return new RedirectView("/basket");
    }
}
