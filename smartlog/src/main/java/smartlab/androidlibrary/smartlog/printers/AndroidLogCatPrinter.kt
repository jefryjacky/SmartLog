package smartlab.androidlibrary.smartlog.printers

import android.util.Log
import smartlab.androidlibrary.smartlog.LogLevel
import smartlab.androidlibrary.smartlog.Printer

class AndroidLogCatPrinter: Printer {

    override fun log(logLevel: LogLevel, tag: String, message: String) {
        Log.println(logLevel.priority, tag, message)
    }
}