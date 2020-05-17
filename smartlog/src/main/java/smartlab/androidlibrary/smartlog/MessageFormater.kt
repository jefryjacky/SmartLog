package smartlab.androidlibrary.smartlog

interface MessageFormater {
    fun format(message:String?, throwable: Throwable? = null):String
}