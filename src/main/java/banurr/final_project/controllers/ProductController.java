package banurr.final_project.controllers;

import banurr.final_project.models.Product;
import banurr.final_project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController
{
    private final String redirectUrl = "/admin/product";

    @Autowired
    private ProductService productService;

    @Autowired
    private PictureController pictureController;

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
    public ResponseEntity<String> updateProduct(@RequestBody Product product)
    {
        productService.updateProduct(product);
        return ResponseEntity.ok("{\"redirectUrl\": \"" + redirectUrl + "\"}");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") Long id)
    {
        productService.deleteProduct(id);
        return ResponseEntity.ok("{\"redirectUrl\": \"" + redirectUrl + "\"}");
    }

    @PutMapping("/add_photo/{id}")
    public ResponseEntity<String> setPhoto(@RequestPart(name="file") MultipartFile multipartFile,
                                           @PathVariable(name="id") Long id) throws IOException
    {
        productService.setPhotoCategory(multipartFile,id);
        pictureController.addPictureLocal(multipartFile);
        return ResponseEntity.ok("{\"redirectUrl\": \"" + redirectUrl + "\"}");
    }

    @PutMapping("/reset_photo/{id}")
    public ResponseEntity<String> removePhoto(@PathVariable(name="id") Long id)
    {
        Product category = productService.findProduct(id);
        category.setPicture("noimage.jpeg");
        productService.updateProduct(category);
        return ResponseEntity.ok("{\"redirectUrl\": \"" + redirectUrl + "\"}");
    }
}
