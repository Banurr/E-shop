package banurr.final_project.services;

import banurr.final_project.models.Product;
import banurr.final_project.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductService
{
    @Autowired
    private ProductRepository productRepository;

    public List<Product> allProducts()
    {
        return productRepository.findAll();
    }

    public Product findProduct(Long id)
    {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(Long id)
    {
        productRepository.deleteById(id);
    }

    public void deleteProductByCategoryId(Long id){productRepository.deleteProductbyCategoryId(id);}

    public void addProduct(Product product)
    {
        productRepository.save(product);
    }

    public void updateProduct(Product product)
    {
        productRepository.save(product);
    }
    public void addPicture(MultipartFile picture, Long productId)
    {
        var product = findProduct(productId);
        product.setPicture(picture.getOriginalFilename());
        productRepository.save(product);
    }
}
