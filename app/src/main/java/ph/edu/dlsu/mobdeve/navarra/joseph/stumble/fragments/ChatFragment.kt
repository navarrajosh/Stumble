package ph.edu.dlsu.mobdeve.navarra.joseph.stumble.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.UserAdapter
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.databinding.FragmentChatBinding
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.model.User

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ChatFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ChatFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var chatRecyclerView: RecyclerView
    private lateinit var messageBox: EditText
    private lateinit var sendButton: ImageView
    private lateinit var mDbRef: DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    private lateinit var userList: ArrayList<User>
    private lateinit var adapter: UserAdapter
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        /*
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        userList = ArrayList()
        //adapter = UserAdapter(this,userList)
        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().getReference()
        val chatRecyclerView: RecyclerView = binding.userRecyclerView
        chatRecyclerView.layoutManager = LinearLayoutManager(context)
        chatRecyclerView.adapter = adapter
        /*
        _binding = FragmentChatBinding.inflate(inflater, container, false)

        adapter = UserAdapter(requireActivity().applicationContext, userList)
        _binding!!.userRecyclerView.layoutManager = LinearLayoutManager(requireActivity().applicationContext, LinearLayoutManager.VERTICAL, false)
        _binding!!.userRecyclerView.adapter = adapter
            */

        mDbRef.child("user").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                userList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentUser = postSnapshot.getValue(User::class.java)
                    if (mAuth.currentUser?.uid != currentUser?.uid) {
                        userList.add(currentUser!!)
                    }
                    adapter.notifyDataSetChanged()
                }
            }
            override fun onCancelled(error: DatabaseError) {

            }

        })*/
        return binding.root
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ChatFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ChatFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}