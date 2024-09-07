package com.example.trailsWithPostGresql.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.example.trailsWithPostGresql.entities.Accounts;
import com.example.trailsWithPostGresql.entities.Customer;

import jakarta.transaction.Transactional;

@Repository
public interface AccountRepo extends JpaRepository<Accounts, Long>{
	
	Optional<Accounts> findByCustomerId(Long id);
	
	@Transactional
	@Modifying
	void deleteByCustomerId(Long id);
}
