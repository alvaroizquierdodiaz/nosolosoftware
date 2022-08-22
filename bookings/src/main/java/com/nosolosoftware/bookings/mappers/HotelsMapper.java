package com.nosolosoftware.bookings.mappers;

import com.nosolosoftware.bookings.dtos.HotelsDTO;
import com.nosolosoftware.bookings.models.Hotels;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface HotelsMapper {

  List<HotelsDTO> hotelsToHotelsDTOList(List<Hotels> hotels);
  List<Hotels> hotelsDTOToHotelsList(List<HotelsDTO> hotelsDTO);


  HotelsDTO hotelsToHotelsDTO(Hotels hotels);

  Hotels hotelsDTOToHotels(HotelsDTO hotelsDTO);

}
