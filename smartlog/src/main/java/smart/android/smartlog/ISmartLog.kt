package smart.android.smartlog

interface ISmartLog {
    fun v(message:String)
    fun v(message:String, throwable: Throwable)
    fun d(message:String)
    fun d(message:String, throwable: Throwable)
    fun i(message: String)
    fun i(message: String, throwable: Throwable)
    fun w(message:String)
    fun w(throwable: Throwable)
    fun w(message:String, throwable: Throwable)
    fun e(message:String)
    fun e(message:String, throwable: Throwable)
    fun wtf(message: String)
    fun wtf(throwable: Throwable)
    fun wtf(message: String, throwable: Throwable)
}