package ml.rhodes.bots.discord.devrant.commands.devrant


import ml.rhodes.bots.discord.devrant.Main.client
import sx.blah.discord.api.events.IListener
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent
import sx.blah.discord.util.EmbedBuilder
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
                val rantId = matcher.group(1).toString()
                client!!.getChannelByID(channel).sendMessage("We're working on parsing devrant rants. Please give us time")
                /*
                * builder.withAuthorName(devRantUser)
                * builder.withAuthorIcon(devRantUserImage)
                * builder.withAuthorUrl(devRantUserUrl)
                *
                * builder.withTitle(rantTitle)
                * builder.withDesc(rantShortPreview)
                * builder.withUrl(rantUrl);
                * if(rantAttachment.isNotEmpty()) {
                *       builder.withImage(rantAttachment);
                * }
                *
                * builder.appendField("++", rantPlusPlus, true);
                * builder.appendField("Comments", rantCommentCount, true);
                *
                * client!!.getChannelByID(channel).sendMessage(builder.build())
                */
            }
        }
    }
}