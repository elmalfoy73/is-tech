package org.example.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.entity.Cat;

import java.io.Serializable;

/**
 * A DTO for the {@link org.example.entity.Flea} entity
 */
@Data
@Getter
@Setter
public class FleaDto implements Serializable {
    private final Long id;
    private final String name;
    private final Cat cat;
}