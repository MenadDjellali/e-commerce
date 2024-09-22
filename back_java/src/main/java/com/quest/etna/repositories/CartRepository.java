package com.quest.etna.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quest.etna.model.Cart;
import com.quest.etna.model.User;

@Repository
public interface CartRepository extends CrudRepository<Cart, Integer>{
	
	public List<Cart> findByUser(User user);

}
