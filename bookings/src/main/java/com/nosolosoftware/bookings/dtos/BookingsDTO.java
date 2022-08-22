package com.nosolosoftware.bookings.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingsDTO implements Serializable {

  private int id;
  private HotelsDTO hotel;
  private Date date_from;
  private Date date_to;
  private String email;

}
