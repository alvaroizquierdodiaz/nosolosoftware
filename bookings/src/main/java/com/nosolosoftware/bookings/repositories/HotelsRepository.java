package com.nosolosoftware.bookings.repositories;

import com.nosolosoftware.bookings.models.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelsRepository extends JpaRepository<Hotels, Integer> {
}