package ph.edu.dlsu.mobdeve.navarra.joseph.stumble.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import org.w3c.dom.Text
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.R
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.databinding.FragmentHomeBinding
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.databinding.FragmentProfileBinding
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.model.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var detector: GestureDetectorCompat
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var user: User
    private lateinit var uid: String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

/*
            val view =  inflater.inflate(R.layout.fragment_home, container, false)

            view.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent): Boolean {
                    return if (detector.onTouchEvent(event)) {
                        true
                    }else{
                        super.onTouchEvent(event)
                }
            }})*/
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        mAuth = FirebaseAuth.getInstance()
        uid = mAuth.currentUser?.uid.toString()
        database = FirebaseDatabase.getInstance().getReference("user")
        val nameName : TextView = binding.nameName
        val courseName : TextView = binding.courseName
        val schoolName : TextView = binding.schoolName
        val ageAge: TextView = binding.ageAge
        if(uid.isNotEmpty()){
            getUserData()
        }
        return binding.root
        }
    private fun getUserData() {
        database.child(uid).addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(User:: class.java)!!
                binding.nameName.setText(user.name)
                binding.ageAge.setText(user.age)
                binding.courseName.setText(user.prog)
                binding.schoolName.setText(user.univ)
                binding.genGen.setText(user.gen)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
/*
    private fun readData() {
        database = FirebaseDatabase.getInstance().getReference("user")
        database.child(uid).get().addOnSuccessListener {
            if(it.exists()){
                val name = it.child("name").value
                val gen = it.child("gen").value
                val age = it.child("age").value
                val univ = it.child("univ").value
                val prog = it.child("prog").value

                binding.nameName.setText(name)
                binding.ageAge.setText(age)


            }else{
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
            }
        }

    }*/


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    /*
    inner class GestureListener: GestureDetector.SimpleOnGestureListener(){
        private val SWIPE_THRESHOLD = 100
        private val SWIPE_VELOCITY_THRESHOLD = 100
        override fun onFling(
            downEvent: MotionEvent?,
            moveEvent: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var diffX = moveEvent?.x?.minus(downEvent!!.x) ?: 0.0F
            var diffY = moveEvent?.y?.minus(downEvent!!.y) ?: 0.0F

            if(Math.abs(diffX) > Math.abs(diffY)){
                //for swipe left and right
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD){
                    if(diffX > 0){
                        this@HomeFragment.onSwipeRight()
                    }else {
                        this@HomeFragment.onSwipeLeft()
                    }
                }
            }else{

            }
            return super.onFling(downEvent, moveEvent, velocityX, velocityY)
        }
    }

    private fun onSwipeLeft() {
        Toast.makeText(activity?.applicationContext, "LEFT SWIPE", Toast.LENGTH_LONG).show()
    }

    private fun onSwipeRight() {

        Toast.makeText(activity?.applicationContext, "RIGHT SWIPE", Toast.LENGTH_LONG).show()
    }*/
}