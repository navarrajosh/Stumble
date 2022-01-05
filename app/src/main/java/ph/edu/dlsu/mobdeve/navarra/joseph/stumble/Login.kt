package ph.edu.dlsu.mobdeve.navarra.joseph.stumble

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Login : AppCompatActivity() {

    var btn_submit : Button? = null
    var et_username : EditText? = null
    var et_password : EditText? = null
    var tv_signup : Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_submit = findViewById(R.id.btn_submit)
        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        tv_signup = findViewById(R.id.tv_signup)

        var helper = stumbledb(applicationContext)
        var db = helper.readableDatabase


        btn_submit!!.setOnClickListener {
            var args =
                listOf(et_username?.text.toString(), et_password?.text.toString()).toTypedArray()
            var rs = db.rawQuery("SELECT * FROM USERS WHERE UNAME = ? AND PWD = ?", args)
            if (rs.moveToNext()) {
                Toast.makeText(applicationContext, "Login Successful",Toast.LENGTH_LONG).show()
                val gotoStumble = Intent(applicationContext, Stumble::class.java)
                startActivity(gotoStumble)

            }else
                Toast.makeText(applicationContext, "Invalid Credentials",Toast.LENGTH_LONG).show()
        }



        tv_signup!!.setOnClickListener{
            val gotoRegister = Intent(applicationContext, Register::class.java)
            startActivity(gotoRegister)
        }
    }
}