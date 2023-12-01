package banurr.final_project.controllers;

import banurr.final_project.models.User;
import banurr.final_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public List<User> allUsers()
    {
        return userService.allUsers();
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody Long id)
    {
        userService.deleteUser(id);
    }
}
