package uz.programmer710.smssherlar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uz.programmer710.smssherlar.models.MyObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyObject.loadSherlar()
        MyObject.list_Yangi_Sherlar

//        val saqlash = MySharedPreference.list
//        saqlash.addAll(MyObject.list_Saqlangan_Sherlar)
//        MySharedPreference.list = saqlash
    }
}