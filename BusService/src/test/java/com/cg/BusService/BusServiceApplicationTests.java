package com.cg.BusService;


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

import com.cg.dao.BusRepository;
import com.cg.exception.BusAlreadyExistsException;
import com.cg.exception.BusNotFoundException;
import com.cg.model.Bus;
import com.cg.service.BusServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BusServiceApplicationTests {

	@Autowired
	BusServiceImpl busService;
	
	@MockBean
	BusRepository busRepo;
	
	Bus bus;
	List<Bus> list;
	
	@Before
	public void setUp() throws Exception {
		bus= new Bus(100,"AP27AY3758","Sleeper","APSRTC","Pune","Mumbai",30,30,null,null);
		list = new ArrayList<Bus>();
		list.add(bus);
	}
	
	@Test
	public void getAllBusesTest()
	{

		when(busRepo.findAll()).thenReturn(Stream.
				of(new Bus(100,"AP27AY3758","Sleeper","APSRTC","Pune","Mumbai",30,30,null,null)).collect(Collectors.toList()));
		assertEquals(1,busService.getAllBusDetails().size());
	}
	
	@Test
	public void TestBusByIdTest() throws BusNotFoundException
	{
		Integer busId=100;
		Optional<Bus> opt=Optional.of(bus);
		when(busRepo.findById(busId)).thenReturn(opt);
		assertEquals(bus, busService.getBusDetailsByBusId(busId));
		
	}
	
	@Test(expected = BusNotFoundException.class)
	public void TestBusByIdNotFound_Failure() throws BusNotFoundException {
		int busId = 1;
		busService.getBusDetailsByBusId(busId);
	}
	
	@Test
	public void TestAddBus() throws BusAlreadyExistsException
	{
		Bus bus= new Bus(100,"AP27AY3758","Sleeper","APSRTC","Pune","Mumbai",30,30,null,null);
		when(busRepo.save(bus)).thenReturn(bus);
		assertEquals(bus, busService.addBus(bus));
		
	}
	
	
	@Test(expected = BusNotFoundException.class)
	public void testUpdateBusById_Success() throws BusNotFoundException {
		Integer busId=100;
		Bus bus= new Bus(100,"AP27AY3758","Sleeper","APSRTC","Pune","Mumbai",30,30,null,null);
		when(busRepo.existsById(bus.getBusId())).thenReturn(true);
		when(busRepo.save(bus)).thenReturn(bus);
		assertEquals(bus, busService.updateBusDetails(busId, bus));
	}
	
	@Test(expected = BusNotFoundException.class)
	public void testDeleteBus() throws BusNotFoundException{
		Integer busId=100;
		Bus bus= new Bus(100,"AP27AY3758","Sleeper","APSRTC","Pune","Mumbai",30,30,null,null);
		busService.deleteBusDetails(busId);
		verify(busRepo,times(1)).delete(bus);
		
	}

}
