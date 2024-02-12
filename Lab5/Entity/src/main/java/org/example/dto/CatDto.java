package org.example.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.Owner;

import java.io.Serializable;

@Data
@Getter
@Setter
public class CatDto implements Serializable {
    private final Long id;
    private final String name;
    private final String birthDate;
    private final String breed;
    private final String colour;
    private final int tailLength;
    private final Owner owner;
}