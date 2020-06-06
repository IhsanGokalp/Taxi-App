package com.example.TaxiApp.Service;

import com.example.TaxiApp.Entity.User;

public interface UserService {
    User save(User user);
    User findByUsername(String username);
}
