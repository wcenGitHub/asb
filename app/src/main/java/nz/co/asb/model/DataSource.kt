package nz.co.asb.model

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nz.co.asb.App
import nz.co.asb.di.SessionScope
import nz.co.asb.network.RetrofitClient
import nz.co.asb.network.Services
import javax.inject.Inject

@SessionScope
class DataSource @Inject constructor(app: App, retrofitClient: RetrofitClient) {

    private val services: Services = retrofitClient.initRetrofit().create(Services::class.java)

    suspend fun fetchTransactions(): List<Transaction> =
        networkAsyncCall {
            services.fetchTransactions()
        }

    private suspend fun <T> networkAsyncCall(apiCall: suspend () -> T): T {
        return withContext(Dispatchers.IO) {
            try {
                apiCall.invoke()
            } catch (exception: java.lang.Exception) {
                throw exception
            }
        }
    }
}