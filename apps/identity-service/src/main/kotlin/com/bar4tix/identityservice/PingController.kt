
package com.bar4tix.identityservice
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
@RestController
class PingController {
  @GetMapping("/ping") fun ping() = mapOf("service" to "identity-service", "status" to "ok")
}
