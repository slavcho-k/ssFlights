package com.ssflights.flightappbackend.api.controller

import com.ssflights.flightappbackend.domain.Flight
import com.ssflights.flightappbackend.service.MyTripsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/api/mytrips"])
@CrossOrigin(origins = ["http://localhost:4200"])
class MyTripController(
    @Autowired
    private val myTripsService: MyTripsService
) {
    @GetMapping("/{username}")
    fun getFlightsByUserId(@PathVariable username: String): List<Flight> {
        return myTripsService.getFlightsByUserId(username)
    }

    @GetMapping("/bookings/{username}")
    fun getBookedFlights(@PathVariable username: String): List<Flight> {
        return myTripsService.getBookedFlights(username);
    }

    @DeleteMapping("/delete/{flightId}/{username}")
    fun deleteMethod(@PathVariable flightId: Long, @PathVariable username:String): ResponseEntity<Void> {
        return myTripsService.deleteMethod(username, flightId)
    }
 }
