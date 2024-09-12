package org.example.banbanh_be.service.impl;

import org.example.banbanh_be.model.User;
import org.example.banbanh_be.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private IUserRepo iUserRepo;

    public User checkUser(User user){
        List<User> accountList = iUserRepo.findAll();
        for (User acc : accountList){
            if (user.getUsername().equals(acc.getUsername()) && user.getPassword().equals(acc.getPassword())){
                return acc;
            }
        }

        return null;
    }
}
