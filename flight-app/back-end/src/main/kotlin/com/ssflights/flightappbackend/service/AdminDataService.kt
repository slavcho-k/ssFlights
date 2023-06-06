package com.ssflights.flightappbackend.service

import com.ssflights.flightappbackend.domain.dto.AdminDataDto
import com.ssflights.flightappbackend.repository.BookingRepository
import com.ssflights.flightappbackend.repository.FlightRepository
import com.ssflights.flightappbackend.repository.PaymentRepository
import com.ssflights.flightappbackend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.floor

@Service
class AdminDataService(
    @Autowired
    private val flightRepository: FlightRepository,
    private val paymentRepository: PaymentRepository,
    private val usersRepository: UserRepository,
    private val bookingRepository: BookingRepository,
) {
    fun getAdminDataStats(): ResponseEntity<AdminDataDto> {
        val adminDto = AdminDataDto(
            getTotalMoneySpent(),
            getAvgFlightCost(),
            totalFlightsBooked(),
            findMostPopularDestination(),
            getFlightsInLastMonth(),
            avgFlightTime(),
            getMostExpensiveFlight(),
            getTotalUsers()
        )
        return ResponseEntity.ok(adminDto)
    }

    fun getTotalMoneySpent(): Double = String.format("%.2f", paymentRepository.findAll().sumOf { it.amount }).toDouble()

    fun getAvgFlightCost(): Double =
        String.format("%.2f", getTotalMoneySpent() / paymentRepository.count().toDouble()).toDouble()

    fun totalFlightsBooked(): Long = bookingRepository.count()

    fun findMostPopularDestination(): String = flightRepository.findMostPopularDestination().orElse("No destinations!")

    fun getFlightsInLastMonth(): Int =
        flightRepository.findAll().map { f -> f.boardingDate.subSequence(5, 7) == getCurrentMonth() }.count()

    fun avgFlightTime(): String = convertToHoursAndMinutes(
        flightRepository.findAll().map { f ->
            f.flightTime.split("[hm]".toRegex()).let { parts -> (parts[0].toInt() * 60) + parts[1].toInt() }
        }.average()
    )

    fun getMostExpensiveFlight(): Double = flightRepository.findAll().maxOfOrNull { f -> f.price } ?: 0.0

    fun getTotalUsers(): Long = usersRepository.count()

    fun getCurrentMonth(): String = LocalDate.now().format(DateTimeFormatter.ofPattern("MM"))

    fun convertToHoursAndMinutes(totalMinutes: Double): String =
        "${(totalMinutes / 60).toInt()}h${(totalMinutes % 60).toInt()}m"

}