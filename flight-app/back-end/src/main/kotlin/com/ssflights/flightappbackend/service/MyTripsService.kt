package com.ssflights.flightappbackend.service

import com.ssflights.flightappbackend.domain.Booking
import com.ssflights.flightappbackend.domain.Flight
import com.ssflights.flightappbackend.domain.MyTrip
import com.ssflights.flightappbackend.repository.BookingRepository
import com.ssflights.flightappbackend.repository.FlightRepository
import com.ssflights.flightappbackend.repository.MyTripRepository
import com.ssflights.flightappbackend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class MyTripsService(
    @Autowired
    private val myTripRepository: MyTripRepository,
    private val flightRepository: FlightRepository,
    private val userRepository: UserRepository,
    private val bookingRepository: BookingRepository
) {
    fun getFlightsByUserId(username: String): MutableList<Flight> {
        val myTrips = findAllByUserId(username)
        val flights = mutableListOf<Flight>()

        myTrips.forEach { myTrip ->
            val flight = flightRepository.findById(myTrip.get().flight!!.flightId!!.toInt()).orElse(null)
            flight?.let { flights.add(it) }
        }

        return flights
    }

    fun getBookedFlights(username: String): List<Flight> {
        val bookedFlights = findUserByUsername(username)
        val flights = mutableListOf<Flight>()

        bookedFlights.forEach { myBooking ->
            val flight = flightRepository.findById(myBooking.get().flight!!.flightId!!.toInt()).orElse(null)
            flight?.let { flights.add(it) }
        }

        return flights
    }

    fun deleteMethod(username: String, flightId: Long): ResponseEntity<Void> {
        val userId = findUserIdByUsername(username)

        return myTripRepository.findByFlightFlightIdAndUserUserId(flightId, userId).map { myTrip ->
            myTripRepository.delete(myTrip)
            ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        }.orElse(ResponseEntity.notFound().build())
    }

    private fun findUserByUsername(username: String): MutableList<Optional<Booking>> {
        return bookingRepository.findAllyByUserUserId(findUserIdByUsername(username))
    }

    private fun findAllByUserId(username: String): MutableList<Optional<MyTrip>> {
        return myTripRepository.findAllyByUserUserId(findUserIdByUsername(username))
    }

    private fun findUserIdByUsername(username: String): Long {
        return userRepository.findByUsername(username).get().userId!!;
    }
}