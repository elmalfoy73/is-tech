package org.example.security;

import org.example.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;

    public Role save(Role role) {
        return repository.save(role);
    }

    public List<Role> getAll() {
        return repository.findAll();
    }

    public Role getById(long id) {
        return repository.getById(id);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
