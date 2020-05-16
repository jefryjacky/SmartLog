package p.com.smartlog

import android.content.Context
import p.com.smartlog.printers.AndroidLogCatPrinter
import p.com.smartlog.printers.FileLogPrinter
import java.io.File

class SmartLog private constructor() {

    internal var messageFormater = DefaultMessageFormater()
    private var printers = ArrayList<Printer>()

    internal fun log(priority: LogLevel, tag: String, message: String?, throwable: Throwable? = null) {
        printers.forEach {
            it.log(priority, tag, messageFormater.format(message, throwable))
        }
    }

    class Builder{
        private var printers = arrayListOf<Printer>(AndroidLogCatPrinter())

        fun enableFileLogging(context: Context):Builder{
            val folder = "SmartLog"
            val dir = context.getExternalFilesDir(folder)?:File("${context.filesDir}/$folder/")
            if(!dir.exists()){
                dir.mkdir()
            }
            printers.add(FileLogPrinter(dir))
            return this
        }

        fun enableFileLogging(dir:File):Builder{
            if(!dir.exists()){
                dir.mkdir()
            }
            printers.add(FileLogPrinter(dir))
            return this
        }

        fun build():SmartLog{
            val configuration = SmartLog()
            configuration.printers = this.printers
            return configuration
        }
    }
}