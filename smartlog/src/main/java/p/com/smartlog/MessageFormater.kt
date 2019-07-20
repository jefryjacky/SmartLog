package p.com.smartlog

interface MessageFormater {
    fun format(message:String?, throwable: Throwable? = null):String
}