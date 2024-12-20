package br.com.alysonrodrigo.praticaapitask.domain.repository;


import br.com.alysonrodrigo.praticaapitask.domain.model.Category;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@ActiveProfiles("test")
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
        category.setName("Test");
        category.setDescription("Test Description");
    }

    @Test
    void testSaveCategory() {
        Category savedCategory = categoryRepository.save(category);

        Assertions.assertThat(savedCategory).isNotNull();
        Assertions.assertThat(savedCategory.getId()).isNotNull();
    }

    @Test
    void testFindCategoryById() {
        Category savedCategory = categoryRepository.save(category);
        Optional<Category> foundCategory = categoryRepository.findById(savedCategory.getId());

        Assertions.assertThat(foundCategory).isPresent();
        Assertions.assertThat(foundCategory.get().getName()).isEqualTo("Test");
    }

    @Test
    void testDeleteCategory() {
        Category savedCategory = categoryRepository.save(category);
        categoryRepository.deleteById(savedCategory.getId());

        Optional<Category> deletedCategory = categoryRepository.findById(savedCategory.getId());
        Assertions.assertThat(deletedCategory).isEmpty();
    }
}
