package p.com.smartlog

class SmartLog {
    internal fun log(priority: Int, tag: String, message: String?, throwable: Throwable?) {
       
    }

    class Builder{
        fun build():SmartLog{
            val configuration = SmartLog()
            return configuration
        }
    }
}