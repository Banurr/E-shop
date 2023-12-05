package banurr.final_project.controllers;

import banurr.final_project.models.Category;
import banurr.final_project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@RestController()
@RequestMapping("/categories")
public class CategoryController
{
    private final String redirectUrl = "/admin/category";
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PictureController pictureController;

    @GetMapping("/all")
    public List<Category> getCategories()
    {
        return categoryService.allCategories();
    }

    @GetMapping("/find/{id}")
    public Category getCategory(@PathVariable(name="id") Long id)
    {
        return categoryService.findCategory(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addCategory(@RequestBody Category category)
    {
        categoryService.addCategory(category);
        return ResponseEntity.ok(redirectUrl);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteCategory(@RequestBody Long id)
    {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(redirectUrl);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCategory(@RequestBody Category category)
    {
        categoryService.updateCategory(category);
        return ResponseEntity.ok(redirectUrl);
    }

    @PutMapping("/add_photo/{id}")
    public ResponseEntity<String> setPhoto(@RequestPart(name="file") MultipartFile multipartFile,
                                           @PathVariable(name = "id") Long id) throws IOException
    {
        categoryService.setPhotoCategory(multipartFile,id);
        pictureController.addPictureLocal(multipartFile);
        return ResponseEntity.ok(redirectUrl);
    }

    @PutMapping("/reset_photo")
    public ResponseEntity<String> removePhoto(@RequestBody Long id)
    {
        Category category = categoryService.findCategory(id);
        category.setPicture("noimage.jpeg");
        categoryService.updateCategory(category);
        return ResponseEntity.ok(redirectUrl);
    }
}
