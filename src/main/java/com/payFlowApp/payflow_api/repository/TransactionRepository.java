package com.payFlowApp.payflow_api.repository;

import com.payFlowApp.payflow_api.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
