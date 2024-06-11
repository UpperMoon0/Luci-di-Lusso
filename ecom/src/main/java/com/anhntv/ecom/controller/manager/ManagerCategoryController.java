package com.anhntv.ecom.controller.manager;

import com.anhntv.ecom.dto.CategoryDTO;
import com.anhntv.ecom.dto.commons.CommonsDTO;
import com.anhntv.ecom.entities.Category;
import com.anhntv.ecom.services.manager.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
@RequiredArgsConstructor
public class ManagerCategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO dto) {
        Category category = categoryService.createCategory(dto);
        CommonsDTO dto1 = new CommonsDTO();
        dto1.setMsg("Create category success");
        System.out.println(dto1.getMsg());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping(" ")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
