package com.nosolosoftware.bookings.services;

import com.nosolosoftware.bookings.dtos.AvailabilityDTO;
import com.nosolosoftware.bookings.dtos.HotelsDTO;
import com.nosolosoftware.bookings.mappers.AvailabilityMapper;
import com.nosolosoftware.bookings.mappers.HotelsMapper;
import com.nosolosoftware.bookings.repositories.AvailabilityRepository;
import com.nosolosoftware.bookings.repositories.HotelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AvailabilityService {

  @Autowired
  private AvailabilityRepository availabilityRepository;

  @Autowired
  private HotelsRepository hotelsRepository;

  @Autowired
  private HotelsMapper hotelsMapper;

  @Autowired
  private AvailabilityMapper availabilityMapper;

  public ResponseEntity<List<HotelsDTO>> checkAvailability(Date dateFrom, Date dateTo) {
    try {
      var availabilityDateFrom = availabilityRepository.findById(dateFrom);
      var availabilityDateTo = availabilityRepository.findById(dateTo);
      List<HotelsDTO> hotelsDTOList = new ArrayList<>();

      if(availabilityDateFrom.isPresent() && availabilityDateTo.isPresent()){
        var hotelsDateFrom = hotelsMapper.hotelsToHotelsDTO(availabilityDateFrom.get().getHotels());
        var hotelsDateTo = hotelsMapper.hotelsToHotelsDTO(availabilityDateTo.get().getHotels());
        hotelsDTOList.add(hotelsDateFrom);
        hotelsDTOList.add(hotelsDateTo);

        return new ResponseEntity<List<HotelsDTO>>(hotelsDTOList, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<List<AvailabilityDTO>> openAvailability(Date dateFrom, Date dateTo, Integer roomsNumber, Integer idHotel) {
    try {
      var hotelFound = hotelsRepository.findById(idHotel);
      var availabilityDateFrom = availabilityRepository.findById(dateFrom);
      var availabilityDateTo = availabilityRepository.findById(dateTo);
      List<AvailabilityDTO> availabilityDTOS = new ArrayList<>();

      if(hotelFound.isPresent() && !availabilityDateFrom.isPresent() && !availabilityDateTo.isPresent()){

        // Se establecen las habitaciones para las fechas de entrada y salida
        availabilityDateFrom.get().setRooms(roomsNumber);
        availabilityDateTo.get().setRooms(roomsNumber);

        // Se guarda la disponibilidad para las fechas de entrada y salida
        var availabilityFromSaved = availabilityRepository.saveAndFlush(availabilityDateFrom.get());
        var availabilityToSaved = availabilityRepository.saveAndFlush(availabilityDateTo.get());

        // Se transforma a DTOs
        var availabilityFromDTO = availabilityMapper.availabilityEntityToDTO(availabilityFromSaved);
        var availabilityToDTO = availabilityMapper.availabilityEntityToDTO(availabilityToSaved);

        // Se guardan en la lista
        availabilityDTOS.add(availabilityFromDTO);
        availabilityDTOS.add(availabilityToDTO);

        return new ResponseEntity<List<AvailabilityDTO>>(availabilityDTOS, HttpStatus.OK);
      } else {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
