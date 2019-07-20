package p.com.smartlog

import java.lang.StringBuilder

class DefaultMessageFormater:MessageFormater {

    private val messageBuilder = StringBuilder()

    override fun format(message: String?, throwable: Throwable?): String {
        if(message!=null) {
            messageBuilder.append(message)
            messageBuilder.append("\n")
        }
        messageBuilder.append("thread: ")
        messageBuilder.append(Thread.currentThread().name)
        messageBuilder.append("\n")
        if(throwable!=null) {
            messageBuilder.append(throwable.toString())
        }
        return messageBuilder.toString()
    }
}