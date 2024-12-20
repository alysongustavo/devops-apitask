package br.com.alysonrodrigo.praticaapitask.domain.service;

import br.com.alysonrodrigo.praticaapitask.domain.model.Category;
import br.com.alysonrodrigo.praticaapitask.domain.repository.CategoryRepository;
import br.com.alysonrodrigo.praticaapitask.exception.NegocioException;
import jakarta.persistence.EntityNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        category = new Category();
        category.setId(1L);
        category.setName("Technology");
        category.setDescription("Technology related category");
    }

    @Test
    void testFindByIdSuccess() {
        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        Category foundCategory = categoryService.findById(1L);

        Assertions.assertThat(foundCategory).isNotNull();
        Assertions.assertThat(foundCategory.getName()).isEqualTo("Technology");
        Mockito.verify(categoryRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> categoryService.findById(1L))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessage("Categoria não encontrada");
    }

    @Test
    void testSaveCategory() {
        Mockito.when(categoryRepository.save(category)).thenReturn(category);

        Category savedCategory = categoryService.save(category);

        Assertions.assertThat(savedCategory).isNotNull();
        Mockito.verify(categoryRepository, Mockito.times(1)).save(category);
    }
}
