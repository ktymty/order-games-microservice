package com.ktymty.gamesorderservice.repository;

import com.ktymty.gamesorderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
