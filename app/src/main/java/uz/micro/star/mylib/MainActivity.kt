package uz.micro.star.mylib

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<uz.micro.star.mixlib.MySwitchButton>(R.id.mySwitch).setOnSwitchListener {
            Log.d("DDDD", "onCreate: ${it}")
        }
        val btn = findViewById<uz.micro.star.mixlib.NextBtnView>(R.id.nextBtn)
        btn.setButtonClickListener {
            Toast.makeText(this, "Yesss", Toast.LENGTH_SHORT).show()
            object : CountDownTimer(1000, 1000) {
                override fun onFinish() {
                    btn.dismissLoader()
                }

                override fun onTick(millisUntilFinished: Long) {

                }
            }.start()
        }
    }
}