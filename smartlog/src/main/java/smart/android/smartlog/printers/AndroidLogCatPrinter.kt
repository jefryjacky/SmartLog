package smart.android.smartlog.printers

import android.util.Log
import smart.android.smartlog.LogLevel
import smart.android.smartlog.Printer

class AndroidLogCatPrinter: Printer {

    override fun log(logLevel: LogLevel, tag: String, message: String) {
        Log.println(logLevel.priority, tag, message)
    }
}