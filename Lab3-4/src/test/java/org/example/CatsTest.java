package org.example;

import org.example.DTO.CatDto;
import org.example.DTO.OwnerDto;
import org.example.entity.Cat;
import org.example.entity.Owner;
import org.example.model.Colour;
import org.example.mapper.CatMapper;
import org.example.mapper.OwnerMapper;
import org.example.service.CatService;
import org.example.service.OwnerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;

import java.time.LocalDate;

public class CatsTest {
    @Mock
    OwnerService ownerService;

    @Mock
    CatService catService;

    MockitoSession session;

    @Test
    public void createCatAndOwner() {
        session = Mockito.mockitoSession()
                .initMocks(this)
                .startMocking();

        Owner owner = new Owner("Elizabeth", LocalDate.of(2003, 1, 15));
        OwnerMapper ownerMapper = new OwnerMapper();
        OwnerDto ownerDto = ownerMapper.entityToDto(owner);
        ownerService.save(ownerDto);

        Cat cat = new Cat("Zahar", LocalDate.of(2015, 6, 29), "british", Colour.Gray, owner);
        CatMapper catMapper = new CatMapper();
        CatDto catDto = catMapper.entityToDto(cat);
        catService.save(catDto);

        session.finishMocking();
    }
}
