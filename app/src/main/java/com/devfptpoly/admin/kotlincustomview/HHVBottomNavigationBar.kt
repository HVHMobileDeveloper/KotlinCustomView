package com.devfptpoly.admin.kotlincustomview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.hhv_bottom_navigation_bar.view.*

class HHVBottomNavigationBar constructor(
    context: Context?,
    attrs: AttributeSet?
) :
    MotionLayout(context, attrs) {

    lateinit var hivBottomNavigationListened: HHVBottomNavigationListenner

    init {
        View.inflate(context, R.layout.hhv_bottom_navigation_bar, this)
        setClickListener()
    }

    private fun setClickListener() {
        // Apply default transition on app launch
        motion_layout.setTransition(motion_layout.currentState, R.id.home_expand)
        motion_layout.transitionToEnd()

        iv_home.setOnClickListener {
            hivBottomNavigationListened.bottomNavigationListenner(BottomNavItem.First)
            onSetTransition(motion_layout.currentState, R.id.home_expand)
        }

        iv_search.setOnClickListener {
            hivBottomNavigationListened.bottomNavigationListenner(BottomNavItem.Second)
            onSetTransition(motion_layout.currentState, R.id.search_expand)
        }

        iv_like.setOnClickListener {
            hivBottomNavigationListened.bottomNavigationListenner(BottomNavItem.Third)
            onSetTransition(motion_layout.currentState, R.id.like_expand)
        }

        iv_profile.setOnClickListener {
            hivBottomNavigationListened.bottomNavigationListenner(BottomNavItem.Four)
            onSetTransition(motion_layout.currentState, R.id.profile_expand)
        }
    }

    private fun onSetTransition(startState: Int, endState: Int) {
        motion_layout.setTransition(startState, endState)
        motion_layout.setTransitionDuration(200)
        motion_layout.transitionToEnd()
    }

    interface HHVBottomNavigationListenner {
        fun bottomNavigationListenner(item: BottomNavItem)
    }

    enum class BottomNavItem {
        First,
        Second,
        Third,
        Four
    }
}