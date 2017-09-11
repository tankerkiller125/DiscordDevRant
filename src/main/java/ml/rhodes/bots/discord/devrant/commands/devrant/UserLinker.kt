package ml.rhodes.bots.discord.devrant.commands.devrant


import ml.rhodes.bots.discord.devrant.Main.client
import ml.rhodes.libs.devrant.DevRant
import ml.rhodes.libs.devrant.exceptions.NoSuchUserException
import sx.blah.discord.api.events.IListener
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent
import sx.blah.discord.util.EmbedBuilder
import sx.blah.discord.util.RequestBuffer
import java.util.regex.Pattern

class UserLinker : IListener<MessageReceivedEvent> {
    override fun handle(event: MessageReceivedEvent) {
        val channel = event.message.channel.longID

        val patternString = "devrant@(([0-9a-zA-Z]*))" // Username

        val pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE or Pattern.DOTALL or Pattern.MULTILINE)
        val matcher = pattern.matcher(event.message.content)

        val builder = EmbedBuilder()

        if (matcher.find()) {
            matcher.reset()
            while (matcher.find()) {
                val username: String = matcher.group(1)
                //client!!.getChannelByID(channel).sendMessage("We're working on parsing devrant users. Please give us time")\

                try {
                    val user = DevRant().getUser(username)
                    builder.withTitle(user.username)
                    builder.withUrl(user.userLink())
                    if (user.avatarLink().toString().isNotEmpty()) {
                        builder.withImage(user.avatarLink())
                    }
                    builder.appendField("++", user.upvotedCount.toString(), true)
                    builder.appendField("Rants", user.rantsCount.toString(), true)
                    builder.appendField("Comments", user.commentsCount.toString(), true)
                    RequestBuffer.request {
                        client!!.getChannelByID(channel).sendMessage(builder.build())
                    }
                } catch (exception: NoSuchUserException) {
                    client!!.getChannelByID(channel).sendMessage("That user doesn't exist")
                }
            }
        }
    }
}