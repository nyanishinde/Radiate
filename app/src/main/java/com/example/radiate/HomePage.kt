package com.example.radiate

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val bottomNavBar = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navigationFrame = findViewById<FrameLayout>(R.id.frameLayoutContainer)

        loadFragment(HomeFragment())

        bottomNavBar.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.menuReminder -> {
                    item.setIcon(R.drawable.icon_reminder_active)
                    loadFragment(ReminderFragment())
                }
                R.id.menuJournal -> {
                    item.setIcon(R.drawable.icon_journal_active)
                    loadFragment(JournalFragment())
                }
                R.id.menuHome -> {
                    item.setIcon(R.drawable.icon_home_active)
                    loadFragment(HomeFragment())
                }
                R.id.menuBlogs -> {
                    item.setIcon(R.drawable.icon_blogs_active)
                    loadFragment(BlogsFragment())
                }
                R.id.menuProfile -> {
                    item.setIcon(R.drawable.icon_profile_active)
                    loadFragment(ProfileFragment())
                }
            }
            true
        }
    }

    private fun loadFragment(fragment:Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayoutContainer,fragment)
            .commit()
    }
}