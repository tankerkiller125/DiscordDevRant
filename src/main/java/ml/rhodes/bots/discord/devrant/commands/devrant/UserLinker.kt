package ml.rhodes.bots.discord.devrant.commands.devrant


import ml.rhodes.bots.discord.devrant.Main.client
import sx.blah.discord.api.events.IListener
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent
import java.util.regex.Pattern

class UserLinker : IListener<MessageReceivedEvent> {
    override fun handle(event: MessageReceivedEvent) {
        val channel = event.message.channel.longID

        val patternString = "devrant@(([0-9a-zA-Z]*))" // Username

        val pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE or Pattern.DOTALL or Pattern.MULTILINE)
        val matcher = pattern.matcher(event.message.content)

        if (matcher.find()) {
            matcher.reset()
            while (matcher.find()) {
                val userId = matcher.group(1)
                client!!.getChannelByID(channel).sendMessage("We're working on parsing devrant users. Please give us time")
            }
        }
    }
}