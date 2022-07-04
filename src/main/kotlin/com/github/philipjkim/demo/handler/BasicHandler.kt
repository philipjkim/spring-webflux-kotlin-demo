package com.github.philipjkim.demo.handler

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import java.time.ZonedDateTime

@Component
class BasicHandler(
    @Value("\${vars.foo}") var foo: String,
    @Value("\${vars.lucky-number}") var luckyNumber: Int,
) {

    private val appStartedAt = ZonedDateTime.now()!!

    suspend fun ping(request: ServerRequest): ServerResponse {
        val respBody = mapOf(
            "appStartedAt" to appStartedAt.toString(),
            "foo" to foo,
            "luckyNumber" to luckyNumber,
        )
        return ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(respBody)
    }
}
