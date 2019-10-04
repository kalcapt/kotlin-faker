package io.github.serpro69.kfaker.provider

import io.github.serpro69.kfaker.*
import io.github.serpro69.kfaker.dictionary.*

/**
 * [FakeDataProvider] implementation for [CategoryName.APP] category.
 */
@Suppress("unused")
class App internal constructor(fakerService: FakerService) : AbstractFakeDataProvider<App>(fakerService) {
    override val categoryName = CategoryName.APP
    override val unique by UniqueProviderDelegate(uniqueDataProvider)

    val name = resolve("name")
    val version = resolve("version")
    val author = resolve("author")
}