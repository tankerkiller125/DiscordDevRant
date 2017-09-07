package ml.rhodes.bots.discord.devrant

import ml.rhodes.bots.discord.devrant.utils.NewHandler
import sx.blah.discord.api.IDiscordClient

object Main {
    var client: IDiscordClient? = null
    var handler: NewHandler? = null

    @JvmStatic fun main(args: Array<String>) {
        // Create bot and handler
        client = Bot.createClient(args[0], true)
        handler = NewHandler(client)

        // Load custom user permissions for bot
        handler!!.loadPermissions()


        // Save custom user permission for bot before shutdown (only when done safely)
        Runtime.getRuntime().addShutdownHook(Thread {
            fun run() {
                handler!!.savePermissions()
                print("Shutdown Run!")
            }
        })
    }
}