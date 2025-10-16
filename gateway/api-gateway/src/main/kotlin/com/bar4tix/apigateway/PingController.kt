
package com.bar4tix.apigateway

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
@RestController
class PingController {
    @GetMapping("/ping") fun ping() = mapOf("service" to "api-gateway", "status" to "ok")
}