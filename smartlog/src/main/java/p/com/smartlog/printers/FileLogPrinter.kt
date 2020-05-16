package p.com.smartlog.printers

import p.com.smartlog.LogLevel
import p.com.smartlog.Printer
import java.io.File
import java.io.OutputStream
import java.lang.StringBuilder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class FileLogPrinter(private var directory: File): Printer {

    private var file: File = getLastFile()
    private var maxLength = 1024000
    private var maxAge: Long = 20 * 24 * 3600 * 1000 // 20 days
    private var maxFiles = 15

    override fun log(logLevel: LogLevel, tag: String, message: String) {
        if(file.length() > maxLength){
            cleanOldfile()
            file = createNewFile()
        }
        val builder = StringBuilder()
        builder.append("\n[")
        builder.append(logLevel.name)
        builder.append("] :  ")
        builder.append(message)
        file.appendText(builder.toString())
    }

    private fun createNewFile():File {
        cleanOldfile()
        val dateFormat = DateFormat.getDateTimeInstance()
        val filename = dateFormat.format(Date())
        val newFile = File("${directory}/${filename}.txt")
        return newFile.also {
            it.createNewFile()
        }
    }

    private fun getLastFile():File{
        if(directory.listFiles().isNotEmpty()) {
            directory.listFiles().forEach {
                if(it.length() <= maxLength){
                    return it
                }
            }
        }
        return createNewFile()
    }

    private fun cleanOldfile(){
        if(directory.listFiles().size > maxFiles) {
            directory.listFiles().forEach {
                val age = System.currentTimeMillis() - it.lastModified()
                if (age > maxAge) {
                    it.delete()
                }
            }
        }
    }
}