package com.quest.etna.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quest.etna.configuration.JwtRequestFilter;
import com.quest.etna.repositories.CartRepository;
import com.quest.etna.repositories.ProductRepository;
import com.quest.etna.repositories.UserRepository;
import com.quest.etna.model.Cart;
import com.quest.etna.model.Product;
import com.quest.etna.model.User;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public void deleteCartItem(Integer cartId) {
		cartRepository.deleteById(cartId);
	}
	
	public Cart addToCart(Integer productId) {
		
		Product product = productRepository.findById(productId).get();
		
		String username = JwtRequestFilter.CURRENT_USER;
		
		User user = null;
		
		if(username != null) {
			user = userRepository.findById(username).get();
			
		}
		
		List<Cart> cartList = cartRepository.findByUser(user);
		List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getProductId() == productId).collect(Collectors.toList());
		
		if(filteredList.size() > 0) {
			return null;
		}
		
		
		if(product != null && user != null) {
			Cart cart = new Cart(product, user);
			return cartRepository.save(cart);
		}
		return null;
	}
	
	public List<Cart> getCartDetails(){
		String username = JwtRequestFilter.CURRENT_USER;
		User user = userRepository.findById(username).get();
		return cartRepository.findByUser(user);
		
	}
	
	

}
