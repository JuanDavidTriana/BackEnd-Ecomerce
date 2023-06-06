package com.coltis.ecomerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coltis.ecomerce.models.product;

@Repository
public interface ProductRepository extends JpaRepository<product, Integer>{

}
