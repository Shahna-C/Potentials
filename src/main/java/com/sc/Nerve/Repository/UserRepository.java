package com.sc.Nerve.Repository;

import org.springframework.data.repository.CrudRepository;

import com.sc.Nerve.Entity.User;

public interface UserRepository extends CrudRepository <User, Long>{
	
			public User findByUsername(String username);
}
