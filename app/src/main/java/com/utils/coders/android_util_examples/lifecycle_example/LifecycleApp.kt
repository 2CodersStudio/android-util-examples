package com.utils.coders.android_util_examples.lifecycle_example

import android.app.Activity
import android.app.Application

class LifecycleApp : Application(), LifecycleInterface {

    override fun onCreate() {
        super.onCreate()
        val lifeCycleHandler = LifecycleManager(this)
        registerLifecycleHandler(lifeCycleHandler)
    }

    override fun onAppBackgrounded() {
        //App has entered in background
    }

    override fun onAppForegrounded(activity: Activity?) {
        //App has come to foreground
    }

    private fun registerLifecycleHandler(lifeCycleHandler: LifecycleManager) {
        registerActivityLifecycleCallbacks(lifeCycleHandler)
        registerComponentCallbacks(lifeCycleHandler)
    }
}