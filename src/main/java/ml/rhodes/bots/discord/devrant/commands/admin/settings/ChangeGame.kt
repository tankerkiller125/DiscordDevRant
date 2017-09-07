package ml.rhodes.bots.discord.devrant.commands.admin.settings

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import sx.blah.discord.api.IDiscordClient

class ChangeGame(private val client: IDiscordClient) : CommandExecutor {
    @Command(aliases = arrayOf("changegame", "chggame"), usage = "changegame <string>", description = "Change the game the bots playing", async = true, requiredPermissions = "admin.settings.game")
    fun onChangeAvatarCommand(args: Array<String>): String {
        val builder = StringBuilder()
        return if (!args.isEmpty()) {
            args.forEach { arg ->
                builder.append(arg)
            }
            client.changePlayingText(builder.toString())
            "Changed my game!"
        } else {
            "Not enough arguments"
        }
    }

}
