package p.com.smartlog

import org.hamcrest.CoreMatchers.`is`
import org.junit.Test
import java.lang.RuntimeException
import java.lang.StringBuilder
import org.junit.Assert.*
import java.text.DateFormat
import java.util.*

class DefaultMessageFormaterTest {

    @Test
    fun givenMessageAndThrowableTest(){
        val formater = DefaultMessageFormater()
        val message = "message"
        val throwable = RuntimeException()
        val messageBuilder = StringBuilder()
        val date = Date()

        val dateFormat = DateFormat.getDateTimeInstance()
        messageBuilder.append("[")
        messageBuilder.append(dateFormat.format(Date()))
        messageBuilder.append("], ")

        messageBuilder.append("[thread: ")
        messageBuilder.append(Thread.currentThread().name)
        messageBuilder.append("] - ")

        messageBuilder.append(message)
        messageBuilder.append("\n")
        messageBuilder.append(throwable.toString())
        val expectedOutputMessage = messageBuilder.toString()
        val outputMessage = formater.format(message, throwable, date)
        assertThat(outputMessage, `is`(expectedOutputMessage))
    }

    @Test
    fun givenThrowableTest(){
        val formater = DefaultMessageFormater()
        val throwable = RuntimeException()
        val messageBuilder = StringBuilder()
        val date = Date()

        val dateFormat = DateFormat.getDateTimeInstance()
        messageBuilder.append("[")
        messageBuilder.append(dateFormat.format(Date()))
        messageBuilder.append("], ")

        messageBuilder.append("[thread: ")
        messageBuilder.append(Thread.currentThread().name)
        messageBuilder.append("] - ")

        messageBuilder.append("\n")
        messageBuilder.append(throwable.toString())
        val expectedOutputMessage = messageBuilder.toString()
        val outputMessage = formater.format(null, throwable = throwable, date = date)
        assertThat(outputMessage, `is`(expectedOutputMessage))
    }

    @Test
    fun givenMessageTest(){
        val formater = DefaultMessageFormater()
        val message = "message"
        val messageBuilder = StringBuilder()
        val date = Date()

        val dateFormat = DateFormat.getDateTimeInstance()
        messageBuilder.append("[")
        messageBuilder.append(dateFormat.format(Date()))
        messageBuilder.append("], ")

        messageBuilder.append("[thread: ")
        messageBuilder.append(Thread.currentThread().name)
        messageBuilder.append("] - ")

        messageBuilder.append(message)
        val expectedOutputMessage = messageBuilder.toString()
        val outputMessage = formater.format(message, date = date)
        assertThat(outputMessage, `is`(expectedOutputMessage))
    }
}