package org.example.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.model.Colour;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "cat")
@Schema(description = "Cat")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "birthdate")
    private String birthDate;

    @NotBlank
    @Column(name = "breed")
    private String breed;

    @NotBlank
    @Column(name = "colour")
    private String colour;

    @NotBlank
    @Column(name = "taillength")
    private int tailLength;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerid")
    private Owner owner;

    public Cat(String name, LocalDate date, String breed, Colour colour, Owner owner) {
        this.name = name;
        this.birthDate = date.toString();
        this.breed = breed;
        this.colour = colour.toString();
        this.owner = owner;
    }

    public Cat(String name, LocalDate date, String breed, Colour colour, int tailLength, Owner owner) {
        this.name = name;
        this.birthDate = date.toString();
        this.breed = breed;
        this.colour = colour.toString();
        this.tailLength = tailLength;
        this.owner = owner;
    }

    public Cat(Long id, String name, String date, String breed, String colour, int tailLength, Owner owner) {
        this.id = id;
        this.name = name;
        this.birthDate = date;
        this.breed = breed;
        this.colour = colour;
        this.tailLength = tailLength;
        this.owner = owner;
    }

}
