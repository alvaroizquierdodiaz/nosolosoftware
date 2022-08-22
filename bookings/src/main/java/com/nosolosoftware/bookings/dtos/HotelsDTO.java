package com.nosolosoftware.bookings.dtos;

import com.nosolosoftware.bookings.models.Availability;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelsDTO implements Serializable {

  private int id;
  private String name;
  private int category;
  private List<Availability> availabilities = new ArrayList<>();

}
