package nz.co.asb.model

import android.util.Log
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import org.joda.time.DateTime
import java.lang.reflect.Type

class DateAdapter : JsonDeserializer<DateTime> {

    companion object {
        private val TAG = DateAdapter::class.java.simpleName
    }

    override fun deserialize(json: JsonElement,
                             typeOfT: Type,
                             context: JsonDeserializationContext): DateTime {
        var date = DateTime()
        try {
            date = DateTime.parse(json.asString)
        } catch (ex: Exception) {
            Log.e(
                TAG,
                (if (ex.message == null) "Error parsing date" else ex.message)!!
            )
        }
        return date
    }
}
