package com.utils.coders.android_util_examples.lifecycle_example

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks2
import android.content.res.Configuration
import android.os.Bundle

/**
 * Created by alvaro on 4/9/18.
 */

interface LifecycleInterface {
    fun onAppBackgrounded()
    fun onAppForegrounded(activity: Activity?)
}

class LifecycleManager(private val lifeCycleDelegate: LifecycleInterface) : Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {

    private var isInForeground = false

    override fun onTrimMemory(level: Int) {
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            isInForeground = false
            lifeCycleDelegate.onAppBackgrounded()
        }
    }

    override fun onActivityResumed(activity: Activity?) {
        if (!isInForeground) {
            isInForeground = true
            //THINGS
            lifeCycleDelegate.onAppForegrounded(activity)
        }

    }

    override fun onActivityPaused(activity: Activity?) {

    }

    override fun onActivityStarted(activity: Activity?) {

    }

    override fun onActivityDestroyed(activity: Activity?) {

    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

    }

    override fun onActivityStopped(activity: Activity?) {

    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {

    }

    override fun onLowMemory() {

    }

    override fun onConfigurationChanged(newConfig: Configuration?) {

    }
}