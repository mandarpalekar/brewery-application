package com.mandar.breweryapplication.controller;

import com.mandar.breweryapplication.model.BeerDto;
import com.mandar.breweryapplication.service.BeerService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/beer")
@RestController
@RequiredArgsConstructor
@Slf4j
public class BeerController {

    private final BeerService beerService;

    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {
        log.debug("BeerDto object: {} is ", beerService.getBeerById(beerId));
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    /** Create a new beer dto **/
    @PostMapping
    public ResponseEntity<BeerDto> saveBeerDto(@RequestBody BeerDto beerDto) {
        BeerDto beerDtoSave = beerService.saveNewBeer(beerDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        //TODO: add hostname to url
        httpHeaders.add("Location", "/api/v1/beer/" + beerDtoSave.getId().toString());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    /** update beer dto **/
    @PutMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> updateBeerDto(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {
        beerService.updateBeer(beerId, beerDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /** delete a beer **/
    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeerDto(@PathVariable("beerId") UUID beerId) {
        beerService.deleteBeerById(beerId);
    }
}
