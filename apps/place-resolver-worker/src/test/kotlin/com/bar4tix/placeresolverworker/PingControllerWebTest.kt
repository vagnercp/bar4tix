package com.bar4tix.placeresolverworker

import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@WebMvcTest(PingController::class)
class PingControllerWebTest @Autowired constructor(
    private val mvc: MockMvc,
) {
    @Test
    fun ping_returns_ok() {
        mvc.perform(get("/ping"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status").exists())
    }
}
