package p.com.smartlog.printers

import kotlinx.coroutines.*
import p.com.smartlog.LogLevel
import p.com.smartlog.Printer
import java.io.File
import java.lang.StringBuilder
import java.text.DateFormat
import java.util.*
import java.util.concurrent.Executors

class FileLogPrinter(private var directory: File): Printer {

    private var maxLength = 1024000
    private var file: File = getUnfullFile(maxLength)
    private var maxAge: Long = 20 * 24 * 3600 * 1000 // 20 days
    private var maxFiles = 15
    private val dispatcher = Executors.newFixedThreadPool(1).asCoroutineDispatcher()

    override fun log(logLevel: LogLevel, tag: String, message: String) {
        GlobalScope.launch(dispatcher) {
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

    /**
     * last file is ussualy unfull
     */
    private fun getUnfullFile(maxLength: Int):File{
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