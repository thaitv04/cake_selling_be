package org.example.banbanh_be.controller;

import org.example.banbanh_be.dto.CartDto;
import org.example.banbanh_be.model.Cake;
import org.example.banbanh_be.model.Cart;
import org.example.banbanh_be.model.User;
import org.example.banbanh_be.repository.CakeRepo;
import org.example.banbanh_be.repository.ICartRepo;
import org.example.banbanh_be.repository.IUserRepo;
import org.example.banbanh_be.service.impl.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RestController("apiCart")
@RequestMapping("/api/cart")
@CrossOrigin
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private CakeRepo cakeRepo;
    @Autowired
    private ICartRepo iCartRepo;


    @GetMapping("/{id}")
    public ResponseEntity<List<Cart>> findAll(@PathVariable int id) {
        List<Cart> cart = iCartRepo.findByUserId(id);
        if (cart.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Cart> save(@ModelAttribute CartDto cartDto) {
        Cart cart = cartService.saveCart(cartDto);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        iCartRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cart> update(@ModelAttribute CartDto cartDto, @PathVariable int id) throws IOException {
        Optional<Cart> optionalCart = iCartRepo.findById(id);
        Optional<User> userOptional = userRepo.findById(cartDto.getId_user());
        Optional<Cake> cakeOptional = cakeRepo.findById(cartDto.getId_cake());

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowWithoutSeconds = now.truncatedTo(ChronoUnit.MINUTES);

        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.setQuantity(cartDto.getQuantity());
            cart.setUser(userOptional.get());
            cart.setCake(cakeOptional.get());
            cart.setCreatedAt(nowWithoutSeconds);
            Cart updatedCart = cartService.updateCart(cartDto);
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/findById/{id}")
    public ResponseEntity<Cart> findCartById(@PathVariable int id) {
        Optional<Cart> cartOptional= iCartRepo.findById(id);
        if (!cartOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cartOptional.get(), HttpStatus.OK);
    }


    @PostMapping("/checkout/{userId}")
    public ResponseEntity<Void> checkout(@PathVariable int userId) {
        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            cartService.completePayment(userOptional.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
