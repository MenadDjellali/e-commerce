package com.quest.etna.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quest.etna.model.OrderDetail;
import com.quest.etna.model.User;

@Repository
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer>{
	
	public List<OrderDetail> findByUser(User user);

}
