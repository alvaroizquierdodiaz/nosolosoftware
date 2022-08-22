package com.nosolosoftware.bookings.services;

import com.nosolosoftware.bookings.dtos.BookingsDTO;
import com.nosolosoftware.bookings.mappers.BookingsMapper;
import com.nosolosoftware.bookings.models.Availability;
import com.nosolosoftware.bookings.repositories.AvailabilityRepository;
import com.nosolosoftware.bookings.repositories.BookingsRepository;
import com.nosolosoftware.bookings.repositories.HotelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingsService {

  @Autowired
  private BookingsRepository bookingsRepository;

  @Autowired
  private AvailabilityRepository availabilityRepository;

  @Autowired
  private HotelsRepository hotelsRepository;

  @Autowired
  private BookingsMapper bookingsMapper;

  public ResponseEntity<BookingsDTO> getBookingById(Integer id) {
    var bookingFound = bookingsRepository.findById(id);
    if (bookingFound.isPresent()) {
      return new ResponseEntity<BookingsDTO>(bookingsMapper.bookingsEntityToDTO(bookingFound.get()), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  public ResponseEntity<Object> cancelBooking(Integer id) {
    try {
      var bookingFinded = bookingsRepository.findById(id);
      if (bookingFinded.isPresent()) {
        var dateFromBooked = bookingFinded.get().getFromDate();
        var dateToBooked = bookingFinded.get().getToDate();

        // Se modifica la disponibilidad de las habitaciones en la fecha de entrada y salida estableciendo el valor del atributo rooms a 0
        changeAvailabilityToDates(availabilityRepository.findById(dateFromBooked), availabilityRepository.findById(dateToBooked), true);

        // Una vez modificadas las habitaciones por fecha de entrada y salida, se cancela la reserva
        bookingsRepository.deleteById(id);
      }
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private void changeAvailabilityToDates(Optional<Availability> availabilityDateFromBookedFound,
                                         Optional<Availability> availabilityDateToBookedFound, boolean cancelRoom) {
    if (availabilityDateFromBookedFound.isPresent() && availabilityDateToBookedFound.isPresent()) {
      var availabilityDateFromBookedEntity = availabilityDateFromBookedFound.get();
      var availabilityDateToBookedEntity = availabilityDateToBookedFound.get();

      // Si se quiere cancelar una reserva, hay que cambiar la disponibilidad de la tabla availability (valor 0). En caso contrario
      // se establece el numero de habitaciones correspondientes (cada vez que se haga una llamada a reservar se añade una habitacion)
      if(cancelRoom){
        availabilityDateFromBookedEntity.setRooms(0);
        availabilityDateToBookedEntity.setRooms(0);
      } else {
        var availabilityFrom = availabilityDateFromBookedEntity.getRooms();
        var availabilityTo = availabilityDateFromBookedEntity.getRooms();

        if(availabilityFrom >= 1 && availabilityTo >= 1){
          availabilityDateFromBookedEntity.setRooms(availabilityFrom++);
          availabilityDateToBookedEntity.setRooms(availabilityTo++);
        } else {
          availabilityDateFromBookedEntity.setRooms(1);
          availabilityDateToBookedEntity.setRooms(1);
        }
      }
      // Se guarda la disponibilidad de la habitacion en ambas fechas
      availabilityRepository.saveAndFlush(availabilityDateFromBookedEntity);
      availabilityRepository.saveAndFlush(availabilityDateToBookedEntity);
    }
  }

  public ResponseEntity<List<BookingsDTO>> getBookingsByHotelAndDates(Integer idHotel, Date dateFrom, Date dateTo) {
    var hotelFound = hotelsRepository.findById(idHotel);
    if (hotelFound.isPresent()) {
      var bookingsFound = bookingsRepository.findByIdHotelAndDates(hotelFound.get().getAvailabilities().stream().findFirst().get().getHotels().getId()
              , dateFrom, dateTo);
      if (bookingsFound == null || bookingsFound.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
        return new ResponseEntity<List<BookingsDTO>>(bookingsMapper.bookingsToBookingsDTOList(bookingsFound), HttpStatus.OK);
      }
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  public ResponseEntity<List<BookingsDTO>> createBooking(BookingsDTO bookingsDTO) {
      var bookingEntity = bookingsMapper.bookingsDTOToEntity(bookingsDTO);
      var bookingFound = bookingsRepository.findByIdHotelAndDates(bookingEntity.getHotels().getAvailabilities().stream().findFirst().get().getHotels().getId(),
              bookingEntity.getFromDate(), bookingEntity.getToDate());

    // Se modifica la disponibilidad de las habitaciones en la fecha de entrada y salida estableciendo el valor del atributo rooms al correspondiente
    changeAvailabilityToDates(availabilityRepository.findById(bookingEntity.getFromDate()),
            availabilityRepository.findById(bookingEntity.getToDate()), false);

    // Se comprueba que no exista una reserva para esas fechas
    if (bookingFound == null || bookingFound.isEmpty()) {
        // Se hace la reserva
        var newBooking = bookingsRepository.saveAll(bookingFound);
        return new ResponseEntity<List<BookingsDTO>>(bookingsMapper.bookingsToBookingsDTOList(newBooking), HttpStatus.CREATED);
      } else {
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
}
