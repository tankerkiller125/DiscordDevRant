package ml.rhodes.bots.discord.devrant

import ml.rhodes.bots.discord.devrant.utils.ClassLoader
import sx.blah.discord.api.events.IListener
import sx.blah.discord.handle.impl.events.ReadyEvent

class Ready : IListener<ReadyEvent> {
    override fun handle(readyEvent: ReadyEvent) {
        println("The bot is connected")

        val ClassLoader = ClassLoader()
        ClassLoader.register()

        //discordClient.getDispatcher().registerListener(new Disconnect());
        //discordClient.getDispatcher().registerListener(new Reload());
        println("Listeners have been added")
    }
}
