package ph.edu.dlsu.mobdeve.navarra.joseph.stumble.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GestureDetectorCompat
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.R

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
    private lateinit var detector: GestureDetectorCompat

    var nameName : TextView? = null
    var courseName : TextView? = null
    var schoolName : TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        //detector = GestureDetectorCompat(activity,GestureListener())

    }
    /*
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

            val view =  inflater.inflate(R.layout.fragment_home, container, false)

            view.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent): Boolean {
                    return if (detector.onTouchEvent(event)) {
                        true
                    }else{
                        super.onTouchEvent(event)
                }
            }})
            return view
        }
    */

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