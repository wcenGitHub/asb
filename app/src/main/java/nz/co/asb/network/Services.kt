package nz.co.asb.network

import nz.co.asb.model.Transaction
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

interface Services {

    // API services
    @GET("transactions")
    suspend fun fetchTransactions(): List<Transaction>
}