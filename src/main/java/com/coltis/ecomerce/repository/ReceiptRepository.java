package com.coltis.ecomerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coltis.ecomerce.models.receipt;

@Repository
public interface ReceiptRepository extends JpaRepository<receipt, Integer> {
	List<receipt> getReceiptsByUser(Integer u);
}
