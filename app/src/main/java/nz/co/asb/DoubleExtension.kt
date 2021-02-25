package nz.co.asb

import java.text.NumberFormat
import java.util.*

fun Double.toCurrencyFormattedString(locale: Locale): String {
    return NumberFormat.getCurrencyInstance(locale).format(this)
}