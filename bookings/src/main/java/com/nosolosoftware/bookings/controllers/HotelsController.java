package com.nosolosoftware.bookings.controllers;

import com.nosolosoftware.bookings.dtos.HotelsDTO;
import com.nosolosoftware.bookings.models.Hotels;
import com.nosolosoftware.bookings.services.HotelsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelsController {

  @Autowired
  private HotelsService hotelsService;

  /**
   * Consulta de hoteles
   * @return Listado de hoteles existentes
   */
  @GetMapping("/getAll")
  public ResponseEntity<List<HotelsDTO>> getAllHotels() {
    return hotelsService.getAllHotels();
  }

  /**
   * Metodo adicional para crear un hotel
   * @param hotel
   * @return Devuelve el hotel creado
   */
  @PostMapping("/create")
  public ResponseEntity<HotelsDTO> createHotel(@RequestBody Hotels hotel) {
    return hotelsService.createHotel(hotel);
  }
}
