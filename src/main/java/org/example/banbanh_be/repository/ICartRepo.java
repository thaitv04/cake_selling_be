package org.example.banbanh_be.repository;

import org.example.banbanh_be.model.Cake;
import org.example.banbanh_be.model.Cart;
import org.example.banbanh_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ICartRepo extends JpaRepository<Cart,Integer>, JpaSpecificationExecutor<Cart> {
    List<Cart> findByUserId(int userId);
    @Modifying
    @Transactional
    void deleteByUserId(int id);
    List<Cart> findByUserAndCake(User user, Cake cake);

}
