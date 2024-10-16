package com.example.accountmswithpostgresql.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.example.accountmswithpostgresql.entities.Accounts;

import jakarta.transaction.Transactional;

@Repository
public interface AccountRepo extends JpaRepository<Accounts, Long>{
	
	Optional<Accounts> findByCustomerId(Long id);
	
	@Transactional
	@Modifying
	void deleteByCustomerId(Long id);
}
