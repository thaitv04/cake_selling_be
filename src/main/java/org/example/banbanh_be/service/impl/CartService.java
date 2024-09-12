package org.example.banbanh_be.service.impl;

import org.example.banbanh_be.dto.CartDto;
import org.example.banbanh_be.dto.ResourceNotFoundException;
import org.example.banbanh_be.model.Cake;
import org.example.banbanh_be.model.Cart;
import org.example.banbanh_be.model.Order;
import org.example.banbanh_be.model.User;
import org.example.banbanh_be.repository.CakeRepo;
import org.example.banbanh_be.repository.ICartRepo;
import org.example.banbanh_be.repository.IUserRepo;
import org.example.banbanh_be.repository.OrderRepo;
import org.example.banbanh_be.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
@Service
public class CartService implements ICartService {
    @Autowired
    private ICartRepo iCartRepo;
    @Autowired
    private IUserRepo iUserRepo;
    @Autowired
    private CakeRepo cakeRepo;


    @Override
    public Iterable<Cart> findAll() {
        return iCartRepo.findAll();
    }

    @Override
    public Optional<Cart> findById(int id) {
        return Optional.empty();
    }

    @Override
    public Cart save(Cart cart) {
        return null;
    }

    @Override
    public void delete(int id) {
        iCartRepo.deleteById(id);
    }


    @Override
    public Cart saveCart(CartDto cartDto) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowWithoutSeconds = now.truncatedTo(ChronoUnit.MINUTES);

        Cart cart = cartDto.toCart();
        cart.setCreatedAt(nowWithoutSeconds);

        User user = iUserRepo.findById(cartDto.getId_user()).orElseThrow(() -> new RuntimeException("User not found"));
        Cake cake = cakeRepo.findById(cartDto.getId_cake()).orElseThrow(() -> new RuntimeException("Cake not found"));

        // Tìm giỏ hàng hiện tại của người dùng
        List<Cart> existingCarts = iCartRepo.findByUserAndCake(user, cake);

        if (!existingCarts.isEmpty()) {
            // Cập nhật số lượng của mặt hàng đã tồn tại
            Cart existingCart = existingCarts.get(0);
            existingCart.setQuantity(existingCart.getQuantity() + cart.getQuantity());
            existingCart.setCreatedAt(nowWithoutSeconds);
            return iCartRepo.save(existingCart);
        } else {
            // Nếu không tồn tại, tạo mới và lưu vào giỏ hàng
            cart.setUser(user);
            cart.setCake(cake);
            return iCartRepo.save(cart);
        }
    }


    @Override
    public List<Cart> findByUserId(int idUser) throws IOException {
        return List.of();
    }
    @Override
    public Cart updateCart(CartDto cartDto) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowWithoutSeconds = now.truncatedTo(ChronoUnit.MINUTES);

        Cart cart = cartDto.toCart();
        cart.setCreatedAt(nowWithoutSeconds);

        User user = iUserRepo.findById(cartDto.getId_user()).orElseThrow(() -> new RuntimeException("User not found"));
        Cake cake = cakeRepo.findById(cartDto.getId_cake()).orElseThrow(() -> new RuntimeException("Cake not found"));

        // Tìm giỏ hàng hiện tại của người dùng
        List<Cart> existingCarts = iCartRepo.findByUserAndCake(user, cake);

        if (!existingCarts.isEmpty()) {
            // Cập nhật số lượng của mặt hàng đã tồn tại
            Cart existingCart = existingCarts.get(0);
            existingCart.setQuantity(existingCart.getQuantity());
            existingCart.setCreatedAt(nowWithoutSeconds);
            return iCartRepo.save(existingCart);
        } else {
            // Nếu không tồn tại, tạo mới và lưu vào giỏ hàng
            cart.setUser(user);
            cart.setCake(cake);
            return iCartRepo.save(cart);
        }
    }
    @Autowired
    private OrderRepo orderRepo;

    public void completePayment(User user) {
        List<Cart> cartItems = iCartRepo.findByUserId(user.getId());
        Cake cake=new  Cake();

        for (Cart cart : cartItems) {
            Order order = new Order();
            order.setUser(user);
            order.setCake(cart.getCake());
            order.setQuantity(cart.getQuantity());
            order.setCreatedAt(LocalDateTime.now());
            orderRepo.save(order);
        }
        iCartRepo.deleteByUserId(user.getId());
    }


}
