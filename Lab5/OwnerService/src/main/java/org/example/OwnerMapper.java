package org.example;

import org.example.dto.OwnerDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OwnerMapper {
    public OwnerDto entityToDto(Owner owner){
        return new OwnerDto(owner.getId(), owner.getName(), owner.getBirthDate(), owner.getEmail(), owner.getPassword());
    }

    public Owner dtoToEntity(OwnerDto ownerDto){
        return new Owner(ownerDto.getId(), ownerDto.getName(), ownerDto.getBirthDate(), ownerDto.getEmail(), ownerDto.getPassword());
    }

    public List<OwnerDto> entitiesToDtos(List<Owner> owners){
        List<OwnerDto> ownerDtoList = null;
        for (Owner owner: owners) {
            ownerDtoList.add(entityToDto(owner));
        }
        return ownerDtoList;
    }

    public List<Owner> DtosToEntities(List<OwnerDto> ownerDtoList){
        List<Owner> owners = null;
        for (OwnerDto ownerDto: ownerDtoList) {
            owners.add(dtoToEntity(ownerDto));
        }
        return owners;
    }
}
