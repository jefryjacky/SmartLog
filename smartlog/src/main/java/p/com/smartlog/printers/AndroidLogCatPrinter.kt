package p.com.smartlog.printers

import android.util.Log
import p.com.smartlog.LogLevel
import p.com.smartlog.Printer

class AndroidLogCatPrinter: Printer {

    override fun log(logLevel: LogLevel, tag: String, message: String) {
        Log.println(logLevel.priority, tag, message)
    }
}