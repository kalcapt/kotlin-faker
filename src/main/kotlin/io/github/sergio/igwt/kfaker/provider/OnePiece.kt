package io.github.sergio.igwt.kfaker.provider

import io.github.sergio.igwt.kfaker.Faker
import io.github.sergio.igwt.kfaker.FakerService
import io.github.sergio.igwt.kfaker.dictionary.CategoryName

/**
 * [FakeDataProvider] implementation for [CategoryName.ONE_PIECE] category.
 */
class OnePiece internal constructor(fakerService: FakerService) : AbstractFakeDataProvider(fakerService) {
    override val categoryName = CategoryName.ONE_PIECE

    val characters = resolve { fakerService.resolve(Faker, it, "characters") }
    val seas = resolve { fakerService.resolve(Faker, it, "seas") }
    val islands = resolve { fakerService.resolve(Faker, it, "islands") }
    val locations = resolve { fakerService.resolve(Faker, it, "locations") }
    val quotes = resolve { fakerService.resolve(Faker, it, "quotes") }
    val akumasNoMi = resolve { fakerService.resolve(Faker, it, "akumas_no_mi") }
}