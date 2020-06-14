package com.cg.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.model.Bus;
@Repository

public interface BusRepository extends JpaRepository<Bus,Integer> {
public Bus findByBusIdAndDateOfJourney(Integer busId,Date dateOfJourney);
}
