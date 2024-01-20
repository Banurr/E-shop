package banurr.final_project.controllers;
import banurr.final_project.models.User;
import banurr.final_project.services.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> allUsers()
    {
        return userService.allUsers();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(@RequestBody Long id)
    {
        userService.deleteUser(id);
    }

    @PostMapping("/addToBasketFirst/{id}")
    @PreAuthorize("isAuthenticated()")
    public RedirectView addToBasketFirst(@PathVariable Long id)
    {
        String result = userService.addToBasketFirst(id);
        return new RedirectView("/product/"+id+"?"+result);
    }

    @Transactional
    @GetMapping("/addToBasket/{id}")
    @PreAuthorize("isAuthenticated()")
    public RedirectView addToBasket(@PathVariable int id)
    {
        userService.addToBasket(id);
        return new RedirectView("/basket");
    }

    @Transactional
    @GetMapping("/removeFromBasket/{id}")
    @PreAuthorize("isAuthenticated()")
    public RedirectView removeFromBasket(@PathVariable int id)
    {
        userService.removeFromBasket(id);
        return new RedirectView("/basket");
    }

    @Transactional
    @GetMapping("/deleteFromBasket/{id}")
    @PreAuthorize("isAuthenticated()")
    public RedirectView deleteFromBasket(@PathVariable int id)
    {
        userService.deleteFromBasket(id);
        return new RedirectView("/basket");
    }
}
