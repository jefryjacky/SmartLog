package smartlab.androidlibrary.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import smartlab.androidlibrary.smartlog.SmartLog
import smartlab.androidlibrary.smartlog.SmartLogConfig
import smartlab.androidlibrary.smartlog.printers.AndroidLogCatPrinter
import smartlab.androidlibrary.smartlog.printers.FileLogPrinter
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
