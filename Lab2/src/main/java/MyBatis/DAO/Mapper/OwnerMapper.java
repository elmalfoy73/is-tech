package MyBatis.DAO.Mapper;

import MyBatis.MyBatisOwner;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OwnerMapper {
    @Insert("INSERT INTO owner (id, name, birthDate) VALUES (#{id}, #{name}, #{birthDate})")
    void save(MyBatisOwner owner);
    @Delete("DELETE FROM owner WHERE id = #{id}")
    void deleteById(long id);
    @Delete("DELETE FROM owner")
    void deleteAll();
    @Update("UPDATE owner SET name = #{entity.getName()}, birthDate = {entity,getBirthDate()} WHERE id = {entity.id}")
    MyBatisOwner update(MyBatisOwner entity);
    @Select("SELECT * FROM owner WHERE id = #{id}")
    MyBatisOwner getById(Long id);
    @Select("SELECT * FROM cat")
    List<MyBatisOwner> getAll();
}
