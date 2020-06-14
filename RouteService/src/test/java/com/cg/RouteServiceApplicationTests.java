package com.cg;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.dao.RouteRepository;
import com.cg.exception.RouteAlreadyExistsException;
import com.cg.exception.RouteNotFoundException;
import com.cg.model.Route;
import com.cg.service.RouteService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RouteServiceApplicationTests {

	@Autowired
	RouteService routeService;
	
	@MockBean
	RouteRepository routeRepo;
	
	Route route;
	List<Route> list;
	
	@Before
	public void setUp() throws Exception {
		route= new Route(100,1001,"Chennai","Banglore",800,null,null);
		list = new ArrayList<Route>();
		list.add(route);
	}
	
	
	@Test
	public void TestGetAllBuses()
	{

		when(routeRepo.findAll()).thenReturn(Stream.
				of(new Route(100,1001,"Chennai","Banglore",800,null,null)).collect(Collectors.toList()));
		assertEquals(1,routeService.getAllRouteDetails().size());
	}
	
	@Test
	public void TestRouteByIdTest() throws RouteNotFoundException
	{
		Integer routeId=100;
		Optional<Route> opt=Optional.of(route);
		when(routeRepo.findById(routeId)).thenReturn(opt);
		assertEquals(route, routeService.getRouteDetailsByRouteId(routeId));
		
	}
	
	@Test(expected = RouteNotFoundException.class)
	public void TestRouteByIdNotFound_Failure() throws RouteNotFoundException {
		Integer routeId = 1;
		routeService.getRouteDetailsByRouteId(routeId);
	}
	
	@Test
	public void TestAddBus() throws RouteAlreadyExistsException
	{
		Route route= new Route(100,1001,"Chennai","Banglore",800,null,null);
		when(routeRepo.save(route)).thenReturn(route);
		assertEquals(route, routeService.addRoute(route));
		
	}
	
	
	@Test(expected = RouteNotFoundException.class)
	public void testUpdateRouteById_Success() throws RouteNotFoundException {
		Integer routeId=100;
		Route route= new Route(100,1001,"Chennai","Banglore",800,null,null);
		when(routeRepo.existsById(route.getBusId())).thenReturn(true);
		when(routeRepo.save(route)).thenReturn(route);
		assertEquals(route, routeService.updateRouteDetails(routeId, route));
	}
	
	@Test(expected = RouteNotFoundException.class)
	public void testDeleteRoute() throws RouteNotFoundException{
		Integer routeId=100;
		route= new Route(100,1001,"Chennai","Banglore",800,null,null);
		routeService.deleteRoute(routeId);
		verify(routeRepo,times(1)).delete(route);
		
	}


}
