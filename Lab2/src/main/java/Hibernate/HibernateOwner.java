package Hibernate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "owner")
public class HibernateOwner {
    @Id
    @GeneratedValue
    Long id;
    @Column(name = "name")
    String name;
    @Column(name = "birthDate")
    LocalDate birthDate;
    public HibernateOwner(String name, LocalDate date){
        this.name = name;
        this.birthDate = date;
    }

}
