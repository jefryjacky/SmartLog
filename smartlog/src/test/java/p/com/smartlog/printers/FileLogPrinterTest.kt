package p.com.smartlog.printers

import com.nhaarman.mockitokotlin2.*
import org.hamcrest.CoreMatchers.`is`
import org.junit.Test

import org.junit.Assert.*
import p.com.smartlog.Given
import p.com.smartlog.Scenario
import p.com.smartlog.Then
import p.com.smartlog.When
import java.io.File
import java.util.*

class FileLogPrinterTest {

    @Test
    fun generateNewFile() {
        Given("directory path"){
            val directory = File("test/")
            val printer = FileLogPrinter(directory)

            When("generate new file path"){
                val newFilePath = printer.generateNewFilePath()

                Then("check if file path is start with directory"){
                    assertTrue(newFilePath.startsWith(directory.path))
                }

                Then("check file extension is .txt"){
                    assertTrue(newFilePath.endsWith(".txt"))
                }
            }
        }
    }

    @Test
    fun getUnfullFile() {
        Given("list of log files"){
            val directory = mock<File>()
            val printer = FileLogPrinter(directory)

            val fileEdxeedMaxSize = mock<File>()
            whenever(fileEdxeedMaxSize.length()).thenReturn((FileLogPrinter.MAX_LENGTH+1).toLong())
            val fileEqualMaxSize = mock<File>()
            whenever(fileEqualMaxSize.length()).thenReturn((FileLogPrinter.MAX_LENGTH).toLong())
            val fileLessMaxSize = mock<File>()
            whenever(fileLessMaxSize.length()).thenReturn((FileLogPrinter.MAX_LENGTH-1).toLong())

            val listFile = arrayOf(fileEdxeedMaxSize, fileEqualMaxSize, fileLessMaxSize)
            whenever(directory.listFiles()).thenReturn(listFile)

            When("get unfull file"){
                val lastfile = printer.getUnfullFile()

                Then("check it file less than max size") {
                    assertThat(lastfile, `is`(fileLessMaxSize))
                }
            }
        }
    }

    @Test
    fun cleanOldFile(){
        Scenario("exceed max file") {
            Given("4 list files and max file 2") {
                val directory = mock<File>()
                val printer = FileLogPrinter(directory, 2)

                val file1 = createFileWithAge(-5)
                val file2 = createFileWithAge(-21)
                val file3 = createFileWithAge(-10)
                val file4 = createFileWithAge(-30)
                val listFile = arrayOf(file1, file2, file3, file4)
                whenever(directory.listFiles()).thenReturn(listFile)

                When("clean older files") {
                    printer.cleanOldfile()

                    Then("file 1 and file 3 is not deleted because younger") {
                        verify(file1, never()).delete()
                        verify(file3, never()).delete()
                    }

                    Then("file 2 and file 4 will be deleted because older") {
                        verify(file2, times(1)).delete()
                        verify(file4, times(1)).delete()
                    }
                }
            }
        }

        Scenario("no exceed max file"){
            Given("1 list files and max file default (15 files)"){
                val directory = mock<File>()
                val printer = FileLogPrinter(directory)

                val file1 = createFileWithAge(-5)
                val file2 = createFileWithAge(-21)
                val file3 = createFileWithAge(-10)
                val file4 = createFileWithAge(-30)
                val listFile = arrayOf(file1, file2, file3, file4)
                whenever(directory.listFiles()).thenReturn(listFile)

                When("clean older files") {
                    printer.cleanOldfile()


                    Then("all file is not deleted") {
                        verify(file1, never()).delete()
                        verify(file2, never()).delete()
                        verify(file3, never()).delete()
                        verify(file4, never()).delete()
                    }
                }
            }
        }
    }

    private fun createFileWithAge(day: Int):File{
        val file = spy(File(""))
        var calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, day) // age 5 days
        val time = calendar.timeInMillis
        whenever(file.lastModified()).thenReturn(time)
        return file
    }

}