package com.github.philipjkim.demo.service

import com.github.javafaker.Faker
import org.springframework.stereotype.Service

@Service
class PokemonCollectingService {
    private val faker = Faker()

    fun collectMultiplePokemons(amount: Int) =
        IntRange(1, amount).map { _ -> pokemon() }

    private fun pokemon(): Pokemon {
        return Pokemon(
            name = faker.pokemon().name(),
            color = faker.color().name(),
            nationality = faker.nation().nationality()
        )
    }
}

data class Pokemon(
    val name: String,
    val color: String,
    val nationality: String,
)
