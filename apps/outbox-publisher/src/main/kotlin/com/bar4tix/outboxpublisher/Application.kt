
package com.bar4tix.outboxpublisher
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OutboxPublisher


fun main(args: Array<String>) {
    runApplication<OutboxPublisher>(*args)
}