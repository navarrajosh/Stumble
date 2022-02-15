package ph.edu.dlsu.mobdeve.navarra.joseph.stumble

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import org.w3c.dom.Text

class Login : AppCompatActivity() {

    var btn_submit: Button? = null
    var et_email: EditText? = null
    var et_password: EditText? = null
    var tv_signup: Button? = null
    var nameName: TextView? = null
    var courseName: TextView? = null
    var schoolName: TextView? = null
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        btn_submit = findViewById(R.id.btn_submit)
        et_email = findViewById(R.id.et_email)
        et_password = findViewById(R.id.et_password)
        tv_signup = findViewById(R.id.tv_signup)
        /*
        nameName = findViewById(R.id.nameName)
        courseName = findViewById(R.id.courseName)
        schoolName = findViewById(R.id.schoolName)
        nameName!!.setText(rs.getString(4))
        schoolName!!.setText(rs.getString(5))
        courseName!!.setText(rs.getString(6))
         */
        /*
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
        }*/

        tv_signup!!.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
        btn_submit!!.setOnClickListener {
            val email = et_email?.text.toString().trim()
            val password = et_password?.text.toString().trim()

            login(email, password)
        }
    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@Login, Home::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login, "User does not Exist", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{ e->
                Toast.makeText(this@Login, "${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}