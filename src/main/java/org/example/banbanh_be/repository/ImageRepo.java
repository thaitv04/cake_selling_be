package org.example.banbanh_be.repository;

import org.example.banbanh_be.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepo extends JpaRepository<Image,Integer>  {
    @Query("from Image as i where i.cake.id = ?1")
    List<Image> findByIdCake(int id);
}
