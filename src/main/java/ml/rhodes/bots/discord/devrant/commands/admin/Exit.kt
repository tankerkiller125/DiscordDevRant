package ml.rhodes.bots.discord.devrant.commands.admin

import de.btobastian.sdcf4j.Command
import de.btobastian.sdcf4j.CommandExecutor
import ml.rhodes.bots.discord.devrant.Main.client
import ml.rhodes.bots.discord.devrant.Main.handler
import sx.blah.discord.handle.obj.IUser
import kotlin.system.exitProcess

class Exit : CommandExecutor {

    /**
     * Shutdown the bot
     *
     * @param user IUser
     * @return String
     */
    @Command(aliases = arrayOf("exit", "logout"), description = "Logout and exit bot", usage = "exit", channelMessages = false)
    fun onExitCommand(user: IUser): String {
        if (user.discriminator == client!!.applicationOwner.discriminator || handler!!.hasPermission(user.stringID, "admin.exit")) {
            user.orCreatePMChannel.sendMessage("Bot shutting Down!")
            exitProgram()
            return "Bot shutting down!"
        } else {
            return "You can't do that!"
        }
    }

    private fun exitProgram() {
        handler!!.savePermissions()
        Thread.sleep(5000)
        client!!.logout()
        exitProcess(0)
    }
}