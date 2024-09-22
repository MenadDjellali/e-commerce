package com.quest.etna.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest.etna.configuration.JwtRequestFilter;
import com.quest.etna.repositories.CartRepository;
import com.quest.etna.repositories.OrderDetailRepository;
import com.quest.etna.repositories.ProductRepository;
import com.quest.etna.repositories.UserRepository;
import com.quest.etna.model.Cart;
import com.quest.etna.model.OrderDetail;
import com.quest.etna.model.OrderInput;
import com.quest.etna.model.OrderProductQuantity;
import com.quest.etna.model.Product;
import com.quest.etna.model.User;

@Service
public class OrderDetailService {
	
	private static final String ORDER_PLACED = "Placed";  
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	public List<OrderDetail> getAllOrderDetails(){
		List<OrderDetail> orderDetails = new ArrayList<>();
		orderDetailRepository.findAll().forEach(e -> orderDetails.add(e));
		
		return orderDetails;
	}
	
	public List<OrderDetail> getOrderDetails() {
		String currentUser = JwtRequestFilter.CURRENT_USER;
		User user = userRepository.findById(currentUser).get();
		
		return orderDetailRepository.findByUser(user);
	}
	
	public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout) {
		List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();
		
		for(OrderProductQuantity o: productQuantityList) {
			Product product = productRepository.findById(o.getProductId()).get();
			
			String currentUser = JwtRequestFilter.CURRENT_USER;
			User user= userRepository.findById(currentUser).get();
			
			OrderDetail orderDetail = new OrderDetail(
					orderInput.getFullName(),
					orderInput.getFullAddress(),
					orderInput.getContactNumber(),
					orderInput.getAlternateContactNumber(),
					ORDER_PLACED,
					product.getProductDiscountedPrice()*o.getQuantity(),
					product,
					user);
			
			if(!isSingleProductCheckout) {
				List<Cart> carts= cartRepository.findByUser(user);
				carts.stream().forEach(x -> cartRepository.deleteById(x.getCartId()));			
				
			}
			orderDetailRepository.save(orderDetail);
		}
	}
	
	

}
