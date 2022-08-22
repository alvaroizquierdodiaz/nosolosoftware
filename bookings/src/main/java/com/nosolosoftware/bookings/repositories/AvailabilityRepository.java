package com.nosolosoftware.bookings.repositories;

import com.nosolosoftware.bookings.models.Availability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface AvailabilityRepository extends JpaRepository<Availability, Date> {

}