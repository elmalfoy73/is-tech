package org.example;

import lombok.AllArgsConstructor;
import org.example.dto.CatDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@AllArgsConstructor
public class CatService {
    private final CatRepository catRepository;
    private final CatMapper catMapper;

    public CatDto save(CatDto catDto) {
        catRepository.save(catMapper.dtoToEntity(catDto));
        return catDto;
    }

    public void deleteById(@PathVariable long id){
        catRepository.deleteById(id);
    }

    public void deleteAll(){
        catRepository.deleteAll();
    }

    public CatDto getById(@PathVariable long id){
        return catMapper.entityToDto(catRepository.getById(id));
    }

    public List<CatDto> getAll(){
        return catMapper.entitiesToDtos(catRepository.findAll());
    }

    public List<CatDto> getAllByOwnerId(Long id){
        return catMapper.entitiesToDtos(catRepository.getAllByOwnerId(id));
    }

    public List<CatDto> getAllByName(String name){
        return catMapper.entitiesToDtos(catRepository.getAllByName(name));
    }


}
