package org.example.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springdoc.api.ErrorMessage;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "flea")
@Schema(description = "Flea")
public class Flea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @NotBlank
    Long id;

    @NotBlank
    String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "catid")
    private Cat cat;

    public Flea(String name, Cat cat) {
        this.name = name;
        this.cat = cat;
    }
    public Flea(Long id, String name, Cat cat) {
        this.id = id;
        this.name = name;
        this.cat = cat;
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(ChangeSetPersister.NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage(exception.getMessage()));
    }
}
