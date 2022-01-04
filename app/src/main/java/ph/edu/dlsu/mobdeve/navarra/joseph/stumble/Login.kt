package ph.edu.dlsu.mobdeve.navarra.joseph.stumble

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {

    var btn_submit : Button? = null
    var et_username : EditText? = null
    var et_password : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_submit = findViewById(R.id.btn_submit)
        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)

        var helper = stumbledb(applicationContext)
        var db = helper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM USERS",null)

        btn_submit!!.setOnClickListener{
            var cv = ContentValues()
            cv.put("UNAME", et_username?.text.toString())
            cv.put("PWD",et_password?.text.toString())
            db.insert("USERS",null,cv)

            et_username?.setText("")
            et_password?.setText("")
            et_username?.requestFocus()
        }
    }
}