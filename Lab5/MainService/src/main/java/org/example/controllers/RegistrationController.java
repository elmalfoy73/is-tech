package org.example.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.Owner;
import org.example.other.OwnerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegistrationController {
    private final OwnerRepository ownerRepository;

    private final PasswordEncoder encoder;

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
