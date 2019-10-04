package io.github.serpro69.kfaker.provider

import io.github.serpro69.kfaker.*
import io.github.serpro69.kfaker.dictionary.*

/**
 * [FakeDataProvider] implementation for [CategoryName.CREATURE] category.
 */
@Suppress("unused")
class Animal internal constructor(fakerService: FakerService) : AbstractFakeDataProvider<Animal>(fakerService) {
    override val categoryName = CategoryName.CREATURE
    override val unique by UniqueProviderDelegate(uniqueDataProvider)

    val name = resolve { fakerService.resolve(it, "animal", "name") }
}