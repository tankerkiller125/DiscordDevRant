package ml.rhodes.bots.discord.devrant.commands.devrant


import ml.rhodes.bots.discord.devrant.Main.client
import ml.rhodes.libs.devrant.DevRant
import ml.rhodes.libs.devrant.exceptions.NoSuchRantException
import sx.blah.discord.api.events.IListener
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent
import sx.blah.discord.util.EmbedBuilder
import sx.blah.discord.util.RequestBuffer
import java.util.regex.Pattern

class RantLinker : IListener<MessageReceivedEvent> {

    override fun handle(event: MessageReceivedEvent) {
        val channel = event.message.channel.longID

        val patternString = "devrant#(([0-9]*))" // Rant ID

        val pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE or Pattern.DOTALL or Pattern.MULTILINE)
        val matcher = pattern.matcher(event.message.content)

        val builder = EmbedBuilder()

        if (matcher.find()) {
            matcher.reset()
            while (matcher.find()) {
                val rantId = matcher.group(1).toInt()
                try {
                    val rant = DevRant().getRant(rantId)

                    builder.withAuthorName(rant.user.username)
                    builder.withAuthorIcon(rant.user.avatarLink())
                    builder.withAuthorUrl(rant.user.userLink())

                    //builder.withTitle()
                    builder.withDesc(rant.content)
                    builder.withUrl(rant.link())
                    if (rant.image.url.isNotEmpty()) {
                        builder.withImage(rant.image.url)
                    }

                    builder.appendField("++", rant.score.toString(), true)
                    builder.appendField("Comments", rant.commentCount.toString(), true);

                    RequestBuffer.request {
                        client!!.getChannelByID(channel).sendMessage(builder.build())
                    }
                } catch (exception: NoSuchRantException) {
                    client!!.getChannelByID(channel).sendMessage("That rant doesn't exist")
                }
                //client!!.getChannelByID(channel).sendMessage("We're working on parsing devrant rants. Please give us time")
            }
        }
    }
}