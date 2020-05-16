package p.com.smartlog

import com.nhaarman.mockitokotlin2.any
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import p.com.smartlog.LogLevel.DEBUG
import java.lang.RuntimeException
import java.util.*

class SmartLogTest {

    private lateinit var smartLogConfig:SmartLogConfig
    @Mock
    private lateinit var printer:Printer
    @Mock
    private lateinit var mockMessageFormater: DefaultMessageFormater

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        smartLogConfig = SmartLogConfig.Builder()
            .addPrinter(printer)
            .build()
        smartLogConfig.messageFormater = mockMessageFormater
    }

    @Test
    fun logTest(){
        val priority = DEBUG
        val tag = "TAG"
        val message = "message"
        val throwable = RuntimeException()
        val messageFormated = "message and throwable"
        `when`(mockMessageFormater.format(eq(message), eq(throwable), any())).thenReturn(messageFormated)
        smartLogConfig.log(priority, tag, message, throwable)
        verify(printer).log(priority, tag, messageFormated)
    }
}