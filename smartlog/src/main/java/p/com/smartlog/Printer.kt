package p.com.smartlog

interface Printer {
    fun log(logLevel:LogLevel, tag:String, message: String)
}