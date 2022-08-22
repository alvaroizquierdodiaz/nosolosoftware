package com.nosolosoftware.bookings.mappers;

import com.nosolosoftware.bookings.dtos.AvailabilityDTO;
import com.nosolosoftware.bookings.models.Availability;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AvailabilityMapper {

  AvailabilityDTO availabilityEntityToDTO(Availability availability);
  Availability availabilityDTOToEntity(AvailabilityDTO availabilityDTO);

}
