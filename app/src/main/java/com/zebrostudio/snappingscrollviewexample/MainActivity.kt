package com.zebrostudio.snappingscrollviewexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewTreObserver = snappingScrollView.viewTreeObserver
        viewTreObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                snappingScrollView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                snappingScrollView.setSnappingView(view2)
            }
        })
    }
}
