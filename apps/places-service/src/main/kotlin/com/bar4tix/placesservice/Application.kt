
package com.bar4tix.placesservice
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlacesService


fun main(args: Array<String>) {
    runApplication<PlacesService>(*args)
}