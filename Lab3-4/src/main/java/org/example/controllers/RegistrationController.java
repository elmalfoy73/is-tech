package org.example.controllers;

import jakarta.validation.Valid;
import org.example.entity.Owner;
import org.example.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/api/register")
    public ResponseEntity<?> register(@Valid @RequestBody Owner owner ){
        if (ownerRepository.findByEmail(owner.getEmail()) != null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        owner.setPassword(encoder.encode(owner.getPassword()));
        ownerRepository.save(owner);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
