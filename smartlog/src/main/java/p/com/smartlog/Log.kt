package p.com.smartlog

object Log {

    var smartLog:SmartLog = SmartLog.Builder().build()

    val VERBOSE = 2
    val DEBUG = 3
    val INFO = 4
    val WARN = 5
    val ERROR = 6
    val ASSERT = 7

    fun v(tag:String, message:String){
        log(VERBOSE, tag, message)
    }

    fun v(tag:String, message:String, throwable: Throwable){
        log(VERBOSE, tag, message, throwable)
    }

    fun d(tag:String, message:String){
        log(DEBUG, tag, message)
    }

    fun d(tag:String, message:String, throwable: Throwable){
        log(DEBUG, tag, message, throwable)
    }

    fun i(tag:String, message: String){
        log(INFO, tag, message)
    }

    fun i(tag: String, message: String, throwable: Throwable){
        log(INFO, tag, message, throwable)
    }

    fun w(tag:String, message:String){
        log(WARN, tag, message)
    }

    fun w(tag:String, throwable: Throwable){
        log(WARN, tag, null, throwable)
    }

    fun w(tag:String, message:String, throwable: Throwable){
        log(WARN, tag, message, throwable)
    }

    fun e(tag:String, message:String){
        log(ERROR, tag, message)
    }

    fun e(tag:String, message:String, throwable: Throwable){
        log(ERROR, tag, message, throwable)
    }

    fun wtf(tag:String, message: String){
        log(ASSERT, tag, message)
    }

    fun wtf(tag:String, throwable: Throwable){
        log(ASSERT, tag, null, throwable)
    }

    fun wtf(tag:String, message: String, throwable: Throwable){
        log(ASSERT, tag, message, throwable)
    }

    private fun log(priority:Int, tag:String, message: String?, throwable: Throwable? = null){
        smartLog.log(priority, tag, message, throwable)
    }
}