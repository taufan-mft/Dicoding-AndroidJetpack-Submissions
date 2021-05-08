package com.topanlabs.filmtopan.favorite

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by taufan-mft on 5/8/2021.
 */
class SectionsAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FilmFragment()
            1 -> fragment = TvFragment()
        }
        return fragment as Fragment
    }

}