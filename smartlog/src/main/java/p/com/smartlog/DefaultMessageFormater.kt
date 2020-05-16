package p.com.smartlog

import java.lang.StringBuilder

class DefaultMessageFormater:MessageFormater {

    private val messageBuilder = StringBuilder()

    override fun format(message: String?, throwable: Throwable?): String {
        messageBuilder.clear()
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