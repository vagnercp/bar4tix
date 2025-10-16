
package com.bar4tix.bffapp
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BffApp


fun main(args: Array<String>) {
    runApplication<BffApp>(*args)
}