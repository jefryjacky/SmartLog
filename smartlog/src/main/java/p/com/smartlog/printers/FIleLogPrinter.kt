package p.com.smartlog.printers

import p.com.smartlog.LogLevel
import p.com.smartlog.Printer
import java.io.File
import java.io.OutputStream

class FIleLogPrinter(private val file: File): Printer {

    private var maxLength = 1024000
    private var directory:String? = null
    private var maxNumberOfFiles = 10

    override fun log(priority: LogLevel, tag: String, message: String) {

    }

}