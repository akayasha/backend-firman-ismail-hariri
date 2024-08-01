package com.backend.backend_firman_ismail_hariri.repository;

import com.backend.backend_firman_ismail_hariri.model.entity.Product;
import com.backend.backend_firman_ismail_hariri.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByMerchant(User Merchant);

}
