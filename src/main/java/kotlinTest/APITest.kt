package kotlinTest

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import ml.rhodes.bots.discord.devrant.network.ResourceStatus
import ml.rhodes.bots.discord.devrant.services.devRant.API
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class APITest {

    @Test
    fun fetchRants_Success() {
        runBlocking {
            val result = API().fetchRants()
            assert(result.status == ResourceStatus.success)
            assert(result.data != null)
            assert(result.data!!.first().id != null)
        }
    }

    @Test
    fun fetchRant_Success(){
        runBlocking {
            val result = API().fetchRant("832739")
            assert(result.status == ResourceStatus.success)
            assert(result.data != null)
            assert(result.data!!.id == 832739)
        }
    }
}