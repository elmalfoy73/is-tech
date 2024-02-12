package org.example.mapper;

import org.example.DTO.FleaDto;
import org.example.entity.Flea;

import java.util.List;

public class FleaMapper {
    public FleaDto entityToDto(Flea flea){
        return new FleaDto(flea.getId(), flea.getName(), flea.getCat());
    }

    public Flea dtoToEntity(FleaDto fleaDto){
        return new Flea(fleaDto.getId(), fleaDto.getName(), fleaDto.getCat());
    }

    public List<FleaDto> entitysToDtos(List<Flea> fleas){
        List<FleaDto> fleaDtoList = null;
        for (Flea flea: fleas) {
            fleaDtoList.add(entityToDto(flea));
        }
        return fleaDtoList;
    }

    public List<Flea> DtosToentitys(List<FleaDto> fleaDtoList){
        List<Flea> fleas = null;
        for (FleaDto fleaDto: fleaDtoList) {
            fleas.add(dtoToEntity(fleaDto));
        }
        return fleas;
    }
}
