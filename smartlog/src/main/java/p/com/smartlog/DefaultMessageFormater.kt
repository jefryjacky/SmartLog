package p.com.smartlog

import java.lang.StringBuilder
import java.text.DateFormat
import java.util.*

class DefaultMessageFormater:MessageFormater {

    private val messageBuilder = StringBuilder()

    override fun format(message: String?, throwable: Throwable?): String {
        messageBuilder.clear()

        val dateFormat = DateFormat.getDateTimeInstance()
        messageBuilder.append("[")
        messageBuilder.append(dateFormat.format(Date()))
        messageBuilder.append("], ")

        messageBuilder.append("[thread: ")
        messageBuilder.append(Thread.currentThread().name)
        messageBuilder.append("] - ")

        if(message!=null) {
            messageBuilder.append(message)
        }
        if(throwable!=null) {
            messageBuilder.append("\n")
            messageBuilder.append(throwable.toString())
        }
        return messageBuilder.toString()
    }
}