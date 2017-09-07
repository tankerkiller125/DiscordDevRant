package ml.rhodes.bots.discord.devrant.commands

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import ml.rhodes.bots.discord.devrant.utils.NewHandler

class Help(private val commandHandler: NewHandler) : CommandExecutor {

    /**
     * Show help page
     *
     * @return String
     */
    @Command(aliases = arrayOf("!help", "!h"), description = "Shows this page", usage = "!help", async = true)
    fun onHelpCommand(): String {
        val builder = StringBuilder()
        builder.append("**Commands:**")
        builder.append("```xml") // a xml code block looks fancy
        commandHandler.commands.forEach { command ->
            if (!command.commandAnnotation.showInHelpPage) {
                return@forEach // skip command
            }
            builder.append("\n")
            if (!command.commandAnnotation.requiresMention) {
                // the default prefix only works if the command does not require a mention
                builder.append(commandHandler.defaultPrefix)
            }
            var usage = command.commandAnnotation.usage
            if (usage.isEmpty()) { // no usage provided, using the first alias
                usage = command.commandAnnotation.aliases[0]
            }
            builder.append(usage)
            val description = command.commandAnnotation.description
            if (description != "none") {
                builder.append(" | ").append(description)
            }
        }
        //builder.append("\n```")
        //builder.append("Questions? https://discord.gg/GAC6q3M")// end of xml code block
        return builder.toString()
    }
}