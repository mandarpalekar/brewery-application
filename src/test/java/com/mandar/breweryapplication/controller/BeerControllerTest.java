package com.mandar.breweryapplication.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mandar.breweryapplication.model.BeerDto;
import com.mandar.breweryapplication.service.BeerService;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.hamcrest.Matchers.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BeerController.class)
public class BeerControllerTest {

  @MockBean
  BeerService beerService;

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  BeerDto validBeerDto;

  @BeforeEach
  public void setup() {
    validBeerDto = BeerDto.builder()
        .id(UUID.randomUUID())
        .beerName("Test Beer")
        .beerType("Ale")
        .upc(123456789L)
        .build();
  }

  @Test
  public void getBeerTest() throws Exception {
    given(beerService.getBeerById(any(UUID.class))).willReturn(validBeerDto);

    mockMvc.perform(get("/api/v1/beer/" + validBeerDto.getId().toString()).accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id", is(validBeerDto.getId().toString())))
        .andExpect(jsonPath("$.beerName", is(validBeerDto.getBeerName())));
  }

  @Test
  public void saveBeerDtoTest() throws Exception {
    BeerDto beerDto  = validBeerDto;
    beerDto.setId(null);
    BeerDto beerDtoToSave = BeerDto.builder().id(UUID.randomUUID()).beerName("New Beer").build();
    String jsonBeerDto = objectMapper.writeValueAsString(beerDto);
    given(beerService.saveNewBeer(any())).willReturn(beerDtoToSave);

    mockMvc.perform(post("/api/v1/beer/")
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonBeerDto))
        .andExpect(status().isCreated());
  }
}
