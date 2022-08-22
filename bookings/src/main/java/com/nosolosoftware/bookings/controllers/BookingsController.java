package com.nosolosoftware.bookings.controllers;

import com.nosolosoftware.bookings.dtos.BookingsDTO;
import com.nosolosoftware.bookings.services.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingsController {

  @Autowired
  private BookingsService bookingsService;

  /**
   * Reservar una habitacion
   * @param bookingsDTO  Dado un hotel, unas fechas de entrada y salida y un email, se creara una reserva.
   * @return Listado de reservas
   */
  @PostMapping("/create")
  public ResponseEntity<List<BookingsDTO>> createBooking(@RequestBody BookingsDTO bookingsDTO) {
    return bookingsService.createBooking(bookingsDTO);

  }

  /**
   * Consulta de reservas
   * @param idHotel Id del hotel
   * @param dateFrom Fecha de entrada
   * @param dateTo Fecha de salida
   * @return Listado de reservas
   */
  @GetMapping("/get/{idHotel}/{dateFrom}/{dateTo}")
  public ResponseEntity<List<BookingsDTO>> getBookingsByHotelAndDates(@PathVariable("idHotel") Integer idHotel,
                                                                      @PathVariable("dateFrom") Date dateFrom,
                                                                      @PathVariable("dateTo") Date dateTo) {
    return bookingsService.getBookingsByHotelAndDates(idHotel, dateFrom, dateTo);
  }

  /**
   * Obtener reserva
   * @param id Id de la reserva
   * @return Datos de la reserva
   */
  @GetMapping("/get/{id}")
  public ResponseEntity<BookingsDTO> getBookingById(@PathVariable("id") Integer id) {
    return bookingsService.getBookingById(id);
  }

  /**
   * Cancelar reserva
   * @param id Id de la reserva
   * @return Se devuelve el codigo de respuesta correspondiente
   */
  @DeleteMapping("/cancel/{id}")
  public ResponseEntity<Object> cancelBooking(@PathVariable("id") Integer id) {
    return bookingsService.cancelBooking(id);
  }

}
