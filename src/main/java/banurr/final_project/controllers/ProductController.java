package banurr.final_project.controllers;

import banurr.final_project.models.Product;
import banurr.final_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController
{

    private final String redirectUrl = "/admin/product";

    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<Product> getProducts()
    {
        return productService.allProducts();
    }

    @GetMapping("/find/{id}")
    public Product getProduct(@PathVariable(name = "id") Long id)
    {
        return productService.findProduct(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody Product product)
    {
        productService.addProduct(product);
        return ResponseEntity.ok("{\"redirectUrl\": \"" + redirectUrl + "\"}");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateProduct(@RequestPart Product product)
    {
        productService.updateProduct(product);
        return ResponseEntity.ok("{\"redirectUrl\": \"" + redirectUrl + "\"}");
    }

}
