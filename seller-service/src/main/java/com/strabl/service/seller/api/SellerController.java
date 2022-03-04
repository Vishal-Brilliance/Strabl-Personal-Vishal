package com.strabl.service.seller.api;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.OrdersDTO;
import com.strabl.sdk.common.dto.response.ProductResponse;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.sdk.domain.dao.StrablSessionDao;
import com.strabl.sdk.domain.entity.User;
import com.strabl.service.seller.service.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.strabl.sdk.domain.entity.enums.columns.UserStatus.INACTIVE;

@Slf4j
@RestController
@RequestMapping("becomeSeller")
@RequiredArgsConstructor
public class SellerController {

	private  final SellerService sellerService;
	private final StrablSessionDao strablSessionDao;
	private static final Integer MAX_PAGE_SIZE =25;

	@PostMapping("/seller/{id}")
	public ResponseDTO<String> becomeSeller(
			@PathVariable("id") Integer id) {
		return ResponseDTO.success(sellerService.becomeSeller(id));
	}
// active to inactive
	@PutMapping("update/{id}")
	public ResponseDTO<String> changeStatus(@RequestParam String token , @PathVariable Integer id){
		UserResponseDTO user = strablSessionDao.getUserProfileForSession(token);
		sellerService.changeStatus(id,INACTIVE);
		return ResponseDTO.success("status changed successfully");
	}

	@GetMapping("product/{sellerId}")
	public ResponseDTO<List<ProductResponse>>  sellerProduct(@PathVariable Integer sellerId , @RequestParam String token , @RequestParam Integer pageNumber,
															 @RequestParam Integer pageSize){
		UserResponseDTO user = strablSessionDao.getUserProfileForSession(token);
		PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
		return ResponseDTO.success(sellerService.sellerProduct(sellerId , pagedResponseRequest));
	}

	@GetMapping("info")
	public ResponseDTO<User> sellerInfo(@RequestParam Integer id){
		return ResponseDTO.success(sellerService.sellerInfo(id));
	}

	@GetMapping("/getCompleteOrders")
	public ResponseDTO<List<OrdersDTO>> getCompleteOrders(
			@RequestParam Integer pageNumber,
			@RequestParam Integer pageSize,
			@RequestParam Integer userId
	){
		PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
		return ResponseDTO.success(sellerService.getCompleteOrders(userId, pagedResponseRequest));
	}

	@GetMapping("/getPendingOrders")
	public ResponseDTO<List<OrdersDTO>> getPendingOrders(
			@RequestParam Integer pageNumber,
			@RequestParam Integer pageSize,
			@RequestParam Integer userId
	){
		PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
		return ResponseDTO.success(sellerService.getPendingOrders(userId, pagedResponseRequest));
	}

	@GetMapping("/getCancelledOrders")
	public ResponseDTO<List<OrdersDTO>> getCancelledOrders(
			@RequestParam Integer pageNumber,
			@RequestParam Integer pageSize,
			@RequestParam Integer userId
	){
		PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
		return ResponseDTO.success(sellerService.getCancelledOrders(userId, pagedResponseRequest));
	}
}
