package MyBatis;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MyBatisOwner {
    Long id;
    String name;
    LocalDate birthDate;
    public MyBatisOwner(Long id, String name, LocalDate date){
        this.id = id;
        this.name = name;
        this.birthDate = date;
    }
}
