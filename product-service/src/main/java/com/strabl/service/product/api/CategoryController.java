package com.strabl.service.product.api;

import com.strabl.sdk.common.dto.enums.OfferingType;
import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.CategoryDTO;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.sdk.domain.entity.Category;
import com.strabl.sdk.domain.mapper.CategoryMapper;
import com.strabl.service.product.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/category")
@AllArgsConstructor
public class CategoryController {

  private static final Integer MAX_PAGE_SIZE = 50;
  private final CategoryService categoryService;

  @GetMapping("categories")
  public ResponseDTO<List<CategoryDTO>> getCategoriesByType(
      @RequestParam OfferingType type,
      @RequestParam Integer pageNumber,
      @RequestParam Integer pageSize
  ) {

    PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
    return ResponseDTO.success(categoryService.fetchCategoriesBy(type, pagedResponseRequest));
  }

  //As a admin i want to add category filter for products(Buy,Rent,Try)
  // isRental = true && isTbyb = false => rent
  @PostMapping("/categories/addBuyCategory")
  public ResponseDTO<List<ProductResponse>> addBuyCategory(
          @RequestParam Integer pageNumber,
          @RequestParam Integer pageSize
  ) {
    PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
    return ResponseDTO.success(categoryService.getByFilterCategory(false, false, pagedResponseRequest));
  }


  // isRental = true && isTbyb = false => rent
  @PostMapping("/categories/addRentCategory")
  public ResponseDTO<List<ProductResponse>> addRentCategory(
          @RequestParam Integer pageNumber,
          @RequestParam Integer pageSize
  ) {
    PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
    return ResponseDTO.success(categoryService.getByFilterCategory(true, false, pagedResponseRequest));
  }


  // isRental = false  && isTbyb = true => Tbyb
  @PostMapping("/categories/addTbybCategory")
  public ResponseDTO<List<ProductResponse>> addTbybCategory(
          @RequestParam Integer pageNumber,
          @RequestParam Integer pageSize
  ) {
    PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
    return ResponseDTO.success(categoryService.getByFilterCategory(false, true, pagedResponseRequest));
  }

  //As a admin i want to add category.
  @PostMapping("/categories/addCategory")
  public ResponseDTO<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO, @RequestParam Integer userId) {
    return ResponseDTO.success(CategoryMapper.toCategoryDTO(categoryService.addCategory(categoryDTO, userId)));
  }

}
