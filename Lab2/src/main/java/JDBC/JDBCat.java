package JDBC;

import Utils.Colour;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class JDBCat{
   private Long id;
   private String name;
   private LocalDate birthDate;
   private String breed;
   private String colour;
   private Long ownerId;

   public JDBCat(Long id, String name, LocalDate date, String breed, Colour colour, Long ownerId) {
       this.id = id;
       this.name = name;
       this.birthDate = date;
       this.breed = breed;
       this.colour = colour.toString();
       this.ownerId = ownerId;
   }
}
