package banurr.final_project.services;

import banurr.final_project.models.BasketItem;
import banurr.final_project.models.Product;
import banurr.final_project.models.Role;
import banurr.final_project.models.User;
import banurr.final_project.repositories.BasketItemRepository;
import banurr.final_project.repositories.RoleRepository;
import banurr.final_project.repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserDetailsService
{

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ProductService productService;

    @Autowired
    private BasketItemService basketItemService;

    public List<User> allUsers()
    {
        return userRepository.findAll();
    }

    public void deleteUser(Long id)
    {
        userRepository.deleteById(id);
    }

    public void updateUser(User user)
    {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmail(email);
        if(user == null) throw new UsernameNotFoundException("User not found!");
        return user;
    }

    public String addUser(User user, String rePassword)
    {
        if(userRepository.findByEmail(user.getEmail()) != null) return "register?exist";
        if(!user.getPassword().equals(rePassword)) return "register?typo";
        user.setPassword(passwordEncoder.encode(rePassword));
        Role role = roleRepository.findRoleUser();
        List<BasketItem> basket = new ArrayList<>();
        user.setBasket(basket);
        user.setRoles(List.of(role));
        userRepository.save(user);
        return "sign-in?success";
    }

    public User getCurrentUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication instanceof AnonymousAuthenticationToken) return null;
        return (User) authentication.getPrincipal();
    }

    public String updatePassword(String currentPassword, String newPassword, String renewPassword)
    {
        User user = getCurrentUser();
        if(passwordEncoder.matches(user.getPassword(), newPassword)) return "profile?same";
        if(!passwordEncoder.matches(currentPassword, user.getPassword())) return "profile?notright";
        if(!newPassword.equals(renewPassword)) return "profile?notmatch";
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return "profile?success";
    }

    public void updateDetails(String name, String surname)
    {
        User user = getCurrentUser();
        user.setName(name);
        user.setSurname(surname);
        userRepository.save(user);
    }

    public void setImage(MultipartFile multipartFile)
    {
        User user = getCurrentUser();
        user.setPicture(multipartFile.getOriginalFilename());
        userRepository.save(user);
    }

    public void deleteAccount()
    {
        User user = getCurrentUser();
        userRepository.delete(user);
    }

    public void resetImage()
    {
        User user = getCurrentUser();
        user.setPicture("anonymous.jpeg");
        userRepository.save(user);
    }

    public String addToBasketFirst(Long id)
    {
        Product product = productService.findProduct(id);
        if(product.getQuantity() <= 0 ) return "error";
        User user = getCurrentUser();
        BasketItem basketItem = BasketItem
                .builder()
                .user(user)
                .quantity(1)
                .product(product)
                .build();
        basketItemService.addBasketItem(basketItem);
        List<BasketItem> basketItems = user.getBasket();
        basketItems.add(basketItem);
        user.setBasket(basketItems);
        updateUser(user);
        return "success";
    }


    public void addToBasket(Long id)
    {
        BasketItem basketItem = basketItemService.findBasketItem(id);
        if(basketItem.getProduct().getQuantity() > 0)
        {
            basketItemService.plusBasketItemQuantity(id);
        }
    }


    public void removeFromBasket(Long id)
    {
        BasketItem basketItem = basketItemService.findBasketItem(id);
        if(basketItem.getQuantity() == 1)
        {
            basketItemService.deleteBasketItem(basketItem);
        }
        else if(basketItem.getQuantity()>1)
        {
            basketItemService.minusBasketItemQuantity(id);
        }
    }


    public void deleteFromBasket(Long id)
    {
        BasketItem basketItem = basketItemService.findBasketItem(id);
        basketItemService.deleteBasketItem(basketItem);
    }
}
