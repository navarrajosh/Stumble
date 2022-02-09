package ph.edu.dlsu.mobdeve.navarra.joseph.stumble

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.fragments.ChatFragment
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.fragments.HomeFragment
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.fragments.ProfileFragment
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.stumbledb

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

        var helper = stumbledb(applicationContext)
        var db = helper.readableDatabase

        var rs = db.rawQuery("SELECT * FROM USERS", null)

        if (rs.moveToLast()) {
            Toast.makeText(applicationContext, "YES", Toast.LENGTH_LONG).show()


        }else  Toast.makeText(applicationContext, "NO",Toast.LENGTH_LONG).show()


        val homeFragment = HomeFragment()
        val chatFragment = ChatFragment()
        val profileFragment = ProfileFragment()
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        makeCurrentFragment(homeFragment)

        bottomNavigationView.setOnItemSelectedListener { it ->
            when(it.itemId){
                R.id. home-> makeCurrentFragment(homeFragment)
                R.id.chat -> makeCurrentFragment(chatFragment)
                R.id.profile -> makeCurrentFragment(profileFragment)
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply{
            replace(R.id.FLStumble, fragment)
            commit()
        }
}