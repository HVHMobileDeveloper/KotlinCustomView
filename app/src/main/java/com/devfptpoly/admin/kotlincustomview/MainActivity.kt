package com.devfptpoly.admin.kotlincustomview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), HHVBottomNavigationBar.HHVBottomNavigationListenner {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        bottomNavBar.hivBottomNavigationListened = this
    }

    override fun bottomNavigationListenner(item: HHVBottomNavigationBar.BottomNavItem) {
        when (item) {
            HHVBottomNavigationBar.BottomNavItem.First -> {
                val home = MainFragment.newInstance(null, null)
                addFragmentOnTop(home)
            }
            HHVBottomNavigationBar.BottomNavItem.Second -> {
                val details = DetailsFragment.newInstance(null, null)
                addFragmentOnTop(details)
            }
            HHVBottomNavigationBar.BottomNavItem.Third -> {
                toast("Third")
            }
            HHVBottomNavigationBar.BottomNavItem.Four -> {
                toast("Four")
            }
        }
    }

    private fun show(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        fragmentManager.popBackStack("root_fragment", FragmentManager.POP_BACK_STACK_INCLUSIVE);

        fragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .addToBackStack("root_fragment")
            .commit();
    }

    private fun addFragmentOnTop(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
    }

}