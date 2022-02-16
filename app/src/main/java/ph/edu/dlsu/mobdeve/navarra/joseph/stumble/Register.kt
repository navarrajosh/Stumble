package ph.edu.dlsu.mobdeve.navarra.joseph.stumble

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
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
    var btn_upload: Button? = null
    var iv_userpic: ImageView? = null
    private var status = "None"
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var strdb: StorageReference
    var uri: Uri? = null

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
        btn_upload = findViewById(R.id.btn_upload)
        iv_userpic = findViewById(R.id.iv_userpic)
        btn_upload!!.setOnClickListener{
            chooseimage()
        }
        
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

    private fun chooseimage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 1001)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1001 && resultCode == RESULT_OK){
            iv_userpic!!.setImageURI(data?.data)
            uri = data?.data
        }
    }

    private fun register(name:String, email: String, password: String, username: String, occupation: String, univ: String, gen: String, age: String, prog: String, status: String ) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name,email, username,occupation, univ, gen,age,prog, status, mAuth.currentUser?.uid!!)
                    addImage()
                    val intent = Intent(this@Register, Home::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Register, "Some error occurred", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{ e->
            Toast.makeText(this@Register, "${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun addImage() {
        uri
        strdb = FirebaseStorage.getInstance().getReference("images/" + mAuth.currentUser?.uid)
        strdb.putFile(uri!!).addOnSuccessListener {
            Toast.makeText(this@Register, "Image Uploaded", Toast.LENGTH_SHORT).show()
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