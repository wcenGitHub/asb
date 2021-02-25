package nz.co.asb.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

open class MockingInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val responseString =
            """
                [
                   {
                      "id":"1",
                      "transactionDate":"2021-02-02T08:11:16+13:00",
                      "summary":"Salary",
                      "debit":200.0,
                      "credit":0
                   },
                   {
                      "id":"2",
                      "transactionDate":"2021-02-03T00:06:37+13:00",
                      "summary":"Parking Auckland",
                      "debit":8.0,
                      "credit":0
                   },
                   {
                      "id":"3",
                      "transactionDate":"2021-02-02T17:22:33+13:00",
                      "summary":"Tips",
                      "debit":0,
                      "credit":1.0
                   }
                ]
            """
        val body = responseString.toByteArray()

        val responseBuilder = Response.Builder()
            .code(200)
            .message(responseString)
            .request(chain.request())
            .protocol(Protocol.HTTP_1_0)
            .body(body.toResponseBody("application/json".toMediaTypeOrNull()))
            .addHeader("content-type", "application/json")

        return responseBuilder.build()
    }
}
