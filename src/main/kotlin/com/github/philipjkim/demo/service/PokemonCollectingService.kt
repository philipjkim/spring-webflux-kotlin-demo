package com.github.philipjkim.demo.service

import com.github.javafaker.Faker
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class PokemonCollectingService {
    private val faker = Faker()
    private val log = LoggerFactory.getLogger(this::class.java)

    /**
     * Collect 3 Pokemon sequentially.
     * It takes at least 3,000ms to complete collection.
     */
    suspend fun collectThreePokemonSequentially() =
        IntRange(1, 3).map { pokemon() }

    /**
     * Collect 3 Pokemon in parallel.
     * It takes less than 3,000ms to complete collection.
     */
    suspend fun collectThreePokemonInParallel() = coroutineScope {
        IntRange(1, 3)
            .map { async { pokemon() } }
            .map { it.await() }
    }

    private suspend fun pokemon(): Pokemon {
        val msToSleep = Random.nextLong(1_000L, 2_000L)
        delay(msToSleep)
        val pokemon = Pokemon(
            name = faker.pokemon().name(),
            color = faker.color().name(),
            nationality = faker.nation().nationality()
        )
        log.info("Elapsed ${msToSleep}ms to collect a pokemon ${pokemon.name}")

        return pokemon
    }
}

data class Pokemon(
    val name: String,
    val color: String,
    val nationality: String,
)
