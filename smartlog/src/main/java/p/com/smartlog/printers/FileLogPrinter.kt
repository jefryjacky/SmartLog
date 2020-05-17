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

    private var file: File? = null
    private val dispatcher = Executors.newFixedThreadPool(1).asCoroutineDispatcher()

    override fun log(logLevel: LogLevel, tag: String, message: String) {
        GlobalScope.launch(dispatcher) {
            if(file == null){
                file = getUnfullFile()
            }

            if(file!!.length() >= MAX_LENGTH){
                cleanOldfile()
                file = createNewFile()
            }
            val builder = StringBuilder()
            builder.append("\n[")
            builder.append(logLevel.name)
            builder.append("] :  ")
            builder.append(message)
            file!!.appendText(builder.toString())
        }
    }

    private fun createNewFile():File {
        return File(generateNewFilePath()).also {
            it.createNewFile()
        }
    }

    internal fun generateNewFilePath():String{
        val dateFormat = DateFormat.getDateTimeInstance()
        val filename = dateFormat.format(Date())
        return "${directory}/${filename}.txt"
    }

    /**
     * last file is ussualy unfull
     */
    internal fun getUnfullFile():File{
        if(directory.listFiles().isNotEmpty()) {
            directory.listFiles().forEach {
                if(it.length() < MAX_LENGTH){
                    return it
                }
            }
        }
        return createNewFile()
    }

    internal fun cleanOldfile(){
        directory.listFiles().forEach {
            val currentTime = System.currentTimeMillis()
            val lastModified = it.lastModified()
            val age = currentTime - lastModified
            if (age > MAX_AGE) {
                it.delete()
            }
        }
    }

    companion object{
        internal const val MAX_LENGTH = 1024000
        internal const val MAX_AGE: Long = 1728000000 // 20 days
    }
}