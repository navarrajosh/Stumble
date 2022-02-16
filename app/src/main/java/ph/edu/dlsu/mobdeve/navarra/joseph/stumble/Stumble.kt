package ph.edu.dlsu.mobdeve.navarra.joseph.stumble

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class Stumble : AppCompatActivity() {

    var nameName: TextView? = null
    var courseName: TextView? = null
    var schoolName: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stumble)

        nameName = findViewById(R.id.nameName)
        courseName = findViewById(R.id.courseName)
        schoolName = findViewById(R.id.schoolName)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

    }
}