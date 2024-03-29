package com.mandar.breweryapplication.mappers;

import com.mandar.breweryapplication.domain.Beer;
import com.mandar.breweryapplication.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDto);
}
