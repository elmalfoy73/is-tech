package org.example.mapper;

import org.example.DTO.OwnerDto;
import org.example.entity.Owner;

import java.util.List;

public class OwnerMapper {
    public OwnerDto entityToDto(Owner owner){
        return new OwnerDto(owner.getId(), owner.getName(), owner.getBirthDate(), owner.getEmail(), owner.getPassword());
    }

    public Owner dtoToEntity(OwnerDto ownerDto){
        return new Owner(ownerDto.getId(), ownerDto.getName(), ownerDto.getBirthDate(), ownerDto.getEmail(), ownerDto.getPassword());
    }

    public List<OwnerDto> entitysToDtos(List<Owner> owners){
        List<OwnerDto> ownerDtoList = null;
        for (Owner owner: owners) {
            ownerDtoList.add(entityToDto(owner));
        }
        return ownerDtoList;
    }

    public List<Owner> DtosToentitys(List<OwnerDto> ownerDtoList){
        List<Owner> owners = null;
        for (OwnerDto ownerDto: ownerDtoList) {
            owners.add(dtoToEntity(ownerDto));
        }
        return owners;
    }
}
