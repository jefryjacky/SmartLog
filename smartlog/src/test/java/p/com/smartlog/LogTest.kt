package p.com.smartlog

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.lang.RuntimeException

class LogTest{

    @Mock
    lateinit var smartLog:SmartLog
    private val tag = "TAG"
    private val message = "Log's MESSAGE"
    private val throwable = RuntimeException()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        Log.smartLog = smartLog
    }

    @Test
    fun verboseTagMessageTest(){
        Log.v(tag, message)
        verify(smartLog).log(Log.VERBOSE, tag, message, null)
    }

    @Test
    fun verboseTagMessageThrowableTest(){
        Log.v(tag, message, throwable)
        verify(smartLog).log(Log.VERBOSE, tag, message, throwable)
    }

    @Test
    fun debugTagMessageTest(){
        Log.d(tag, message)
        verify(smartLog).log(Log.DEBUG, tag, message, null)
    }

    @Test
    fun debugTagMessageThrowableTest(){
        Log.d(tag, message, throwable)
        verify(smartLog).log(Log.DEBUG, tag, message, throwable)
    }

    @Test
    fun infoTagMessageTest(){
        Log.i(tag, message)
        verify(smartLog).log(Log.INFO, tag, message, null)
    }

    @Test
    fun infoTagMessageThrowableTest(){
        Log.i(tag, message, throwable)
        verify(smartLog).log(Log.INFO, tag, message, throwable)
    }

    @Test
    fun warnTagMessageTest(){
        Log.w(tag, message)
        verify(smartLog).log(Log.WARN, tag, message, null)
    }

    @Test
    fun warnTagThrowableTest(){
        Log.w(tag, throwable)
        verify(smartLog).log(Log.WARN, tag, null, throwable)
    }

    @Test
    fun warnTagMessageThrowableTest(){
        Log.w(tag, message)
        verify(smartLog).log(Log.WARN, tag, message, null)
    }

    @Test
    fun errorTagMessageTest(){
        Log.e(tag, message)
        verify(smartLog).log(Log.ERROR, tag, message, null)
    }

    @Test
    fun errorTagMessageThrowable(){
        Log.e(tag, message, throwable)
        verify(smartLog).log(Log.ERROR, tag, message, throwable)
    }

    @Test
    fun wtfTagMessage(){
        Log.wtf(tag, message)
        verify(smartLog).log(Log.ASSERT, tag, message, null)
    }

    @Test
    fun wtfTagThrowable(){
        Log.wtf(tag, throwable)
        verify(smartLog).log(Log.ASSERT, tag, null, throwable)
    }

    @Test
    fun wtfTagMessageThrowable(){
        Log.wtf(tag, message, throwable)
        verify(smartLog).log(Log.ASSERT, tag, message, throwable)
    }
}