package ml.rhodes.bots.discord.devrant.services.devRant.models

import java.util.*

/**
 * Created by obizreh on 8/9/17.
 */
class Comment{
    var Id: Int = 0
    var content: String = ""
    var score: Int = 0
    var creationDate: Date = Date()
    var user: User = User()
}