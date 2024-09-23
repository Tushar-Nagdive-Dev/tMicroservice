package com.example.accountmswithpostgresql.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.accountmswithpostgresql.entities.Customer;


@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {

	Optional<Customer> findByMobileNumber(String mobileNumber);
}
