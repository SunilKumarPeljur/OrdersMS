/**
 * 
 */
package com.training.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author admin
 *
 */

@Repository
public interface OrdersRepository extends MongoRepository<Orders, Integer> {	
	
}
