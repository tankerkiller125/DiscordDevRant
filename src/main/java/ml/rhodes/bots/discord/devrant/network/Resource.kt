package ml.rhodes.bots.discord.devrant.network

/**
 * Created by obizreh on 8/9/17.
 */
class Resource<T> private constructor(val status: ResourceStatus, val data: T?, val message: String?) {
    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(ResourceStatus.success, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ResourceStatus.failed, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(ResourceStatus.loading, data, null)
        }
    }
}