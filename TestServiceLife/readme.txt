1  前言
对于Service的生命周期的学习，我是通过测试Service生命周期方法的调用顺序来分析的，Service的
所有生命周期方法如下所示：
onCreate
onBind
onStartCommand
onStart//已经被onStartCommand方法替代
onUnbind
onDestroy

分别在每一个方法中打印一句log，通过log的打印顺序，可以推测生命周期方法的执行顺序，
主要通过几组常用操作来分析。

2 点击idle界面上应用的图标，从而打开FirstActivity，然后点击Start Service按钮两次，接着点击
Stop Service按钮两次。
打印的log如下：
01-01 15:14:28.564: I/android.app.Service.TestService(3980): onCreate
01-01 15:14:28.566: I/android.app.Service.TestService(3980): onStartCommand
01-01 15:14:28.566: I/android.app.Service.TestService(3980): onStart
01-01 15:14:31.677: I/android.app.Service.TestService(3980): onStartCommand
01-01 15:14:31.677: I/android.app.Service.TestService(3980): onStart
01-01 15:14:33.743: I/android.app.Service.TestService(3980): onDestroy

3 点击idle界面上应用的图标，从而打开FirstActivity，然后点击Bind Service按钮两次，接着点击
Unbind Service按钮两次。
打印的log如下：
01-01 15:15:54.221: I/android.app.Service.TestService(4008): onCreate
01-01 15:15:54.238: I/android.app.Service.TestService(4008): onBind
01-01 15:16:03.587: I/android.app.Service.TestService(4008): onUnbind
01-01 15:16:03.591: I/android.app.Service.TestService(4008): onDestroy
注意：第二次点击Unbind Service按钮时，会报错退出，因为第一次点击Unbind Service按钮时，系统会摧毁掉
Service，导致第二次点击时由于Service已经不存在，所以报错。
03-15 06:03:00.017: E/AndroidRuntime(19958): FATAL EXCEPTION: main
03-15 06:03:00.017: E/AndroidRuntime(19958): Process: com.cytmxk.testservicelife, PID: 19958
03-15 06:03:00.017: E/AndroidRuntime(19958): java.lang.IllegalArgumentException: Service not registered: com.cytmxk.testservicelife.FirstActivity$1@1395151e
03-15 06:03:00.017: E/AndroidRuntime(19958): 	at android.app.LoadedApk.forgetServiceDispatcher(LoadedApk.java:1068)
03-15 06:03:00.017: E/AndroidRuntime(19958): 	at android.app.ContextImpl.unbindService(ContextImpl.java:1951)
03-15 06:03:00.017: E/AndroidRuntime(19958): 	at android.content.ContextWrapper.unbindService(ContextWrapper.java:551)
03-15 06:03:00.017: E/AndroidRuntime(19958): 	at com.cytmxk.testservicelife.FirstActivity$5.onClick(FirstActivity.java:84)
03-15 06:03:00.017: E/AndroidRuntime(19958): 	at android.view.View.performClick(View.java:4848)
03-15 06:03:00.017: E/AndroidRuntime(19958): 	at android.view.View$PerformClick.run(View.java:20260)
03-15 06:03:00.017: E/AndroidRuntime(19958): 	at android.os.Handler.handleCallback(Handler.java:815)
03-15 06:03:00.017: E/AndroidRuntime(19958): 	at android.os.Handler.dispatchMessage(Handler.java:104)
03-15 06:03:00.017: E/AndroidRuntime(19958): 	at android.os.Looper.loop(Looper.java:194)
03-15 06:03:00.017: E/AndroidRuntime(19958): 	at android.app.ActivityThread.main(ActivityThread.java:5624)
03-15 06:03:00.017: E/AndroidRuntime(19958): 	at java.lang.reflect.Method.invoke(Native Method)
03-15 06:03:00.017: E/AndroidRuntime(19958): 	at java.lang.reflect.Method.invoke(Method.java:372)
03-15 06:03:00.017: E/AndroidRuntime(19958): 	at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:959)
03-15 06:03:00.017: E/AndroidRuntime(19958): 	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:754)


4 点击idle界面上应用的图标，从而打开FirstActivity，然后点击Start Service按钮，接着点击
Bind Service按钮,接着点击Stop Service按钮，接着点击Unbind Service按钮。
打印的log如下：
01-01 15:21:26.523: I/android.app.Service.TestService(4169): onCreate
01-01 15:21:26.527: I/android.app.Service.TestService(4169): onStartCommand
01-01 15:21:26.527: I/android.app.Service.TestService(4169): onStart
01-01 15:21:30.230: I/android.app.Service.TestService(4169): onBind
01-01 15:21:43.014: I/android.app.Service.TestService(4169): onUnbind
01-01 15:21:43.015: I/android.app.Service.TestService(4169): onDestroy
TestService demo 中的readme.txt中5.5可以解释点击Stop Service按钮不会起作用的原因


5 点击idle界面上应用的图标，从而打开FirstActivity，然后点击Bind Service按钮，接着点击
Start Service按钮,接着点击Unbind Service按钮，接着点击Stop Service按钮。
打印的log如下：
01-01 15:24:44.539: I/android.app.Service.TestService(4311): onCreate
01-01 15:24:44.543: I/android.app.Service.TestService(4311): onBind
01-01 15:24:46.611: I/android.app.Service.TestService(4311): onStartCommand
01-01 15:24:46.611: I/android.app.Service.TestService(4311): onStart
01-01 15:24:48.862: I/android.app.Service.TestService(4311): onUnbind
01-01 15:24:50.633: I/android.app.Service.TestService(4311): onDestroy

6 总结 
通过 4和5可以得知，Start Service按钮和Bind Service按钮点击顺序不管怎样，最后都会导致Stop Service按钮不会起作用









