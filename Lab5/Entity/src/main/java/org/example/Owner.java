package org.example;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owner")
@Schema(description = "Owner")
public class Owner {
    @Id
    @GeneratedValue
    Long id;

    @NotBlank
    @Column(name = "name")
    String name;

    @NotBlank
    @Column(name = "birthdate")
    String birthDate;

    @NotBlank
    String email;

    @NotBlank
    String password;

    public Owner(String name, LocalDate date){
        this.name = name;
        this.birthDate = date.toString();
    }

    public Owner(Long id, String name, String date){
        this.id = id;
        this.name = name;
        this.birthDate = date;
    }

}
