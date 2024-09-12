package org.example.banbanh_be.repository;

import org.example.banbanh_be.model.Cake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CakeRepo extends JpaRepository<Cake,Integer>,JpaSpecificationExecutor<Cake> {
}
