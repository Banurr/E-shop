package banurr.final_project.controllers;

import banurr.final_project.models.Category;
import banurr.final_project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        return ResponseEntity.ok("{\"redirectUrl\": \"" + redirectUrl + "\"}");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id)
    {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok("{\"redirectUrl\": \"" + redirectUrl + "\"}");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable(name = "id" ) Long id,
                                                 @RequestBody String new_name)
    {
        Category category = categoryService.findCategory(id);
        category.setName(new_name.replace("\"",""));
        categoryService.updateCategory(category);
        return ResponseEntity.ok("{\"redirectUrl\": \"" + redirectUrl + "\"}");
    }

    @PutMapping("/add_photo/{id}")
    public ResponseEntity<String> setPhoto(@RequestPart(name="file") MultipartFile multipartFile,
                                           @PathVariable(name="id") Long id) throws IOException
    {
        categoryService.setPhotoCategory(multipartFile,id);
        pictureController.addPictureLocal(multipartFile);
        return ResponseEntity.ok("{\"redirectUrl\": \"" + redirectUrl + "\"}");
    }

    @PutMapping("/reset_photo/{id}")
    public ResponseEntity<String> removePhoto(@PathVariable(name="id") Long id)
    {
        Category category = categoryService.findCategory(id);
        category.setPicture("noimage.jpeg");
        categoryService.updateCategory(category);
        return ResponseEntity.ok("{\"redirectUrl\": \"" + redirectUrl + "\"}");
    }
}
