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
    private val tag = "TAG"
    private val message = "Log's MESSAGE"
    private val throwable = RuntimeException()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        SmartLog.config = smartLogConfig
    }

    @Test
    fun verboseTagMessageTest(){
        SmartLog.v(tag, message)
        verify(smartLogConfig).log(VERBOSE, tag, message, null)
    }

    @Test
    fun verboseTagMessageThrowableTest(){
        SmartLog.v(tag, message, throwable)
        verify(smartLogConfig).log(VERBOSE, tag, message, throwable)
    }

    @Test
    fun debugTagMessageTest(){
        SmartLog.d(tag, message)
        verify(smartLogConfig).log(DEBUG, tag, message, null)
    }

    @Test
    fun debugTagMessageThrowableTest(){
        SmartLog.d(tag, message, throwable)
        verify(smartLogConfig).log(DEBUG, tag, message, throwable)
    }

    @Test
    fun infoTagMessageTest(){
        SmartLog.i(tag, message)
        verify(smartLogConfig).log(INFO, tag, message, null)
    }

    @Test
    fun infoTagMessageThrowableTest(){
        SmartLog.i(tag, message, throwable)
        verify(smartLogConfig).log(INFO, tag, message, throwable)
    }

    @Test
    fun warnTagMessageTest(){
        SmartLog.w(tag, message)
        verify(smartLogConfig).log(WARN, tag, message, null)
    }

    @Test
    fun warnTagThrowableTest(){
        SmartLog.w(tag, throwable)
        verify(smartLogConfig).log(WARN, tag, null, throwable)
    }

    @Test
    fun warnTagMessageThrowableTest(){
        SmartLog.w(tag, message)
        verify(smartLogConfig).log(WARN, tag, message, null)
    }

    @Test
    fun errorTagMessageTest(){
        SmartLog.e(tag, message)
        verify(smartLogConfig).log(ERROR, tag, message, null)
    }

    @Test
    fun errorTagMessageThrowable(){
        SmartLog.e(tag, message, throwable)
        verify(smartLogConfig).log(ERROR, tag, message, throwable)
    }

    @Test
    fun wtfTagMessage(){
        SmartLog.wtf(tag, message)
        verify(smartLogConfig).log(ASSERT, tag, message, null)
    }

    @Test
    fun wtfTagThrowable(){
        SmartLog.wtf(tag, throwable)
        verify(smartLogConfig).log(ASSERT, tag, null, throwable)
    }

    @Test
    fun wtfTagMessageThrowable(){
        SmartLog.wtf(tag, message, throwable)
        verify(smartLogConfig).log(ASSERT, tag, message, throwable)
    }
}