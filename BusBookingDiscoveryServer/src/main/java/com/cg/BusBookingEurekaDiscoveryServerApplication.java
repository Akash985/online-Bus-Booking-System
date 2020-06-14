package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class BusBookingEurekaDiscoveryServerApplication 
{

	public static void main(String[] args)
	{
		SpringApplication.run(BusBookingEurekaDiscoveryServerApplication.class, args);
		System.out.println(" For BusBooking Eureka discovery server started on 8761 port");
	}

}
