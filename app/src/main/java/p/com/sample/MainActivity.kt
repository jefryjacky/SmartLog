package p.com.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import p.com.smartlog.printers.AndroidLogCatPrinter
import p.com.smartlog.Log
import p.com.smartlog.SmartLog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.smartLog = SmartLog.Builder()
            .addPrinter(AndroidLogCatPrinter())
            .enableFileLogging(this)
            .build()

        Log.d("jj","test write log to file")
        Log.d("jj","test write log to file2")
    }
}
