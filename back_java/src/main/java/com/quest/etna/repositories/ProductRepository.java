package com.quest.etna.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quest.etna.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>{

	public List<Product> findAll(Pageable pageable);
	
	public List<Product>  findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
			String key1, String key2, Pageable pageable);
	

}
