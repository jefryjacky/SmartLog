package p.com.smartlog

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import p.com.smartlog.LogLevel.*
import java.lang.RuntimeException

class LogTest{

    @Mock
    lateinit var smartLogConfig:SmartLogConfig
    private val tag = SmartLog.defaultTag
    private val message = "Log's MESSAGE"
    private val throwable = RuntimeException()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        SmartLog.config = smartLogConfig
    }

    @Test
    fun verboseTagMessageTest(){
        SmartLog.v(message)
        verify(smartLogConfig).log(VERBOSE, tag, message, null)
    }

    @Test
    fun verboseTagMessageThrowableTest(){
        SmartLog.v(message, throwable)
        verify(smartLogConfig).log(VERBOSE, tag, message, throwable)
    }

    @Test
    fun debugTagMessageTest(){
        SmartLog.d(message)
        verify(smartLogConfig).log(DEBUG, tag, message, null)
    }

    @Test
    fun debugTagMessageThrowableTest(){
        SmartLog.d(message, throwable)
        verify(smartLogConfig).log(DEBUG, tag, message, throwable)
    }

    @Test
    fun infoTagMessageTest(){
        SmartLog.i(message)
        verify(smartLogConfig).log(INFO, tag, message, null)
    }

    @Test
    fun infoTagMessageThrowableTest(){
        SmartLog.i(message, throwable)
        verify(smartLogConfig).log(INFO, tag, message, throwable)
    }

    @Test
    fun warnTagMessageTest(){
        SmartLog.w(message)
        verify(smartLogConfig).log(WARN, tag, message, null)
    }

    @Test
    fun warnTagThrowableTest(){
        SmartLog.w(throwable)
        verify(smartLogConfig).log(WARN, tag, null, throwable)
    }

    @Test
    fun warnTagMessageThrowableTest(){
        SmartLog.w(message)
        verify(smartLogConfig).log(WARN, tag, message, null)
    }

    @Test
    fun errorTagMessageTest(){
        SmartLog.e(message)
        verify(smartLogConfig).log(ERROR, tag, message, null)
    }

    @Test
    fun errorTagMessageThrowable(){
        SmartLog.e(message, throwable)
        verify(smartLogConfig).log(ERROR, tag, message, throwable)
    }

    @Test
    fun wtfTagMessage(){
        SmartLog.wtf(message)
        verify(smartLogConfig).log(ASSERT, tag, message, null)
    }

    @Test
    fun wtfTagThrowable(){
        SmartLog.wtf(throwable)
        verify(smartLogConfig).log(ASSERT, tag, null, throwable)
    }

    @Test
    fun wtfTagMessageThrowable(){
        SmartLog.wtf(message, throwable)
        verify(smartLogConfig).log(ASSERT, tag, message, throwable)
    }

    @Test
    fun tag(){
        Given("custom tag") {
            val customTag = "custom"

            When("log"){
                SmartLog.tag(customTag).d(message)

                Then("check if logged with with custom tag"){
                    verify(smartLogConfig).log(DEBUG, customTag, message)
                }
            }
        }

    }
}