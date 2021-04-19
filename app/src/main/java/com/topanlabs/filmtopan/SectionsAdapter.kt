package com.topanlabs.filmtopan

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.topanlabs.filmtopan.list.film.filmFragment
import com.topanlabs.filmtopan.list.tv.tvFragment

/**
 * Created by taufan-mft on 4/19/2021.
 */
class SectionsAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = filmFragment()
            1 -> fragment = tvFragment.newInstance("ya", "ya")
        }
        return fragment as Fragment
    }

}