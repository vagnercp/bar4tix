
package com.bar4tix.bisinkworker
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
@RestController
class PingController {
  @GetMapping("/ping") fun ping() = mapOf("service" to "bi-sink-worker", "status" to "ok")
}
