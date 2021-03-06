package com.topanlabs.filmtopan

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.tabs.TabLayoutMediator
import com.topanlabs.filmtopan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
                R.string.movie_tab,
                R.string.tv_tab
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val viewPager = binding.viewPager
        val sectionsAdapter = SectionsAdapter(this)
        viewPager.adapter = sectionsAdapter
        val tabs = binding.tabLayout
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()


    }
}