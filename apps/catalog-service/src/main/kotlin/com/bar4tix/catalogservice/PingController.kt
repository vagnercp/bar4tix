
package com.bar4tix.catalogservice
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
@RestController
class PingController {
  @GetMapping("/ping") fun ping() = mapOf("service" to "catalog-service", "status" to "ok")
}
