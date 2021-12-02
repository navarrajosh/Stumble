package ph.edu.dlsu.mobdeve.navarra.joseph.stumble

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.fragments.ChatFragment
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.fragments.HomeFragment
import ph.edu.dlsu.mobdeve.navarra.joseph.stumble.fragments.ProfileFragment

class Stumble : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stumble)

        val homeFragment = HomeFragment()
        val chatFragment = ChatFragment()
        val profileFragment = ProfileFragment()

        makeCurrentFragment(homeFragment)



        botnav.setOnNavigationItemSelectedListener{
            when (it.itemId){
                R.id.home -> makeCurrentFragment(homeFragment)
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