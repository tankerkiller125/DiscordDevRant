package ml.rhodes.bots.discord.devrant.commands.admin

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import ml.rhodes.bots.discord.devrant.Main
import ml.rhodes.bots.discord.devrant.utils.NewHandler
import sx.blah.discord.handle.obj.IMessage
import sx.blah.discord.handle.obj.IUser

class RemovePermission(private val commandHandler: NewHandler) : CommandExecutor {

    /**
     * Remove permission from user
     *
     * @param args Array<String>
     * @param user IUser
     * @param message IMessage
     * @return String
     */
    @Command(aliases = arrayOf("removepermission", "rmperm"), usage = "rmperm <permission> <@user>", description = "Remove permission from user")
    fun onRemovePermissionCommand(args: Array<String>, user: IUser, message: IMessage): String {
        if (user.discriminator == Main.client!!.applicationOwner.discriminator || commandHandler.hasPermission(user.stringID, "admin.permission.remove")) {
            val users = message.mentions
            users.forEach { user ->
                if (!commandHandler.hasPermission(user.stringID, args[0])) {
                    return user.mention() + " doesn't have the " + args[0] + " permission."
                }
                commandHandler.removePermission(user.stringID, args[0])
                commandHandler.savePermissions()
                return user.mention() + " lost the " + args[0] + "permission."
            }
            return "This should never happen"
        } else {
            return "You can not do that!"
        }
    }
}