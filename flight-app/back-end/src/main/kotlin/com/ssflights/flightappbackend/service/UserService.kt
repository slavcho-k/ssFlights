package com.ssflights.flightappbackend.service

import com.ssflights.flightappbackend.domain.Role
import com.ssflights.flightappbackend.domain.User
import com.ssflights.flightappbackend.exceptions.UsernameNotFoundException
import com.ssflights.flightappbackend.repository.RoleRepository
import com.ssflights.flightappbackend.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    @Autowired
    var userRepository: UserRepository,
    var passwordEncoder: BCryptPasswordEncoder,
    var roleRepository: RoleRepository
) : UserDetailsService {
    fun signUp(user: User): ResponseEntity<String> {
        val userExistsByEmail = userRepository.findByEmail(user.email.orEmpty()).isPresent
        val userExistsByUsername = userRepository.findByUsername(user.username).isPresent

        if (userExistsByEmail) {
            return ResponseEntity.badRequest().body("email already taken")
        } else if (userExistsByUsername) {
            return ResponseEntity.badRequest().body("username already taken")
        }

        val roleUser = roleRepository.findByAuthority("USER")!!.get()
        val set = mutableListOf<Role?>()
        set.add(roleUser)

        val encodedPassword = passwordEncoder.encode(user.password)
        val savedUser =
            User(user.username, encodedPassword, user.surname, user.email, user.name, set)

        userRepository.save(savedUser)

        val token = UUID.randomUUID().toString()

        return ResponseEntity.ok().body("{\"message\": \"Successful!\"}");
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        if (userRepository.findByUsername(username).isPresent) {
            val user: Optional<User> = userRepository.findByUsername(username)
            return org.springframework.security.core.userdetails.User(user.get().email, user.get().password, null)
        } else {
            throw UsernameNotFoundException()
        }
    }
}

