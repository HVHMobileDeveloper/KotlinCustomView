package com.devfptpoly.admin.kotlincustomview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.hhv_bottom_navigation_bar.view.*

class HHVBottomNavigationBar(context: Context?, attrs: AttributeSet?) :
    MotionLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.hhv_bottom_navigation_bar, this)
        setClickListener()
    }

    private fun setClickListener() {
        // Apply default transition on app launch
        motion_layout.setTransition(motion_layout.currentState, R.id.home_expand)
        motion_layout.transitionToEnd()

        iv_home.setOnClickListener {
            onSetTransition(motion_layout.currentState, R.id.home_expand)
        }

        iv_search.setOnClickListener {
            onSetTransition(motion_layout.currentState, R.id.search_expand)
        }

        iv_like.setOnClickListener {
            onSetTransition(motion_layout.currentState, R.id.like_expand)
        }

        iv_profile.setOnClickListener {
            onSetTransition(motion_layout.currentState, R.id.profile_expand)
        }
    }

     private fun onSetTransition(startState: Int, endState: Int) {
        motion_layout.setTransition(startState, endState)
        motion_layout.setTransitionDuration(200)
        motion_layout.transitionToEnd()
    }
}