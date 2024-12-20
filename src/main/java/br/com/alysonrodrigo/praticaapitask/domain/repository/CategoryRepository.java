package br.com.alysonrodrigo.praticaapitask.domain.repository;

import br.com.alysonrodrigo.praticaapitask.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
