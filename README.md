# SnappingScrollView
A custom scrollview implementation for Android that follows drag path and snaps to the top or bottom.

## Table of Contents
 - [Introduction](#introduction)
 - [Sample](#sample)
 - [Installation](#installation)
 - [Library Details](#library-details)
 - [Usage Examples](#usage-examples)
 - [How to Contribute](#how-to-contribute)
 - [About the Author](#about-the-author)
 - [License](#license)
 
## Introduction
  
  SnappingScrollView serves as an improvement over the default scrollview implementation of android by bringing `Bottom Sheet` like `snapping` behaviour to the scrollview.
 
## Sample
<p align="center">
  <img height="400" src="snappingScroll.gif">
</p>
  
## Installation

[![Download](https://api.bintray.com/packages/abhriyaroy/SnappingScrollView/snappingscrollview/images/download.svg)](https://bintray.com/abhriyaroy/SnappingScrollView/snappingscrollview/_latestVersion)

  Add the dependency in your app module's build.gradle file
          
          dependencies {
		         ...
	          implementation "com.zebrostudio.snappingscrollview:snappingscrollview:{latest_version}"
	        }
   
## Library Details

  - Extends the scrollview class
  - Min sdk 15
  - Written in kotlin
  - Offers disable scroll helper method

## Usage Example

  Add to your layout in your `XML` file -

	<com.zebrostudio.snappingscrollview.SnappingScrollView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/snappingScrollView"
        android:fillViewport="true">

        <LinearLayout
            android:orientation="vertical"
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <View
                android:layout_width="match_parent"
                android:layout_height="600dp"
                android:background="@color/colorAccent"/>

            <View
                android:layout_width="match_parent"
                android:layout_height="600dp"
                android:id="@+id/view2"
                android:background="@color/colorPrimaryDark"/>

            <View
                android:layout_width="match_parent"
                android:layout_height="600dp"
                android:background="@color/colorPrimary"/>
                
             <!-- Other views -->

        </LinearLayout>

    </com.zebrostudio.snappingscrollview.SnappingScrollView>

  And then in your class file add to you `onCreate()` or any other lifecycle method -

  	snappingScrollView.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
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
 
## How to Contribute

  Please feel free to raise an Issue/Pr in-case you come across a bug or even if you have any minor suggestion.

## About the Author

### Abhriya Roy

 Android Developer with 2 years of experience in building apps that look and feel great. 
 Enthusiastic towards writing clean and maintainable code.
 Open source contributor.

 <a href="https://www.linkedin.com/in/abhriya-roy/"><img src="https://i.imgur.com/toWXOAd.png" alt="LinkedIn" width=40 height=40></a>     &nbsp;
 <a href="https://twitter.com/AbhriyaR"><img src="https://i.imgur.com/ymEo5Iy.png" alt="Twitter" width=42 height=40></a> 
 &nbsp;
 <a href="https://stackoverflow.com/users/6197251/abhriya-roy"><img src="https://i.imgur.com/JakJaHP.png" alt="Stack Overflow" width=40  height=40></a> 
 &nbsp;
 <a href="https://angel.co/abhriya-roy?public_profile=1"><img src="https://i.imgur.com/TiwMDMK.png" alt="Angel List" width=40  height=40></a>
 &nbsp;
 <a href="https://play.google.com/store/apps/developer?id=Zebro+Studio"><img src="https://i.imgur.com/Rj1IsYI.png" alt="Angel List" width=40  height=40></a>

 <br>

## License

    Copyright 2020 Abhriya Roy

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
