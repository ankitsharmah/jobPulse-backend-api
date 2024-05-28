package com.jobpulse.controller;

import com.jobpulse.dto.CategoryDto;
import com.jobpulse.dto.JobDto;
import com.jobpulse.entity.Category;
import com.jobpulse.service.CategoryService;
import com.jobpulse.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final JobService jobService;


    @PostMapping("/add-category")
    public ResponseEntity<?> addCategory(@RequestBody Category category){
        return ResponseEntity.ok().body(categoryService.add(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getByID(@PathVariable long id){
        return ResponseEntity.ok().body(categoryService.findById(id));
    }
    @PutMapping("/update/{id}")
    public  ResponseEntity<Category> updateById(@PathVariable long id, @RequestBody CategoryDto categoryDto){
        return ResponseEntity.ok().body(categoryService.update(id,categoryDto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable long id){
        return ResponseEntity.ok().body(categoryService.delete(id));
    }
    @GetMapping("")
    public ResponseEntity<?> getCategory(){
        return ResponseEntity.ok().body(categoryService.get());
    }
}
