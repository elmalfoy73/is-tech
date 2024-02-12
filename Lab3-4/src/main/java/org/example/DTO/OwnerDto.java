package org.example.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class OwnerDto implements Serializable {
    private final Long id;
    private final String name;
    private final String birthDate;
    private final String email;
    private final String password;
}