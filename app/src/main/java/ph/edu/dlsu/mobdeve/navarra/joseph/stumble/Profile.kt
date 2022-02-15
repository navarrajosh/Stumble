package ph.edu.dlsu.mobdeve.navarra.joseph.stumble

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.databinding.ActivityProfileBinding
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.databinding.FragmentProfileBinding
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.model.User
import kotlin.math.sign

class Profile : AppCompatActivity() {
    private var binding: ActivityProfileBinding? = null
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbrf: DatabaseReference
    private lateinit var user: User
    private lateinit var uid: String
    var signout: Button? = null
    var edit: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        signout = findViewById(R.id.btn_logout)
        edit = findViewById(R.id.btn_edit)
        // Inflate the layout for this fragment
        mAuth = FirebaseAuth.getInstance()
        uid = mAuth.currentUser?.uid.toString()
        mDbrf = FirebaseDatabase.getInstance().getReference("user")

        if(uid.isNotEmpty()){
            getUserData()
        }
        edit!!.setOnClickListener {
            startActivity(Intent(this, EditProfile::class.java))
        }

        signout!!.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this, Login::class.java))
        }
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { it ->
            when(it.itemId){
                R.id.home -> startActivity(Intent(this, Home::class.java))
                R.id.chat -> startActivity(Intent(this, Chat::class.java))
            }
            true
        }

    }
    private fun getUserData() {
        mDbrf.child(uid).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(User:: class.java)!!
                binding!!.tvFullname.setText(user.name)
                binding!!.tvAge.setText(user.age)
                binding!!.tvProg.setText(user.prog)
                binding!!.tvUniv.setText(user.univ)
                binding!!.tvOccu.setText(user.occupation)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}