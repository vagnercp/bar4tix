
package com.bar4tix.eventsservice
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EventsService


fun main(args: Array<String>) {
    runApplication<EventsService>(*args)
}