package ph.edu.dlsu.mobdeve.navarra.joseph.stumble

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

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

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mAuth = FirebaseAuth.getInstance()
        btn_submit = findViewById(R.id.btn_submit)
        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        et_email = findViewById(R.id.et_email)
        et_fullname = findViewById(R.id.et_fullname)
        et_school = findViewById(R.id.et_school)
        et_program = findViewById(R.id.et_program)
        et_gender = findViewById(R.id.et_gender)
        et_age = findViewById(R.id.et_age)
        /*
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

        }*/
        
        btn_submit!!.setOnClickListener { 
            val email = et_email?.text.toString()
            val password = et_password?.text.toString()
            
            register(email, password)
        }

    }

    private fun register(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val intent = Intent(this@Register, Stumble::class.java)
                    startActivity(intent)
                } else {
                    Log.e("login","data: $mAuth")
                    Log.e("login","data: $mAuth")
                   Toast.makeText(this@Register, "Some error occurred", Toast.LENGTH_SHORT).show()
                }
            }
    }
    /*task.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
        @Override
        public void onSuccess(AuthResult authResult) {
            // Task completed successfully
            // ...
        }
    });
    task.addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            // Task failed with an exception
            // ...
        }
    });
    https://developers.google.com/android/guides/tasks
    */


}