package com.github.philipjkim.demo.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PokemonCollectingServiceTest {

    private val service = PokemonCollectingService()

    @Test
    fun `test collectMultiplePokemons`() {
        val pokemons = service.collectMultiplePokemons(3)
        println(pokemons)
        assertEquals(3, pokemons.size)

        pokemons.forEach {
            assertTrue(it.name.isNotBlank())
            assertTrue(it.color.isNotBlank())
            assertTrue(it.nationality.isNotBlank())
        }
    }
}
