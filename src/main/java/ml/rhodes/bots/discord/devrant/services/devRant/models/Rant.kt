package ml.rhodes.bots.discord.devrant.services.devRant.models

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.json.JSONObject
import java.net.URI
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by obizreh on 8/9/17.
 */
class Rant {
    var id: Int = -1
    var text: String = ""
    var score: Int = 0
    var creationDate: Date = Date()
    var tags: ArrayList<String> = arrayListOf()
    var edited: Boolean = false
    var link: URI? = null
    var user: User = User()
    var comments: List<Comment> = listOf()

    constructor(jsonObject: JsonObject){
        if(jsonObject.has("id")) {
            this.id = jsonObject.get("id").asInt
        }

        if(jsonObject.has("text")){
            this.text = jsonObject.get("text").asString
        }

        if(jsonObject.has("score")){
            this.score = jsonObject.get("score").asInt
        }

        if(jsonObject.has("create_time")){
            Calendar.getInstance().time.time = jsonObject.get("create_time").asLong
        }

        if(jsonObject.has("tags")){
            val tagsArray = jsonObject.get("tags").asJsonArray
            for(element: JsonElement in tagsArray){
                this.tags.add(element.asString)
            }
        }
    }

    constructor(){
        // Empty Constructor
    }
}