package com.zebrostudio.snappingscrollviewexample

import android.os.Build
import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        snappingScrollView.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                // Remove the layout listener
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    snappingScrollView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                } else {
                    snappingScrollView.viewTreeObserver.removeGlobalOnLayoutListener(this)
                }
                // Set the view to be snapped on scrolling
                snappingScrollView.setSnappingView(view2)
            }
        })
    }
}
