package org.example.banbanh_be.repository;

import org.example.banbanh_be.model.Cake;
import org.example.banbanh_be.model.Cart;
import org.example.banbanh_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IUserRepo extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {
}
