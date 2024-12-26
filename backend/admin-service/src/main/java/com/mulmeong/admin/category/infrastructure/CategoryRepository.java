package com.mulmeong.admin.category.infrastructure;


import com.mulmeong.admin.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsByCategoryName(String categoryName);


}
