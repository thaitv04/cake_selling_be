package org.example.banbanh_be.repository;

import org.example.banbanh_be.model.Image;
import org.example.banbanh_be.model.TypeOfCake;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITypeRepo extends JpaRepository<TypeOfCake,Integer> {
}
