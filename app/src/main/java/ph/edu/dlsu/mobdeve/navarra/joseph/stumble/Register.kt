package ph.edu.dlsu.mobdeve.navarra.joseph.stumble

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Register : AppCompatActivity() {

    var btn_submit : Button? = null
    var et_username : EditText? = null
    var et_password : EditText? = null
    var et_email : EditText? = null
    var et_fullname : EditText? = null
    var et_school : EditText? = null
    var et_program : EditText? = null
    var et_age : EditText? = null
    var et_gender : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btn_submit = findViewById(R.id.btn_submit)
        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        et_email = findViewById(R.id.et_email)
        et_fullname = findViewById(R.id.et_fullname)
        et_school = findViewById(R.id.et_school)
        et_program = findViewById(R.id.et_program)
        et_gender = findViewById(R.id.et_gender)
        et_age = findViewById(R.id.et_age)

        var helper = stumbledb(applicationContext)
        var db = helper.readableDatabase
        var rs = db.rawQuery("SELECT * FROM USERS", null)

        btn_submit!!.setOnClickListener {
            var cv = ContentValues()
            cv.put("UNAME", et_username?.text.toString())
            cv.put("PWD", et_password?.text.toString())
            cv.put("EMAIL", et_email?.text.toString())
            cv.put("fNAME", et_fullname?.text.toString())
            cv.put("UNIV", et_school?.text.toString())
            cv.put("COURSE", et_program?.text.toString())
            cv.put("GENDER", et_gender?.text.toString())
            cv.put("AGE", et_age?.text.toString())
            db.insert("USERS", null, cv)

            et_username?.setText("")
            et_password?.setText("")
            et_email?.setText("")
            et_fullname?.setText("")
            et_school?.setText("")
            et_program?.setText("")
            et_gender?.setText("")
            et_age?.setText("")
            et_username?.requestFocus()

            val gotoLogin = Intent(applicationContext, Login::class.java)
            startActivity(gotoLogin)

        }

    }



}