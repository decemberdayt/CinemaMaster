package pl.cinema.springboot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.lang.Nullable;
import pl.cinema.springboot.model.Actor;

import java.util.List;

@Mapper
public interface ActorMapper {

    @Insert("INSERT INTO ANONYMOUS.ACTOR (IDACTOR, NAME, SURNAME, COUNTRY, APHOTOURL) VALUES (#{idActor},#{name},#{surname}, #{country},#{aPhotoUrl})")
    void insert(Actor actor);

    @Select("SELECT * FROM ANONYMOUS.ACTOR WHERE IDACTOR = #{idActor}")
    Actor findByIdActor(int idActor);

    @Select("SELECT * FROM ANONYMOUS.ACTOR")
    List<Actor> findAll();

    @Select("SELECT * FROM ANONYMOUS.ACTOR WHERE NAME||SURNAME LIKE '%'||#{name}||'%'")
    Actor findByNameActor(String name);

    @Select("SELECT * FROM ANONYMOUS.ACTOR WHERE ROWNUM = 1 ORDER BY IDACTOR DESC")
    Actor findMaxId();

    @Select("SELECT * FROM ANONYMOUS.ACTOR WHERE NAME = #{name} AND SURNAME = #{surname} AND COUNTRY = #{country} AND ROWNUM = 1")
    @Nullable Actor checkIfActorExists(Actor actor);
}
