package org.example.banbanh_be.service;

import org.example.banbanh_be.dto.CakeDto;
import org.example.banbanh_be.dto.CartDto;
import org.example.banbanh_be.model.Cake;
import org.example.banbanh_be.model.Cart;

import java.io.IOException;
import java.util.List;

public interface ICartService extends IGenerateService<Cart>{
    Cart saveCart(CartDto cartDto)throws IOException;
    List<Cart> findByUserId(int idUser)throws IOException;
    Cart updateCart(CartDto cartDto) throws IOException;
}
