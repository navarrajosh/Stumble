package ph.edu.dlsu.mobdeve.navarra.joseph.stumble

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.model.User

class Register : AppCompatActivity() {

    var btn_submit : Button? = null
    var et_username : EditText? = null
    var et_password : EditText? = null
    var et_email : EditText? = null
    var et_occupation: EditText? = null
    var et_fullname : EditText? = null
    var et_school : EditText? = null
    var et_program : EditText? = null
    var et_age : EditText? = null
    var et_gender : EditText? = null
    private var status = "None"
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mAuth = FirebaseAuth.getInstance()
        btn_submit = findViewById(R.id.btn_submit)
        et_username = findViewById(R.id.et_username)
        et_password = findViewById(R.id.et_password)
        et_email = findViewById(R.id.et_email)
        et_fullname = findViewById(R.id.et_fullname)
        et_occupation = findViewById(R.id.et_occupation)
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
            val name = et_fullname?.text.toString()
            val username = et_username?.text.toString()
            val univ = et_school?.text.toString()
            val occupation = et_occupation?.text.toString()
            val gen = et_gender?.text.toString()
            val age = et_age?.text.toString()
            val prog = et_program?.text.toString()

            
            register(name,email, password,username,occupation,univ,gen,age,prog,status)
        }

    }

    private fun register(name:String, email: String, password: String, username: String, occupation: String, univ: String, gen: String, age: String, prog: String, status: String ) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name,email, username,occupation, univ, gen,age,prog, status, mAuth.currentUser?.uid!!)
                    val intent = Intent(this@Register, Stumble::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Register, "Some error occurred", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{ e->
            Toast.makeText(this@Register, "${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun addUserToDatabase(
        name: String,
        email: String,
        username: String,
        occupation: String,
        univ: String,
        gen: String,
        age: String,
        prog: String,
        status:String,
        uid: String
    ) {
        mDbRef = FirebaseDatabase.getInstance().getReference()

        mDbRef.child("user").child(uid).setValue(User(name,email,uid,occupation,username,univ,gen,age,prog,status))
    }


}