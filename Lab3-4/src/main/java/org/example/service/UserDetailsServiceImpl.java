package org.example.service;

import org.example.entity.Owner;
import org.example.entity.User;
import org.example.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        Owner owner = ownerRepository.findByEmail(email);

        if (owner == null) {
            throw new UsernameNotFoundException("Not found: " + email);
        }

        return new User(owner);
    }
}
