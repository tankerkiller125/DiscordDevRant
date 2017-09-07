package ml.rhodes.bots.discord.devrant

import sx.blah.discord.api.ClientBuilder
import sx.blah.discord.api.IDiscordClient
import sx.blah.discord.util.DiscordException

object Bot {
    /**
     * Create discord client
     *
     * @param token String
     * @param login Boolean
     * @return IDiscordClient|null
     */
    fun createClient(token: String, login: Boolean): IDiscordClient? { // Returns a new instance of the Discord client
        val clientBuilder = ClientBuilder().withRecommendedShardCount().withToken(token) // Creates the ClientBuilder instance with recommended shards
        try {
            if (login) {
                return clientBuilder.login() // Creates the client instance and logs the client in
            } else {
                return clientBuilder.build() // Creates the client instance but it doesn't log the client in yet, you would have to call client.login() yourself
            }
        } catch (e: DiscordException) { // This is thrown if there was a problem building the client
            e.printStackTrace()
            return null
        }
    }
}