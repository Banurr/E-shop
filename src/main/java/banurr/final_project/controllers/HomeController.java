package banurr.final_project.controllers;

import banurr.final_project.models.Product;
import banurr.final_project.models.User;
import banurr.final_project.services.CategoryService;
import banurr.final_project.services.FeatureService;
import banurr.final_project.services.ProductService;
import banurr.final_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController
{
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private FeatureService featureService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public String home()
    {
        return "home";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/sign-in")
    public String signInPage()
    {
        return "sign-in";
    }

    @PreAuthorize("isAnonymous()")
    @GetMapping("/register")
    public String registerPage()
    {
        return "register";
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/register")
    public String register(User user, @RequestParam(name = "rePassword") String rePassword)
    {
        System.out.println(user);
        String result = userService.addUser(user, rePassword);
        return "redirect:/"+result;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String profilePage()
    {
        return "profile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/updatePassword")
    public String updatePassword(@RequestParam String currentPassword,
                                 @RequestParam String newPassword,
                                 @RequestParam String renewPassword)
    {

        String result = userService.updatePassword(currentPassword, newPassword, renewPassword);
        return "redirect:/"+result;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin")
    public String adminPanel()
    {
        return "admin_panel";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/category")
    public String adminCategory(Model model)
    {
        model.addAttribute("categories", categoryService.allCategories());
        return "admin_category";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/product")
    public String adminProduct(Model model)
    {
        model.addAttribute("products", productService.allProducts());
        model.addAttribute("categories", categoryService.allCategories());
        model.addAttribute("features", featureService.allFeatures());
        return "admin_product";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/feature")
    public String adminFeature(Model model)
    {
        model.addAttribute("features", featureService.allFeatures());
        return "admin_feature";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/users")
    public String adminUsers()
    {
        return "admin_user";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/comments")
    public String adminComments()
    {
        return "admin_comment";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/removeFeature/{id}")
    public String removeFeature(@PathVariable(name = "id") Long id,
                                @RequestParam(name = "feature") Long feature)
    {
        Product product = productService.findProduct(id);
        product.getFeatures().remove(featureService.findFeature(feature));
        productService.updateProduct(product);
        return "redirect:/admin/product";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addFeature/{id}")
    public String addFeature(@PathVariable(name = "id") Long id,
                             @RequestParam(name = "feature") Long feature)
    {
        Product product = productService.findProduct(id);
        product.getFeatures().add(featureService.findFeature(feature));
        productService.updateProduct(product);
        return "redirect:/admin/product";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/categories")
    public String allCategories(Model model)
    {
        model.addAttribute("categories", categoryService.allCategories());
        return "client_category";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/category/{id}")
    public String categoryDetails(@PathVariable(name = "id") Long id, Model model)
    {
        model.addAttribute("category", categoryService.findCategory(id));
        return "client_category_details";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/product/{id}")
    public String productDetails(@PathVariable(name = "id") Long id,
                                 Model model)
    {
        model.addAttribute("product", productService.findProduct(id));
        return "client_product_details";
    }
}

