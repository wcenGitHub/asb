package nz.co.asb

import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

object Constants {
    const val BASE_URL = "https://60220907ae8f8700177dee68.mockapi.io/api/v1/"
    //Connect timeout in seconds
    const val CONNECT_TIMEOUT = 60L
    //Read timeout in seconds
    const val READ_TIMEOUT = 60L

    val DATE_DISPLAY_FORMAT: DateTimeFormatter = DateTimeFormat.forPattern("dd MMM yyyy")

    const val GST_RATE = 0.15
}