package com.quest.etna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.quest.etna.model.OrderDetail;
import com.quest.etna.model.OrderInput;
import com.quest.etna.service.OrderDetailService;

@RestController
public class OrderDetailController {
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	
	
	@PreAuthorize("hasRole('User')")
	@PostMapping("/placeOrder/{isSingleProductCheckout}")
	public void placeOrder(@PathVariable ("isSingleProductCheckout") boolean isSingleProductCheckout, @RequestBody OrderInput orderInput) {
		orderDetailService.placeOrder(orderInput, isSingleProductCheckout);
		
	}
	
	/*@PreAuthorize("hasRole('User')")
	@PostMapping("/placeOrder/{isSingleProductCheckout}")
	public void placeOrder(@PathVariable String isSingleProductCheckout, @RequestBody OrderInput orderInput) {
	    boolean isSingleProductCheckoutValue = Boolean.valueOf(isSingleProductCheckout);
	    orderDetailService.placeOrder(orderInput, isSingleProductCheckoutValue);   
	}*/

	
	/*@PostMapping("/placeOrder/{isSingleProductCheckout}")
	public void placeOrder(@PathVariable boolean isSingleProductCheckout, @RequestBody OrderInput orderInput, Authentication authentication) {
	    if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("User"))) {
	        orderDetailService.placeOrder(orderInput, isSingleProductCheckout);
	    } else {
	        throw new AccessDeniedException("User does not have ROLE_USER");
	    }
	}*/

	
	@PreAuthorize("hasRole('User')")
	@GetMapping("/getOrderDetails")
	public List<OrderDetail> getOrderDetails() {
		return orderDetailService.getOrderDetails();
	}
	
	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/getAllOrderDetails")
	public List<OrderDetail> getAllOrderDetails() {
		return orderDetailService.getAllOrderDetails();
	}

}
