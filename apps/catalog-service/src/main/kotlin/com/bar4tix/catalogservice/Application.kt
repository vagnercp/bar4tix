
package com.bar4tix.catalogservice
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CatalogService

fun main(args: Array<String>) {
    runApplication<CatalogService>(*args)
}