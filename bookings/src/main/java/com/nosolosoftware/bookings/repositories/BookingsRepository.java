package com.nosolosoftware.bookings.repositories;

import com.nosolosoftware.bookings.models.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BookingsRepository extends JpaRepository<Bookings, Integer> {

  @Query(value = "SELECT b from bookings b WHERE b.hotels.id=:idHotel AND b.fromDate=:fromDate AND b.toDate=:toDate", nativeQuery = true)
  List<Bookings> findByIdHotelAndDates(@Param("idHotel") Integer idHotel, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

}