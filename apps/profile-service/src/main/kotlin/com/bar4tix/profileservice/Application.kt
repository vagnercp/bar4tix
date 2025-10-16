
package com.bar4tix.profileservice
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProfileService


fun main(args: Array<String>) {
    runApplication<ProfileService>(*args)
}