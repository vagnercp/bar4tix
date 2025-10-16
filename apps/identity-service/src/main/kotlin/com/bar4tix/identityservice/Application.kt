
package com.bar4tix.identityservice
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IdentityService


fun main(args: Array<String>) {
    runApplication<IdentityService>(*args)
}