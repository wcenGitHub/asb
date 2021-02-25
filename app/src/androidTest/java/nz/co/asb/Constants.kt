package nz.co.asb

import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

object Constants {

    const val WAIT_TIME_MILLISECOND_LONG: Long = 2000
    const val WAIT_TIME_MILLISECOND_MEDIUM: Long = 1000
    const val WAIT_TIME_MILLISECOND_SHORT: Long = 500
    const val WAIT_TIME_MILLISECOND_FAST: Long = 200

    const val WAIT_TIME_MILLISECOND_TOAST: Long = 3000

    val DATE_DISPLAY_FORMAT: DateTimeFormatter = DateTimeFormat.forPattern("dd MMM yyyy")

}