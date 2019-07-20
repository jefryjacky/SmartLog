package p.com.smartlog

import android.util.Log

class AndroidLogCatPrinter:Printer {

    override fun log(priority: Int, tag: String, message: String) {
        Log.println(priority, tag, message)
    }
}