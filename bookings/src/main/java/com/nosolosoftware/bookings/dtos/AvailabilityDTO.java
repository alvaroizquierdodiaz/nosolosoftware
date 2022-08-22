package com.nosolosoftware.bookings.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityDTO implements Serializable {

  private Date date;
  private int rooms;
  private HotelsDTO hotel;

}
