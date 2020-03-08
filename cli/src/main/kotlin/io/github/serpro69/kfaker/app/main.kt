package io.github.serpro69.kfaker.app

import io.github.serpro69.kfaker.*
import picocli.*

@CommandLine.Command(name = "kFaker", description = ["Generates realistic fake data"])
object KFaker : Runnable {
    val fakerConfig = FakerConfig.builder().create {

    }
    val faker = Faker()

    @JvmStatic
    fun main(args: Array<String>) {
        val cli = CommandLine(KFaker)

        cli.execute(*args)
    }

    override fun run() {
        println(faker.address.city())
    }
}
