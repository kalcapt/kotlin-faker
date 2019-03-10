package io.github.sergio.igwt.kfaker.provider

import io.github.sergio.igwt.kfaker.Faker
import io.github.sergio.igwt.kfaker.FakerService
import io.github.sergio.igwt.kfaker.dictionary.CategoryName

/**
 * [FakeDataProvider] implementation for [CategoryName.THE_THICK_OF_IT] category.
 */
class TheThickOfIt internal constructor(fakerService: FakerService) : AbstractFakeDataProvider(fakerService) {
    override val categoryName = CategoryName.THE_THICK_OF_IT

    val characters = resolve { fakerService.resolve(Faker, it, "characters") }
    val positions = resolve { fakerService.resolve(Faker, it, "positions") }
    val departments = resolve { fakerService.resolve(Faker, it, "departments") }
}
