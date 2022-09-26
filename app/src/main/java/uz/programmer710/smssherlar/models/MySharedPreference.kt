package uz.programmer710.smssherlar.models

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object MySharedPreference {
    private const val NAME = "catch_file_name"
    private const val MODE = Context.MODE_PRIVATE

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context){
        sharedPreferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation:(SharedPreferences.Editor) -> Unit){
        val editor = edit()
        operation(editor)
        editor.apply(

        )
    }

    var list: ArrayList<Info>
        get() = stringToList(sharedPreferences.getString("list", "[]")!!)
        set(value) = sharedPreferences.edit {
            if (value != null) {
                it.putString("list", listToString(value))
            }
        }

    fun stringToList(str:String):ArrayList<Info>{
        val gson = Gson()
        val list = ArrayList<Info>()

        val type = object : TypeToken<ArrayList<Info>>(){}.type
        list.addAll(gson.fromJson(str, type))

        return list
    }

    fun listToString(list: ArrayList<Info>): String {
        return Gson().toJson(list)
    }
}