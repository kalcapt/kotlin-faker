package io.github.serpro69.kfaker.app.subcommands

import io.github.serpro69.kfaker.app.*
import picocli.*

@CommandLine.Command(
    name = "address"
)
object Address : SubCommand() {
    @CommandLine.Spec
    override lateinit var spec: CommandLine.Model.CommandSpec

    @CommandLine.ParentCommand
    override lateinit var parent: KFaker

    @CommandLine.Command(name = "city")
    fun city() {
        super.run()
        println(faker.address.city())
    }
}
