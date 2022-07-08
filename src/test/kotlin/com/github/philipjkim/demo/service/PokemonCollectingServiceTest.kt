package com.github.philipjkim.demo.service

import app.cash.turbine.test
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

internal class PokemonCollectingServiceTest {

    private val service = PokemonCollectingService()

    @Test
    fun `test collectThreePokemon`() {
        val elapsedMs = measureTimeMillis {
            runBlocking {
                val pokemon = service.collectThreePokemonSequentially()
                println(pokemon)
                assertEquals(3, pokemon.size)

                pokemon.forEach {
                    assertTrue(it.name.isNotBlank())
                    assertTrue(it.color.isNotBlank())
                    assertTrue(it.nationality.isNotBlank())
                }
            }
        }
        println("elapsedMs: $elapsedMs")
        assertTrue(elapsedMs > 3_000L)
    }

    @Test
    fun `test collectMultiplePokemonsInParallel`() {
        val elapsedMs = measureTimeMillis {
            runBlocking {
                flowOf(service.collectThreePokemonInParallel())
                    .test {
                        val pokemon = awaitItem()
                        println(pokemon)
                        assertEquals(3, pokemon.size)
                        pokemon.forEach {
                            assertTrue(it.name.isNotBlank())
                            assertTrue(it.color.isNotBlank())
                            assertTrue(it.nationality.isNotBlank())
                        }
                        awaitComplete()
                    }
            }
        }
        println("elapsedMs: $elapsedMs")
        assertTrue(elapsedMs < 3_000L)
    }
}
