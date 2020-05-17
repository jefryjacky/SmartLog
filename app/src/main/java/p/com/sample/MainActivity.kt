package p.com.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import p.com.smartlog.SmartLog
import p.com.smartlog.SmartLogConfig
import p.com.smartlog.printers.AndroidLogCatPrinter
import p.com.smartlog.printers.FileLogPrinter
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val folder = "SmartLog"
        val dir = getExternalFilesDir(folder)?: File("${filesDir}/$folder/")
        if(!dir.exists()){
            dir.mkdir()
        }

        SmartLog.config = SmartLogConfig.Builder()
            .addPrinter(AndroidLogCatPrinter())
            .addPrinter(FileLogPrinter(dir))
            .build()

        logBtn.setOnClickListener {
            SmartLog.tag("customtag").d(message.text.toString())
            SmartLog.d(message.text.toString())
        }

    }
}
