package br.com.alysonrodrigo.praticaapitask.domain.service;

import br.com.alysonrodrigo.praticaapitask.domain.model.Category;
import br.com.alysonrodrigo.praticaapitask.domain.repository.CategoryRepository;
import br.com.alysonrodrigo.praticaapitask.exception.NegocioException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
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
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada"));
    }

    @Transactional
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Transactional
    public Category update(Long id, Category category) {
        Optional<Category> categoryToUpdate = categoryRepository.findById(id);
        if (categoryToUpdate.isPresent()) {
            Category category1 = categoryToUpdate.get();
            BeanUtils.copyProperties(category, category1, "id");
            return categoryRepository.save(category1);
        }
        return null;
    }

    @Transactional
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
