package com.web.app.dao;

import com.web.app.dao.entity.OrderEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntry, Long> {
}
