package org.example.banbanh_be.service.impl;

import org.example.banbanh_be.dto.CakeDto;
import org.example.banbanh_be.dto.CakeSpec;
import org.example.banbanh_be.model.Cake;
import org.example.banbanh_be.model.Image;
import org.example.banbanh_be.model.PaginateRequest;
import org.example.banbanh_be.repository.CakeRepo;
import org.example.banbanh_be.repository.ITypeRepo;
import org.example.banbanh_be.repository.IUserRepo;
import org.example.banbanh_be.repository.ImageRepo;
import org.example.banbanh_be.service.ICakeService;
import org.example.banbanh_be.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class CakeService implements ICakeService {

    @Value("${file-upload}")
    private String fileUpload;
    @Autowired
    private CakeRepo cakeRepo;
    @Autowired
    private ImageRepo imageRepo;
    @Autowired
    private ITypeRepo typeRepo;
    @Autowired
    private IUserRepo userRepo;

    @Override
    public Cake saveCake(CakeDto cakeDto) throws IOException {
        // tự nhập thời gian
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowWithoutSeconds = now.truncatedTo(ChronoUnit.MINUTES);

        //thêm bánh

        Cake cake = cakeDto.toCake();
        cake.setCreatedAt(nowWithoutSeconds);
        cake.setTypeOfCake(typeRepo.findById(cakeDto.getTypeIdCake()).get());
        cake.setUser(userRepo.findById(cakeDto.getId_user()).get());
        cake = cakeRepo.save(cake);



//thêm ảnh
        if (cakeDto.getImage() == null) {
            Image image = new Image();
            image.setName("file-upload/default.jpg");
            image.setCake(cake);
            imageRepo.save(image);
        }
        MultipartFile[] multipartFile = cakeDto.getImage();
        for (MultipartFile file : multipartFile) {
            String filename = file.getOriginalFilename();
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + filename));
            Image image = new Image();
            image.setName(filename);
            image.setCake(cake);
            imageRepo.save(image);
        }
        return cake;
    }

    @Override
    public Cake updateCake(CakeDto cakeDto) throws IOException {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowWithoutSeconds = now.truncatedTo(ChronoUnit.MINUTES);
        //thêm bánh

        Cake cake = cakeDto.toCake();
        cake.setCreatedAt(nowWithoutSeconds);
        cake.setTypeOfCake(typeRepo.findById(cakeDto.getTypeIdCake()).get());
        cake.setUser(userRepo.findById(cakeDto.getId_user()).get());
        cakeRepo.findById(cakeDto.getId());
        cake = cakeRepo.save(cake);



//thêm ảnh
        if (cakeDto.getImage() == null) {
            Image image = new Image();
            image.setName("file-upload/default.jpg");
            image.setCake(cake);
            imageRepo.save(image);
        }
        MultipartFile[] multipartFile = cakeDto.getImage();
        for (MultipartFile file : multipartFile) {
            String filename = file.getOriginalFilename();
            FileCopyUtils.copy(file.getBytes(), new File(fileUpload + filename));
            Image image = new Image();
            image.setName(filename);
            image.setCake(cake);
            imageRepo.save(image);
        }
        return cake;
    }


    @Override
    public Page<Cake> findAll(CakeDto cakeDto, PaginateRequest paginateRequest) {
        Specification<Cake> specification = new CakeSpec(cakeDto);
        Pageable pageable = PageRequest.of(paginateRequest.getPage(), paginateRequest.getSize());
        return cakeRepo.findAll(specification, pageable);
    }


    @Override
    public Iterable<Cake> findAll() {
        return null;
    }

    @Override
    public Optional<Cake> findById(int id) {
        return cakeRepo.findById(id);
    }

    @Override
    public Cake save(Cake cake) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
