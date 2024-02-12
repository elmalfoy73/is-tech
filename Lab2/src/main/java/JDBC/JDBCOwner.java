package JDBC;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class JDBCOwner {
    Long id;
    String name;
    LocalDate birthDate;
    public JDBCOwner(Long id, String name, LocalDate date){
        this.id = id;
        this.name = name;
        this.birthDate = date;
    }
}
