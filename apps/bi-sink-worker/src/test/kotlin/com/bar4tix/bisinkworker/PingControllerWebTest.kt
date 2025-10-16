package com.bar4tix.bisinkworker

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PingControllerWebTest(private val webTestClient: WebTestClient) {

    @Test
    fun ping_returns_ok() {
        webTestClient.get().uri("/ping")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.status").isEqualTo("ok")
    }
}
