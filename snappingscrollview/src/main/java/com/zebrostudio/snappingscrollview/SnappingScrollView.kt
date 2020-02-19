package com.zebrostudio.snappingscrollview

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.widget.NestedScrollView

private const val DELAY_MILLIS = 40L

class SnappingScrollView : NestedScrollView {

    private var mOnScrollChangedListener: OnScrollChangedListener? = null
    private var snappingView: View? = null
    private var offset = 0f
    private var wasActionMoveStarted = false
    private var startY = 0
    private var endY = 0
    private var flingListener: OnFlingListener? = null
    private var scrollChecker: Runnable? = null
    private var mPreviousPosition: Int = 0
    private var flingStartingPosition = 0
    private var isScrollable: Boolean = true

    interface OnScrollChangedListener {
        fun onScrollChanged(isExpanded: Boolean, newY: Int)
    }

    interface OnFlingListener {
        fun onFlingStarted()
        fun onFlingStopped()
    }

    constructor(context: Context) : super(context) {
        initScrollChecker()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initScrollChecker()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        initScrollChecker()
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
        if (scrollY in offset..0f && (oldt - t) > 0) {
            smoothScrollTo(0, 0)
        }
        if (mOnScrollChangedListener != null) {
            mOnScrollChangedListener!!.onScrollChanged(scrollY >= offset, scrollY)
        }
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return when (ev.action) {
            MotionEvent.ACTION_MOVE -> {
                if (!wasActionMoveStarted) {
                    wasActionMoveStarted = true
                    startY = scrollY
                }
                isScrollable && super.onTouchEvent(ev)
            }
            MotionEvent.ACTION_UP -> {
                endY = scrollY
                wasActionMoveStarted = false
                if ((endY - startY >= 50) && scrollY < offset) {
                    println("slow smooth scroll")
                    slowSmoothScrollTo(offset.toInt())
                    false
                } else if ((endY - startY < 50) && scrollY < offset) {
                    println("slow scroll")
                    smoothScrollTo(0, 0)
                    false
                } else {
                    isScrollable && super.onTouchEvent(ev)
                }
            }
            else -> isScrollable && super.onTouchEvent(ev)
        }
    }

    override fun fling(velocityY: Int) {
        super.fling(velocityY)
        if (flingListener != null) {
            flingListener?.onFlingStarted()
            handler.post(scrollChecker)
        }
    }

    fun setOnScrollChangedListener(listener: OnScrollChangedListener) {
        mOnScrollChangedListener = listener
    }

    fun setSnappingView(view: View) {
        snappingView = view
        offset = view.y
        flingListener = object : OnFlingListener {
            override fun onFlingStarted() {
                flingStartingPosition = scrollY
            }

            override fun onFlingStopped() {
                if (scrollY < offset.toInt() && flingStartingPosition - scrollY > 0) {
                    smoothScrollTo(0, 0)
                }
            }
        }
    }

    fun setScrollingEnabled(enabled: Boolean) {
        isScrollable = enabled
    }

    private fun initScrollChecker() {
        scrollChecker = Runnable {
            val position = scrollY
            if (mPreviousPosition - position == 0) {
                flingListener?.onFlingStopped()
                removeCallbacks(scrollChecker)
            } else {
                mPreviousPosition = scrollY
                postDelayed(scrollChecker, DELAY_MILLIS)
            }
        }
    }

    private fun slowSmoothScrollTo(scrollTo: Int) {
        println("smooth scroll to")
        ObjectAnimator.ofInt(this, "scrollY", scrollY, scrollTo).apply {
            duration = 150
            interpolator = DecelerateInterpolator()
        }.start()
    }
}
