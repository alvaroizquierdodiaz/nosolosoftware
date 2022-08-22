package com.nosolosoftware.bookings.mappers;

import com.nosolosoftware.bookings.dtos.BookingsDTO;
import com.nosolosoftware.bookings.models.Bookings;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingsMapper {

  BookingsDTO bookingsEntityToDTO(Bookings bookings);
  Bookings bookingsDTOToEntity(BookingsDTO bookingsDTO);

  List<BookingsDTO> bookingsToBookingsDTOList(List<Bookings> bookingsList);
  List<Bookings> bookingsDTOToBookingsList(List<BookingsDTO> bookingsDTOList);

}
