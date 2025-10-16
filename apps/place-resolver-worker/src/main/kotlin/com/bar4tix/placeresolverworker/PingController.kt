
package com.bar4tix.placeresolverworker
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
@RestController
class PingController {
  @GetMapping("/ping") fun ping() = mapOf("service" to "place-resolver-worker", "status" to "ok")
}
