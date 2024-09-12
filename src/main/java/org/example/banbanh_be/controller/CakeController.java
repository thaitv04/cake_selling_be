package org.example.banbanh_be.controller;

import org.example.banbanh_be.dto.CakeDto;
import org.example.banbanh_be.model.*;
import org.example.banbanh_be.repository.CakeRepo;
import org.example.banbanh_be.repository.ITypeRepo;
import org.example.banbanh_be.repository.IUserRepo;
import org.example.banbanh_be.repository.ImageRepo;
import org.example.banbanh_be.service.impl.CakeService;
import org.example.banbanh_be.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@RestController("apiCake")
@RequestMapping("/api/cake")
@CrossOrigin
public class CakeController {
    @Autowired
    private CakeRepo cakeRepo;
    @Autowired
    private CakeService cakeService;
    @Autowired
    private ITypeRepo typeRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private IUserRepo userRepo;
    @Autowired
    private ImageRepo imageRepo;

    //hiển thị bánh
    @GetMapping
    public ResponseEntity<List<Cake>> listCake(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "typeIdCake", required = false) String typeIdCake,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "100") int size
    ) {
        CakeDto dto = new CakeDto()
                .setName(name)
                .setTypeIdCake(typeIdCake);
        PaginateRequest paginateRequest = new PaginateRequest(page, size);
        Page<Cake> pages = cakeService.findAll(dto, paginateRequest);
        return new ResponseEntity<>(pages.getContent(), HttpStatus.OK);
    }

    //Thêm bánh
    @PostMapping()
    public ResponseEntity<Cake> save(@ModelAttribute CakeDto cakeDto) throws IOException {
        Cake cake = cakeService.saveCake(cakeDto);
        return new ResponseEntity<>(cake, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cake> update(@ModelAttribute CakeDto cakeDto, @PathVariable int id) throws IOException {
        Optional<Cake> optionalCake = cakeRepo.findById(id);



        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowWithoutSeconds = now.truncatedTo(ChronoUnit.MINUTES);
        if (optionalCake.isPresent()) {
            Cake cake = optionalCake.get();
            cake.setName(cakeDto.getName());
            cake.setQuantity(cakeDto.getQuantity());

            cake.setCode(cakeDto.getCode());
            cake.setDescription(cakeDto.getDescription());

            cake.setPrice(cakeDto.getPrice());
            cake.setCreatedAt(nowWithoutSeconds);
            List<Image> images = imageRepo.findByIdCake(id);
            for (Image img : images) {
                imageRepo.delete(img);
            }

            return new ResponseEntity<>(cakeService.updateCake(cakeDto), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @GetMapping("/typeCake")
    public ResponseEntity<Iterable<TypeOfCake>> findAll() {
        List<TypeOfCake> typeRooms = (List<TypeOfCake>) typeRepo.findAll();
        if (typeRooms.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(typeRooms, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cake> findCakeById(@PathVariable int id) {
        Optional<Cake> cakeOptional = cakeService.findById(id);
        if (!cakeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cakeOptional.get(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User user1 = userService.checkUser(user);
        if (user1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<User> register(@ModelAttribute User user) {
        User user1 = userRepo.save(user);
        if (user1 == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user1, HttpStatus.OK);
    }



}
