package p.com.smartlog

import java.util.*

interface MessageFormater {
    fun format(message:String?, throwable: Throwable? = null):String
}