package com.strabl.service.order.api;

import java.util.List;

import com.strabl.sdk.common.dto.page.PagedResponseRequest;
import com.strabl.sdk.common.dto.response.OrdersDTO;
import com.strabl.sdk.common.dto.response.ResponseDTO;
import com.strabl.sdk.common.dto.response.UserResponseDTO;
import com.strabl.sdk.domain.dao.StrablSessionDao;
import com.strabl.sdk.domain.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import com.strabl.sdk.domain.entity.Orders;
import com.strabl.service.order.service.OrderService;

import lombok.AllArgsConstructor;

import javax.naming.AuthenticationException;

import static com.strabl.sdk.domain.entity.enums.columns.OrdersStatus.CANCELLED;
import static com.strabl.sdk.domain.entity.enums.columns.UserStatus.INACTIVE;

@RestController
@RequestMapping("/v1/order")
@Configuration
@AllArgsConstructor
public class OrderController {
	
	private final OrderService orderService;
	private static final Integer MAX_PAGE_SIZE = 25;
	private final StrablSessionDao strablSessionDao;
	
	@GetMapping("getById")
	public ResponseDTO<List<OrdersDTO>> getOrderByCustomerId(@RequestParam String token , @RequestParam Integer user_id , @RequestParam Integer pageNumber,
															 @RequestParam Integer pageSize) throws AuthenticationException {
		UserResponseDTO user = strablSessionDao.getUserProfileForSession(token);
		PagedResponseRequest pagedResponseRequest = PagedResponseRequest.of(pageNumber, pageSize, MAX_PAGE_SIZE);
		return ResponseDTO.success(orderService.getOrderByCustomerId(user_id , pagedResponseRequest));
	}

	@PostMapping("order")
	public ResponseDTO<OrdersDTO> placeOrder(@RequestBody Orders orders , @RequestParam Integer userId, @RequestParam String token) throws AuthenticationException{
		UserResponseDTO user = strablSessionDao.getUserProfileForSession(token);
		return ResponseDTO.success(orderService.placeOrder(orders , userId));
	}

}
