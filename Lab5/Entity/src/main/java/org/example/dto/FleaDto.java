package org.example.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.Cat;

import java.io.Serializable;

@Data
@Getter
@Setter
public class FleaDto implements Serializable {
    private final Long id;
    private final String name;
    private final Cat cat;
}