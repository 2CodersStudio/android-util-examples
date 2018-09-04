# Lifecycle example

Here are the needed files to know when your app has gone to background or has come to foreground and execute the action you want.

### Instructions

1. First of all you have to create an Application class and link it with your AndroidManifest file.

```kotlin
class LifecycleApp : Application() {

}
```

```xml
    <application
        ...
        android:name=".lifecycle_example.LifecycleApp"
        ...
     </application>
```
2. Create an interface to handle callbacks
```kotlin
interface LifecycleInterface {
    fun onAppBackgrounded()
    fun onAppForegrounded(activity: Activity?)
}
```
3. Now you need to create a "Manager" class to control the events.

```kotlin
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
            lifeCycleDelegate.onAppForegrounded(activity)
        }

    }
    ...
```
**NOTE**: you need to implement all the Activity Lifecycle methods but for background/foreground detection only **onTrimMemory** and **onActivityResumed** are needed.

4. Go back to your Application class and add the following code:

```kotlin
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
```

Now whenever your app goes to background/foreground the **onAppBackgrounded** and **onAppForegrounded** will be triggered.