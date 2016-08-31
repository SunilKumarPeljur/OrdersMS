/**
 * 
 */
package com.training.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author admin
 *
 */

@Repository
public interface OrdersRepository extends MongoRepository<Orders, Integer> {	
	
	@Query("{ 'manufacturerId' : ?0 }")
	public List<Orders> findByManufactuerId(Integer id);
	
}
