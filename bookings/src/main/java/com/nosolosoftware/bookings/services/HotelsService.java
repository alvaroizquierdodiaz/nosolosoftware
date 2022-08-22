package com.nosolosoftware.bookings.services;

import com.nosolosoftware.bookings.dtos.HotelsDTO;
import com.nosolosoftware.bookings.mappers.HotelsMapper;
import com.nosolosoftware.bookings.models.Hotels;
import com.nosolosoftware.bookings.repositories.AvailabilityRepository;
import com.nosolosoftware.bookings.repositories.HotelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelsService {

  @Autowired
  private HotelsRepository hotelsRepository;

  @Autowired
  private HotelsMapper hotelsMapper;

  @Autowired
  private AvailabilityRepository availabilityRepository;

  public ResponseEntity<List<HotelsDTO>> getAllHotels() {
    var hotels = hotelsRepository.findAll();
    if (hotels == null || hotels.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } else {
      return new ResponseEntity<List<HotelsDTO>>(hotelsMapper.hotelsToHotelsDTOList(hotels), HttpStatus.OK);
    }
  }

  public ResponseEntity<HotelsDTO> createHotel(Hotels hotel) {
    try {
      return new ResponseEntity<>(hotelsMapper.hotelsToHotelsDTO(hotelsRepository.save(hotel)), HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
