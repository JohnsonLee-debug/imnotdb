package com.imnotdb.Mapper;

import com.imnotdb.Entity.Name;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NameMapper {
    Name getNameByNconst(String nconst);
    List<Name> getNameByJobAndName(@Param("name") String Name, @Param("job") String Job);
    default List<Name> getDirectorByName(String name){
        return getNameByJobAndName(name, "director");
    }
    default List<Name> getActorByName(String name){
        return getNameByJobAndName(name, "actor actress");
    }
    default List<Name> getWriterByName(String name){
        return getNameByJobAndName(name, "writer");
    }
}
