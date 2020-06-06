package com.example.TaxiApp.Service.ServicesImpl;

import com.example.TaxiApp.Entity.User;
import com.example.TaxiApp.Repository.UserRepository;
import com.example.TaxiApp.needed_classes.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userService = userRepository.findByUsername(username);
        if (userService == null)
            throw new UsernameNotFoundException("There is no user with username "+ username);
        return new UserService(userService);
    }
}
