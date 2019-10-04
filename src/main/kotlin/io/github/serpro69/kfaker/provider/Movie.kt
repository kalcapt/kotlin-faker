package io.github.serpro69.kfaker.provider

import io.github.serpro69.kfaker.*
import io.github.serpro69.kfaker.dictionary.*

/**
 * [FakeDataProvider] implementation for [CategoryName.MOVIE] category.
 */
@Suppress("unused")
class Movie internal constructor(fakerService: FakerService) : AbstractFakeDataProvider<Movie>(fakerService) {
    override val categoryName = CategoryName.MOVIE
    override val unique by UniqueProviderDelegate(uniqueDataProvider)

    val quote = resolve("quote")
}
