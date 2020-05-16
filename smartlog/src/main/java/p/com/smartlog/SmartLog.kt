package p.com.smartlog

class SmartLog private constructor() {

    internal var messageFormater = DefaultMessageFormater()
    private var printers = ArrayList<Printer>()

    internal fun log(priority: LogLevel, tag: String, message: String?, throwable: Throwable? = null) {
        printers.forEach {
            it.log(priority, tag, messageFormater.format(message, throwable))
        }
    }

    class Builder{
        private var printers = ArrayList<Printer>()
        fun addPrinter(printer: Printer):Builder{
            printers.add(printer)
            return this
        }

        fun build():SmartLog{
            val configuration = SmartLog()
            configuration.printers = this.printers
            return configuration
        }
    }
}