package com.example.TaxiApp.Service.ServicesImpl;

import com.example.TaxiApp.Entity.User;
import com.example.TaxiApp.Repository.UserRepository;
import com.example.TaxiApp.Service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String user) {
        return userRepository.findByUsername(user);
    }
}
