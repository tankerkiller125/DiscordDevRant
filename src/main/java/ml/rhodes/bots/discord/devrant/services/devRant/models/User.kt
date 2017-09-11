package ml.rhodes.bots.discord.devrant.services.devRant.models

import org.json.JSONObject

/**
 * Created by obizreh on 8/9/17.
 */
class User {
    var userId: Int
    var userName: String
    var score: Int
    var avatar: String

    constructor(jsonObject: JSONObject){
        if (jsonObject.has("user_id")){
            this.userId = jsonObject.getInt("user_id")
        }else{
            this.userId = -1
        }
        if (jsonObject.has("avatar")){
            this.avatar = jsonObject.getString("avatar")
        }else{
            this.avatar = ""
        }
        if (jsonObject.has("score")){
            this.score = jsonObject.getInt("score")
        }else{
            this.score = -1
        }
        if (jsonObject.has("user_name")){
            this.userName = jsonObject.getString("user_name")
        }else{
            this.userName = ""
        }

    }

    constructor()
    {
        this.userId = 0
        this.userName = ""
        this.score = 0
        this.avatar = ""
    }
}