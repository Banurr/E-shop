package banurr.final_project.controllers;

import banurr.final_project.services.CategoryService;
import banurr.final_project.services.FeatureService;
import banurr.final_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController
{
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private FeatureService featureService;

    @GetMapping("/")
    public String home()
    {
        return "home";
    }

    @GetMapping("/admin")
    public String adminPanel()
    {
        return "admin_panel";
    }

    @GetMapping("/admin/category")
    public String adminCategory(Model model)
    {
        model.addAttribute("categories", categoryService.allCategories());
        return "admin_category";
    }

    @GetMapping("/admin/product")
    public String adminProduct(Model model)
    {
        model.addAttribute("products", productService.allProducts());
        model.addAttribute("categories", categoryService.allCategories());
        model.addAttribute("features", featureService.allFeatures());
        return "admin_product";
    }

    @GetMapping("/admin/feature")
    public String adminFeature(Model model)
    {
        model.addAttribute("features", featureService.allFeatures());
        return "admin_feature";
    }
}

