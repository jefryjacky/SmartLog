package p.com.smartlog

import org.hamcrest.CoreMatchers.`is`
import org.junit.Test
import java.lang.RuntimeException
import java.lang.StringBuilder
import org.junit.Assert.*

class DefaultMessageFormaterTest {

    @Test
    fun givenMessageAndThrowableTest(){
        val formater = DefaultMessageFormater()
        val message = "message"
        val throwable = RuntimeException()
        val messageBuilder = StringBuilder()
        messageBuilder.append("thread: ")
        messageBuilder.append(Thread.currentThread().name)
        messageBuilder.append(", ")
        messageBuilder.append(message)
        messageBuilder.append("\n")
        messageBuilder.append(throwable.toString())
        val expectedOutputMessage = messageBuilder.toString()
        val outputMessage = formater.format(message, throwable)
        assertThat(outputMessage, `is`(expectedOutputMessage))
    }

    @Test
    fun givenThrowableTest(){
        val formater = DefaultMessageFormater()
        val throwable = RuntimeException()
        val messageBuilder = StringBuilder()
        messageBuilder.append("thread: ")
        messageBuilder.append(Thread.currentThread().name)
        messageBuilder.append(", ")
        messageBuilder.append("\n")
        messageBuilder.append(throwable.toString())
        val expectedOutputMessage = messageBuilder.toString()
        val outputMessage = formater.format(null, throwable = throwable)
        assertThat(outputMessage, `is`(expectedOutputMessage))
    }

    @Test
    fun givenMessageTest(){
        val formater = DefaultMessageFormater()
        val message = "message"
        val messageBuilder = StringBuilder()
        messageBuilder.append("thread: ")
        messageBuilder.append(Thread.currentThread().name)
        messageBuilder.append(", ")
        messageBuilder.append(message)
        val expectedOutputMessage = messageBuilder.toString()
        val outputMessage = formater.format(message)
        assertThat(outputMessage, `is`(expectedOutputMessage))
    }
}