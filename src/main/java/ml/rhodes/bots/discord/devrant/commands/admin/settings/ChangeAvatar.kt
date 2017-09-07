package ml.rhodes.bots.discord.devrant.commands.admin.settings

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import sx.blah.discord.api.IDiscordClient
import sx.blah.discord.util.Image

class ChangeAvatar(private val client: IDiscordClient) : CommandExecutor {
    @Command(aliases = arrayOf("changeavatar", "chgavatar"), usage = "changeavatar <image URL>", description = "Change the avatar of the bot", async = true, requiredPermissions = "admin.settings.avatar")
    fun onChangeAvatarCommand(args: Array<String>): String {
        if (args.size == 1) {
            if (args[0].endsWith(".png")) {
                client.changeAvatar(Image.forUrl("png", args[0]))
            } else if (args[0].endsWith(".jpg")) {
                client.changeAvatar(Image.forUrl("jpg", args[0]))
            } else if (args[0].endsWith(".gif")) {
                client.changeAvatar(Image.forUrl("gif", args[0]))
            } else {
                return "Image type not supported"
            }
            return "Changed my avatar!"
        } else {
            return "Not enough arguments or to many"
        }
    }

}