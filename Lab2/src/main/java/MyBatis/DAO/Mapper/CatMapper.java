package MyBatis.DAO.Mapper;

import MyBatis.MyBatisCat;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CatMapper {
    @Insert("INSERT INTO cat (id, name, birthDate, breed, colour, ownerId) VALUES (#{id}, #{name}, #{birthDate}, #{breed}, #{colour}, #{ownerId})")
    void save(MyBatisCat cat);

    @Delete("DELETE FROM cat WHERE id = #{id}")
    void deleteById(long id);
    @Delete("DELETE FROM cat")
    void deleteAll();
    @Update("UPDATE cat SET name = #{entity.getName()}, birthDate = {entity,getBirthDate()}, breed = {entity.getBreed()}, colour = {entity.getColour()} WHERE id = {entity.id}")
    MyBatisCat update(MyBatisCat entity);
    @Select("SELECT * FROM cat WHERE id = #{id}")
    MyBatisCat getById(Long id);
    @Select("SELECT * FROM cat")
    List<MyBatisCat> getAll();

    @Select("SELECT * FROM cat WHERE ownerId = #{id}")
    List<MyBatisCat> getAllByOwnerId(Long id);

}
