package com.jobpulse.service;

import com.jobpulse.dto.CategoryDto;
import com.jobpulse.entity.Category;
import com.jobpulse.mapper.CategoryMapper;
import com.jobpulse.repository.CategoryRepository;
import org.hibernate.sql.ast.tree.cte.CteMaterialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class CategoryService {
@Autowired
    private CategoryRepository categoryRepository;

    public String add(Category category){
        categoryRepository.save(category);
        return "added successfully";
    }

    public List<CategoryDto> get(){

        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtos=new ArrayList<>();
        CategoryMapper categoryMapper = new CategoryMapper();
        for (Category category:categories
             ) {
            categoryDtos.add(categoryMapper.toDto(category));
        }

        return categoryDtos;
    }

    public Category findById(long id) {
        return categoryRepository.findById(id).get();
    }

    public String delete(long id){
        categoryRepository.deleteById(id);
        return "Deleted successfully";
    }
    public Category update(long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).get();

        category.setType(categoryDto.getType());
        category.setJobs(category.getJobs());

        return categoryRepository.save(category);
    }
}
