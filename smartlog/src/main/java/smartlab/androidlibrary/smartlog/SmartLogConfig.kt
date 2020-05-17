package smartlab.androidlibrary.smartlog

import kotlin.collections.ArrayList

class SmartLogConfig private constructor() {

    internal var messageFormater = DefaultMessageFormater()
    private var printers: List<Printer>? = null

    internal fun log(priority: LogLevel, tag: String, message: String?, throwable: Throwable? = null) {
        printers?.forEach {
            it.log(priority, tag, messageFormater.format(message, throwable))
        }
    }

    class Builder{
        private var printers = ArrayList<Printer>()

        fun addPrinter(printer: Printer): Builder{
            printers.add(printer)
            return this
        }

        fun build():SmartLogConfig{
            val configuration = SmartLogConfig()
            configuration.printers = this.printers
            return configuration
        }
    }
}