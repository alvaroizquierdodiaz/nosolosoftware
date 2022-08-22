package com.nosolosoftware.bookings.controllers;

import com.nosolosoftware.bookings.dtos.AvailabilityDTO;
import com.nosolosoftware.bookings.dtos.HotelsDTO;
import com.nosolosoftware.bookings.services.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {

  @Autowired
  private AvailabilityService availabilityService;

  /**
   * Consulta de disponibilidad
   * @param dateFrom Fecha de entrada
   * @param dateTo Fecha de salida
   * @return Listado de hoteles con disponibilidad
   */
  @GetMapping("/check/{dateFrom}/{dateTo}")
  public ResponseEntity<List<HotelsDTO>> checkAvailability(@PathVariable("dateFrom") Date dateFrom, @PathVariable("dateTo") Date dateTo){
    return availabilityService.checkAvailability(dateFrom, dateTo);
  }

  /**
   * Abrir disponibilidad
   * @param dateFrom Fecha de entrada
   * @param dateTo Fecha de salida
   * @param roomsNumber Numero de habitaciones
   * @param idHotel Id hotel para abrir la disponibilidad
   * @return Disponibilidad para las fechas dadas
   */
  @PostMapping("/open/{dateFrom}/{dateTo}/{roomsNumber}/{idHotel}")
  public ResponseEntity<List<AvailabilityDTO>> openAvailability(@PathVariable("dateFrom") Date dateFrom, @PathVariable("dateTo") Date dateTo,
                                                                @PathVariable("roomsNumber") Integer roomsNumber, @PathVariable("idHotel") Integer idHotel){
    return availabilityService.openAvailability(dateFrom, dateTo, roomsNumber, idHotel);
  }

}
