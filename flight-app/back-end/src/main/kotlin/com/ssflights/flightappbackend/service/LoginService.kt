package com.ssflights.flightappbackend.service

import com.ssflights.flightappbackend.domain.User
import com.ssflights.flightappbackend.domain.dto.UserRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class LoginService(
    @Autowired
    private val userService: UserService,
    private val passwordEncoder: PasswordEncoder
) {
    private val jwt: JwtUtils = JwtUtils

    fun authenticate(user: UserRequest): ResponseEntity<Map<String, String>> {
        if (userService.userRepository.findByUsername(user.username).isPresent) {
            val userCheckPassword: User = userService.userRepository.findByUsername(user.username).get()

            return if (passwordEncoder.encode().matches(user.password, userCheckPassword.password)) {
                val token = jwt.generateToken(user.username)
                val responseBody = mapOf("token" to token)

                ResponseEntity.ok(responseBody)
            } else {
                ResponseEntity(HttpStatus.UNAUTHORIZED)
            }
        }

        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

}
