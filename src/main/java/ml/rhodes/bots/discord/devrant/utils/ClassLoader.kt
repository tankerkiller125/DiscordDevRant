package ml.rhodes.bots.discord.devrant.utils


import ml.rhodes.bots.discord.devrant.Main.client
import sx.blah.discord.api.events.IListener

class ClassLoader {
    fun unregister() {
        for (command in classes) {
            client!!.dispatcher.unregisterListener(command)
        }
    }

    fun register() {
        for (command in classes) {
            client!!.dispatcher.registerListener(command)
        }
    }

    companion object {
        private val classes = arrayOf<IListener<*>>(
                // List of command classes
        )
    }
}
