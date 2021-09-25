package com.mandar.breweryapplication.service;

import com.mandar.breweryapplication.model.BeerDto;
import com.mandar.breweryapplication.model.BeerStyle;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BeerServiceImpl implements BeerService {

    @Override
    public BeerDto getBeerById(UUID beerId) {
        return BeerDto.builder().id(UUID.randomUUID())
                .beerName("Bud Light")
                .beerStyle(BeerStyle.CIDER)
            .build();
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return BeerDto.builder()
            .id(UUID.randomUUID())
            .build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        //TODO add real impl to update beer object
    }

    @Override
    public void deleteBeerById(UUID beerId) {
        log.debug("Deleting a beer...");
    }
}
