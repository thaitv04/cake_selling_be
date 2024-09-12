package org.example.banbanh_be.service;

import org.example.banbanh_be.dto.CakeDto;
import org.example.banbanh_be.model.Cake;
import org.example.banbanh_be.model.PaginateRequest;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface ICakeService extends IGenerateService<Cake>{
    Page<Cake> findAll(CakeDto house, PaginateRequest paginateRequest);
    Cake saveCake(CakeDto cakeDto)throws IOException;
    Cake updateCake(CakeDto cakeDto)throws IOException;
}
