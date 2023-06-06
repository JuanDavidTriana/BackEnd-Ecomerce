package com.coltis.ecomerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coltis.ecomerce.models.user;

@Repository
public interface UserRepository extends JpaRepository<user, Integer> {
	Optional<user> getByEmail(String email);
}
