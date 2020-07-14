package com.devfptpoly.admin.kotlincustomview

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.HorizontalScrollView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.core.view.marginBottom


class HHVTabLayout(context: Context, attrs: AttributeSet?) :
    HorizontalScrollView(context, attrs) {

    private var tabsTemps: MutableList<TabView> = ArrayList()

    private var tabWidth: Int = 0
    private var tabHeight: Int = 0

    private var lineTabHeight: Int = 0

    private var bgContainerTabColor: Int = 0

    private var bgTabButtonSelected: Int = 0
    private var bgTabButtonNormal: Int = 0

    private var tvTabNormal: Int = 0
    private var tvTabSelected: Int = 0

    private var bgLineSelected: Int = 0
    private var bgLineNormal: Int = 0


    init {
        initArrtibutes()
        initLayout()
    }


    private fun initArrtibutes() {
        lineTabHeight = resources.getDimensionPixelOffset(R.dimen.lineTabHeight)
        tabWidth = resources.getDimensionPixelOffset(R.dimen.tabWith)
        tabHeight = resources.getDimensionPixelOffset(R.dimen.tabHeight)

        bgContainerTabColor = ContextCompat.getColor(context, R.color.bgContainerTabColor)

        bgTabButtonSelected = ContextCompat.getColor(context, R.color.bgTabButtonSelected)
        bgTabButtonNormal = ContextCompat.getColor(context, R.color.bgTabButtonNormal)

        tvTabNormal = ContextCompat.getColor(context, R.color.tvTabNormal)
        tvTabSelected = ContextCompat.getColor(context, R.color.tvTabSelected)

        bgLineSelected = ContextCompat.getColor(context, R.color.bgLineSelected)
        bgLineNormal = ContextCompat.getColor(context, R.color.bgLineNormal)
    }

    private fun container(): LinearLayout {
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val view = LinearLayout(context)
        view.orientation = LinearLayout.HORIZONTAL
        view.layoutParams = params
        view.setBackgroundColor(bgContainerTabColor)
        return view;
    }

    private fun tabParent(): LinearLayout {
        val params: ViewGroup.LayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        val view = LinearLayout(context)
        view.orientation = LinearLayout.VERTICAL
        view.layoutParams = params
        return view;
    }

    private fun tabButton(index: Int, width: Int, height: Int): AppCompatButton {
        val params: ViewGroup.LayoutParams = ViewGroup.LayoutParams(
            width, height
        )
        val button = AppCompatButton(context)
        button.layoutParams = params
        button.text = "$index"
        button.setOnClickListener { onHandleTabSelected(index) }
        return button
    }

    private fun lineTab(index: Int, width: Int, height: Int): View {
        val params: ViewGroup.LayoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, height
        )
        var view = View(context)
        view.layoutParams = params
        return view
    }

    private fun initLayout() {

        val containerView = container()
        //2
        for (index in 0..10) {
            val tabParent = tabParent()
            val button = tabButton(index, tabWidth, tabHeight)
            val lineTab = lineTab(index, tabWidth, lineTabHeight)
            val tabView = TabView(tabParent, button, lineTab)

            tabParent.addView(button)
            tabParent.addView(lineTab)
            containerView.addView(tabParent)
            tabsTemps.add(tabView)
        }

        this.addView(containerView)
        this.isHorizontalScrollBarEnabled = false

        onHandleTabSelected(0)
    }

    private fun onHandleTabSelected(indexSelected: Int) {
        tabsTemps.forEachIndexed { index, tab ->
            val parentView = tab.parentView
            val tabBtn = tab.tabButton
            val lineView = tab.lineView

            val isSelected: Boolean = index == indexSelected
            val bgTabBtn = if (isSelected) bgTabButtonSelected else bgTabButtonNormal
            val bgTvTabBtn = if (isSelected) tvTabSelected else tvTabNormal
            val bgLineTabView = if (isSelected) bgLineSelected else bgLineNormal

            tabBtn.setBackgroundColor(bgContainerTabColor)
            parentView.setBackgroundColor(bgTabBtn)
            tabBtn.setTextColor(bgTvTabBtn)
            lineView.setBackgroundColor(bgLineTabView)

            if (indexSelected == index) {
                val scrollX: Int =
                    (parentView.left - (this.width / 2)) + (parentView.width / 2)
                this.smoothScrollTo(scrollX, 0);
            }
        }
    }

    data class TabView(
        val parentView: LinearLayout,
        val tabButton: AppCompatButton,
        val lineView: View
    )
}
