package ph.edu.dlsu.mobdeve.navarra.joseph.stumble

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter

class stumbleadapter (private val context: Context, private val myModelArrayList: ArrayList<stumblemodel>) : PagerAdapter() {



    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return myModelArrayList.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate(R.layout.card_item, container, false)

        val model = myModelArrayList[position]


        return super.instantiateItem(container, position)
    }

}