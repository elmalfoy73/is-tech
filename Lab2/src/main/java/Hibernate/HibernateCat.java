package Hibernate;

import Utils.Colour;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "cat")
public class HibernateCat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "birthDate")
    private String birthDate;
    @Column(name="breed")
    private String breed;
    @Column(name="colour")
    private String colour;
    @Column(name="tailLength")
    private int tailLength;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ownerId")
    private HibernateOwner owner;

    public HibernateCat(String name, LocalDate date, String breed, Colour colour, HibernateOwner owner) {
        this.name = name;
        this.birthDate = date.toString();
        this.breed = breed;
        this.colour = colour.toString();
        this.owner = owner;
    }

    public HibernateCat(String name, LocalDate date, String breed, Colour colour, int tailLength, HibernateOwner owner) {
        this.name = name;
        this.birthDate = date.toString();
        this.breed = breed;
        this.colour = colour.toString();
        this.tailLength = tailLength;
        this.owner = owner;
    }

}
