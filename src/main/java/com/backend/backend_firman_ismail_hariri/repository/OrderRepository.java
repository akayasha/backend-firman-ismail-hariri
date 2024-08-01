package com.backend.backend_firman_ismail_hariri.repository;

import com.backend.backend_firman_ismail_hariri.model.entity.Order;
import com.backend.backend_firman_ismail_hariri.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findByCustomer(User customer);
    List<Order> findByProduct_Merchant(User merchant);

}
