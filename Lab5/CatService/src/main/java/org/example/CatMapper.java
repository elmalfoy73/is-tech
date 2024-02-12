package org.example;

import org.example.dto.CatDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatMapper {
    public CatDto entityToDto(Cat cat){
        return new CatDto(cat.getId(), cat.getName(), cat.getBirthDate(), cat.getBreed(), cat.getColour(), cat.getTailLength(), cat.getOwner());
    }

    public Cat dtoToEntity(CatDto catDto){
        return new Cat(catDto.getId(), catDto.getName(), catDto.getBirthDate(), catDto.getBreed(), catDto.getColour(), catDto.getTailLength(), catDto.getOwner());
    }

    public List<CatDto> entitiesToDtos(List<Cat> cats){
        List<CatDto> catDtoList = null;
        for (Cat owner: cats) {
            catDtoList.add(entityToDto(owner));
        }
        return catDtoList;
    }

    public List<Cat> DtosToEntities(List<CatDto> catDtoList){
        List<Cat> cats = null;
        for (CatDto ownerDto: catDtoList) {
            cats.add(dtoToEntity(ownerDto));
        }
        return cats;
    }

}
