package ml.rhodes.bots.discord.devrant.services.devRant

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import ml.rhodes.bots.discord.devrant.network.Resource
import ml.rhodes.bots.discord.devrant.services.devRant.models.Rant
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONObject
import java.util.concurrent.TimeUnit

/**
 * Created by obizreh on 7/9/17.
 */
class API {
    private val baseUrl: String = "https://www.devrant.io/api/devrant/%s?app=3%s"
    private val httpClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    private val defaultGson = Gson()

    /**
     * Fetch rants
     */
    suspend fun fetchRants(): Resource<ArrayList<Rant>> {
        return async(CommonPool) {
            val request: Request = Request.Builder().url(String.format(baseUrl, "rants", "")).build()
            try {
                val response: Response = httpClient.newCall(request).execute()
                if (response.isSuccessful) {
                    val body: String = response.body()!!.string()
                    val rants: ArrayList<Rant> = arrayListOf()
                    val jsonBody: JsonObject = defaultGson.fromJson(body, JsonObject::class.java)
                    if (jsonBody.has("success") && jsonBody.get("success").asBoolean && jsonBody.has("rants")
                            && !jsonBody.get("rants").isJsonNull && jsonBody.get("rants").isJsonArray) {
                        rants.addAll(jsonBody.get("rants").asJsonArray.mapTo(rants) { Rant(it.asJsonObject) })
                    }
                    Resource.success(rants)
                } else {
                    Resource.error(response.message(), arrayListOf<Rant>())
                }
            } catch (ex: Exception) {
                Resource.error(ex.message!!, arrayListOf<Rant>())
            }
        }.await()
    }

    /**
     * Fetch rant by Id
     *
     * @param rantId ID of rant to get
     * @return [Resource<Rant>] instance
     */
    suspend fun fetchRant(rantId: String): Resource<Rant> {
        return async(CommonPool) {
            val request: Request = Request.Builder().url(String.format(baseUrl, "rants/" + rantId, "")).build()
            try {
                val response: Response = httpClient.newCall(request).execute()
                if(response.isSuccessful){
                    val body: String = response.body()!!.string()
                    val rant: JsonObject = defaultGson.fromJson(body, JsonObject::class.java)
                    Resource.success(Rant(rant.get("rant").asJsonObject))
                }else{
                    Resource.error(response.message(), Rant())
                }
            } catch (ex: Exception) {
                Resource.error(ex.message!!, Rant())
            }
        }.await()
    }
}