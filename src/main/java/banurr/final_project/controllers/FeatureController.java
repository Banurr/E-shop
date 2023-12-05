package banurr.final_project.controllers;

import banurr.final_project.models.Feature;
import banurr.final_project.models.Product;
import banurr.final_project.services.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/features")
public class FeatureController
{
    private final String redirectUrl = "/admin/feature";

    @Autowired
    private FeatureService featureService;

    @GetMapping("/find/{id}")
    public Feature getFeature(@PathVariable(name = "id") Long id)
    {
        return featureService.findFeature(id);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addFeature(@RequestBody Feature feature)
    {
        featureService.addFeature(feature);
        return ResponseEntity.ok(redirectUrl);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFeature(@PathVariable(name = "id" ) Long id)
    {
        featureService.deleteFeature(id);
        return ResponseEntity.ok(redirectUrl);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateFeature(@RequestBody Feature feature)
    {
        featureService.updateFeature(feature);
        return ResponseEntity.ok(redirectUrl);
    }
}
