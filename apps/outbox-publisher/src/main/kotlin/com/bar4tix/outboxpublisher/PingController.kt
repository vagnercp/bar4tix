
package com.bar4tix.outboxpublisher
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
@RestController
class PingController {
  @GetMapping("/ping") fun ping() = mapOf("service" to "outbox-publisher", "status" to "ok")
}
