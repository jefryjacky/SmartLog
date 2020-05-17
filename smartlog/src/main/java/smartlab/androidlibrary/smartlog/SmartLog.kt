package smartlab.androidlibrary.smartlog

import smartlab.androidlibrary.smartlog.LogLevel.*
import java.util.regex.Pattern

object SmartLog:ISmartLog {

    private val ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$")

    private var tag:String? = null

    internal val defaultTag: String
        get() {
            return Throwable().stackTrace
                .first {
                    it.className !in javaClass.name
                }.let(::createStackElementTag)
        }

    /**
     * Extract the tag which should be used for the message from the `element`. By default
     * this will use the class name without any anonymous class suffixes (e.g., `Foo$1`
     * becomes `Foo`).
     */
    private fun createStackElementTag(element: StackTraceElement):String{
        var tag = element.className.substringAfterLast('.')
        val anonymous = ANONYMOUS_CLASS.matcher(tag)
        if(anonymous.find()){
            tag = anonymous.replaceAll("")
        }
        return tag
    }

    var config:SmartLogConfig = SmartLogConfig.Builder().build()

    fun tag(tag: String):ISmartLog{
        this.tag = tag
        return this
    }

    override fun v(message:String){
        log(VERBOSE, tag, message)
    }

    override fun v(message:String, throwable: Throwable){
        log(VERBOSE, tag, message, throwable)
    }

    override fun d(message:String){
        log(DEBUG, tag, message)
    }

    override fun d(message:String, throwable: Throwable){
        log(DEBUG, tag, message, throwable)
    }

    override fun i(message: String){
        log(INFO, tag, message)
    }

    override fun i(message: String, throwable: Throwable){
        log(INFO, tag, message, throwable)
    }

    override fun w(message:String){
        log(WARN, tag, message)
    }

    override fun w(throwable: Throwable){
        log(WARN, tag, null, throwable)
    }

    override fun w(message:String, throwable: Throwable){
        log(WARN, tag, message, throwable)
    }

    override fun e(message:String){
        log(ERROR, tag, message)
    }

    override fun e(message:String, throwable: Throwable){
        log(ERROR, tag, message, throwable)
    }

    override fun wtf(message: String){
        log(ASSERT, tag, message)
    }

    override fun wtf(throwable: Throwable){
        log(ASSERT, tag, null, throwable)
    }

    override fun wtf( message: String, throwable: Throwable){
        log(ASSERT, tag, message, throwable)
    }

    private fun log(priority:LogLevel, tag:String?, message: String?, throwable: Throwable? = null){
        config.log(priority, tag?:defaultTag, message, throwable)
        this.tag = null
    }
}