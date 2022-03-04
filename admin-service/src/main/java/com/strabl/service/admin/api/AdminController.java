package com.strabl.service.admin.api;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.sdk.common.dto.response.TransactionDTO;
import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.sdk.domain.entity.Category;
import com.strabl.sdk.domain.entity.User;
import com.strabl.service.admin.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/admin")
@AllArgsConstructor
public class AdminController {

	private static final Integer MAX_PAGE_SIZE = 50;
	private final AdminService adminService;

	@GetMapping("getAllCustomers")
	public ResponseDTO<List<UserResponseDTO>> getAllCustomers( @RequestParam Integer pageNumber,
															  @RequestParam Integer pageSize) {
		PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
		return ResponseDTO.success(adminService.getAllCustomers(pagedResponseRequest));
	}

	@GetMapping("getAllCategories")
	public ResponseDTO<List<Category>> getAllCategory(
			@RequestParam Integer pageNumber,
			@RequestParam Integer pageSize){
		PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
		return ResponseDTO.success(adminService.getAllCategory(pagedResponseRequest));
	}

}
