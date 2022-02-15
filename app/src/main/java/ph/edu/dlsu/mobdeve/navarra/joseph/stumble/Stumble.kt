package ph.edu.dlsu.mobdeve.navarra.joseph.stumble

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.fragments.ChatFragment
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.fragments.HomeFragment
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.fragments.ProfileFragment

class Stumble : AppCompatActivity() {

    var nameName : TextView? = null
    var courseName : TextView? = null
    var schoolName : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stumble)

        nameName = findViewById(R.id.nameName)
        courseName = findViewById(R.id.courseName)
        schoolName = findViewById(R.id.schoolName)



        val homeFragment = HomeFragment()
        val chatFragment = ChatFragment()
        val profileFragment = ProfileFragment()
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        makeCurrentFragment(homeFragment)

        bottomNavigationView.setOnItemSelectedListener { it ->
            when(it.itemId){
                R.id. home-> {
                    startActivity(Intent(this, Home::class.java))
                    //extend(homeFragment)
                }
                R.id.chat -> startActivity(Intent(this, Chat::class.java))
                R.id.profile -> makeCurrentFragment(profileFragment)
            }
            true
        }

    }

    /*
    private fun extend(fragment: Fragment) =
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<Fragment>(R.id.Hfragment)
        }*/


    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.FLStumble, fragment)
            commit()
        }
}