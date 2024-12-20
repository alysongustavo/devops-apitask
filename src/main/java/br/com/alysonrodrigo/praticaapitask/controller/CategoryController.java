package br.com.alysonrodrigo.praticaapitask.controller;

import br.com.alysonrodrigo.praticaapitask.domain.model.Category;
import br.com.alysonrodrigo.praticaapitask.domain.service.CategoryService;
import br.com.alysonrodrigo.praticaapitask.dto.CategoryDTO;
import br.com.alysonrodrigo.praticaapitask.mapper.CategoryMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {

        List<Category> listCategory = categoryService.findAll();

        if(listCategory.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<CategoryDTO> listDTO =  listCategory.stream()
                .map(CategoryMapper.INSTANCE::toDTO).toList();

        return ResponseEntity.ok(listDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable Long id) {
        Category category = categoryService.findById(id);

        CategoryDTO categoryDTO = CategoryMapper.INSTANCE.toDTO(category);

        return ResponseEntity.ok(categoryDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> save(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = CategoryMapper.INSTANCE.toEntity(categoryDTO);

        category = categoryService.save(category);

        categoryDTO = CategoryMapper.INSTANCE.toDTO(category);

        return ResponseEntity.ok(categoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
        Category category = CategoryMapper.INSTANCE.toEntity(categoryDTO);

        category = categoryService.update(id, category);

        if(category == null) {
            return ResponseEntity.notFound().build();
        }

        categoryDTO = CategoryMapper.INSTANCE.toDTO(category);

        return ResponseEntity.ok(categoryDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        categoryService.delete(category.getId());
        return ResponseEntity.noContent().build();
    }
}
