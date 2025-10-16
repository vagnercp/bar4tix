
package com.bar4tix.profileservice
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
@RestController
class PingController {
  @GetMapping("/ping") fun ping() = mapOf("service" to "profile-service", "status" to "ok")
}
