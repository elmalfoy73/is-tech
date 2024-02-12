package org.example;

import org.example.dto.FleaDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FleaMapper {
    public FleaDto entityToDto(Flea flea){
        return new FleaDto(flea.getId(), flea.getName(), flea.getCat());
    }

    public Flea dtoToEntity(FleaDto fleaDto){
        return new Flea(fleaDto.getId(), fleaDto.getName(), fleaDto.getCat());
    }

    public List<FleaDto> entitiesToDtos(List<Flea> fleas){
        List<FleaDto> fleaDtoList = null;
        for (Flea flea: fleas) {
            fleaDtoList.add(entityToDto(flea));
        }
        return fleaDtoList;
    }

    public List<Flea> DtosToEntitys(List<FleaDto> fleaDtoList){
        List<Flea> fleas = null;
        for (FleaDto fleaDto: fleaDtoList) {
            fleas.add(dtoToEntity(fleaDto));
        }
        return fleas;
    }
}
