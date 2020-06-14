package com.cg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.model.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route,Integer> {

	List<Route> findAllByBoardingPointAndDroppingPoint(String boardingPoint, String droppingPoint);

}
