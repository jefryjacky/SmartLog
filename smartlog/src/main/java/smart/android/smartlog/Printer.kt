package smart.android.smartlog

interface Printer {
    fun log(logLevel:LogLevel, tag:String, message: String)
}