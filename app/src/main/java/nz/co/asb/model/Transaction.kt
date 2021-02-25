package nz.co.asb.model

import com.google.gson.annotations.JsonAdapter
import org.joda.time.DateTime

data class Transaction(
    val id: String,

    @JsonAdapter(DateAdapter::class)
    val transactionDate: DateTime,

    val summary: String,
    val debit: Double,
    val credit: Double
)