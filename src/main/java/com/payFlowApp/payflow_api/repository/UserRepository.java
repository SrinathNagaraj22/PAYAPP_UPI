package com.payFlowApp.payflow_api.repository;

import com.payFlowApp.payflow_api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.balance > :balance")
    List<User> findUsersWithBalanceGreaterThan(double balance);
    Optional<User> findByUpiId(String upiId);
}
