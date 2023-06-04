package com.ssflights.flightappbackend.api.controller

import com.ssflights.flightappbackend.domain.dto.BookFlightPayload
import com.ssflights.flightappbackend.domain.dto.FlightDto
import com.ssflights.flightappbackend.service.FlightService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/api/flights"])
@CrossOrigin(origins = ["http://localhost:4200"])
class FlightController(
    private val flightService: FlightService
) {
    @PostMapping()
    fun userBooksFlight(@RequestBody payload: BookFlightPayload): ResponseEntity<String> {
        return flightService.bookingLogic(payload)
    }

    @PostMapping("/addflight")
    fun addFlight(@RequestBody request: FlightDto): ResponseEntity<String> {
        return flightService.saveFlight(request)
    }

    @PostMapping("/mytrips")
    fun userSavesFlightToMyTrips(@RequestBody payload: BookFlightPayload): ResponseEntity<String> {
        return flightService.saveFlightToMyTrips(payload)
    }
}
