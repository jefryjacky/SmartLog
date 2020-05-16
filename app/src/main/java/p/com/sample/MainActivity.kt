package p.com.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import p.com.smartlog.SmartLog
import p.com.smartlog.SmartLogConfig

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        SmartLog.config = SmartLogConfig.Builder()
            .enableFileLogging(this)
            .build()

        SmartLog.d("jj","test write log to file")
        SmartLog.d("jj","test write log to file2")
    }
}
