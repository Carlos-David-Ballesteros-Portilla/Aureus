package com.budget.service;

import com.budget.model.Category;
import com.budget.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con id: " + id));
    }

    public Category save(String name) {
        if (categoryRepository.findByName(name).isPresent()) {
            throw new IllegalArgumentException("Ya existe una categoría con ese nombre.");
        }
        return categoryRepository.save(new Category(name));
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
