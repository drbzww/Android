1  前言
对于Activity的生命周期的学习，我是通过测试Activity生命周期方法的调用顺序来分析的，Activity的
所有生命周期方法如下所示：
onCreate
onStart
onResume
onPause
onStop
onRestart
onDestroy
除了测试以上的生命周期方法外，我还测试了onSaveInstanceState和onRestoreInstanceState方法。

分别在每一个方法中打印一句log，通过log的打印顺序，可以推测生命周期方法和onSaveInstanceState的执行顺序，
主要通过几组常用操作来分析。

2 点击idle界面上应用的图标，从而打开FirstActivity，然后点击回退键回到idle界面，接着点击idle界面
上应用的图标，再一次打开FirstActivity，点击回退键回到idle界面
打印的log如下：
09-04 23:00:33.845: I/FirstActivity(19042): onCreate()方法被调用
09-04 23:00:33.849: I/FirstActivity(19042): onStart()方法被调用
09-04 23:00:33.851: I/FirstActivity(19042): onResume()方法被调用
09-04 23:00:35.684: I/FirstActivity(19042): onPause()方法被调用
09-04 23:00:36.263: I/FirstActivity(19042): onStop()方法被调用
09-04 23:00:36.265: I/FirstActivity(19042): onDestroy()方法被调用
09-04 23:00:49.010: I/FirstActivity(19042): onCreate()方法被调用
09-04 23:00:49.014: I/FirstActivity(19042): onStart()方法被调用
09-04 23:00:49.015: I/FirstActivity(19042): onResume()方法被调用
09-04 23:00:50.946: I/FirstActivity(19042): onPause()方法被调用
09-04 23:00:51.525: I/FirstActivity(19042): onStop()方法被调用
09-04 23:00:51.526: I/FirstActivity(19042): onDestroy()方法被调用


3 点击idle界面上应用的图标，从而打开FirstActivity，然后点击home键回到idle界面，接着点击idle界面
上应用的图标，再一次打开FirstActivity，点击home键回到idle界面。
打印的log如下：
09-04 23:02:36.746: I/FirstActivity(19330): onCreate()方法被调用
09-04 23:02:36.750: I/FirstActivity(19330): onStart()方法被调用
09-04 23:02:36.752: I/FirstActivity(19330): onResume()方法被调用
09-04 23:02:38.752: I/FirstActivity(19330): onPause()方法被调用
09-04 23:02:39.353: I/FirstActivity(19330): onSaveInstanceState()方法被调用
09-04 23:02:39.355: I/FirstActivity(19330): onStop()方法被调用
09-04 23:02:53.951: I/FirstActivity(19330): onRestart()方法被调用
09-04 23:02:53.971: I/FirstActivity(19330): onStart()方法被调用
09-04 23:02:53.972: I/FirstActivity(19330): onResume()方法被调用
09-04 23:02:57.302: I/FirstActivity(19330): onPause()方法被调用
09-04 23:02:57.912: I/FirstActivity(19330): onSaveInstanceState()方法被调用
09-04 23:02:57.913: I/FirstActivity(19330): onStop()方法被调用


4 点击idle界面上应用的图标，从而打开FirstActivity，然后长按home键进入到最近运行的应用程序界面，接着点击
该应用的界面，再一次打开FirstActivity，然后长按home键进入到最近运行的应用程序界面，然后将该应用kill。
打印的log如下：
09-04 23:05:03.202: I/FirstActivity(19566): onCreate()方法被调用
09-04 23:05:03.205: I/FirstActivity(19566): onStart()方法被调用
09-04 23:05:03.206: I/FirstActivity(19566): onResume()方法被调用
09-04 23:05:05.658: I/FirstActivity(19566): onPause()方法被调用
09-04 23:05:05.739: I/FirstActivity(19566): onSaveInstanceState()方法被调用
09-04 23:05:05.741: I/FirstActivity(19566): onStop()方法被调用
09-04 23:05:25.384: I/FirstActivity(19566): onRestart()方法被调用
09-04 23:05:25.388: I/FirstActivity(19566): onStart()方法被调用
09-04 23:05:25.388: I/FirstActivity(19566): onResume()方法被调用
09-04 23:05:31.178: I/FirstActivity(19566): onPause()方法被调用
09-04 23:05:31.229: I/FirstActivity(19566): onSaveInstanceState()方法被调用
09-04 23:05:31.229: I/FirstActivity(19566): onStop()方法被调用
09-04 23:05:34.743: I/FirstActivity(19566): onDestroy()方法被调用


5 点击idle界面上应用的图标，从而打开FirstActivity，然后点击Show Dialog按钮(AlertDialog)，然后点击确定
按钮。
打印的log如下：
09-04 23:06:27.910: I/FirstActivity(19745): onCreate()方法被调用
09-04 23:06:27.913: I/FirstActivity(19745): onStart()方法被调用
09-04 23:06:27.915: I/FirstActivity(19745): onResume()方法被调用


6 点击idle界面上应用的图标，从而打开FirstActivity，然后点击Show Activity Dialog按钮(Activity)，然后
点击回退键。
打印的log如下：
09-04 23:07:03.498: I/FirstActivity(19845): onCreate()方法被调用
09-04 23:07:03.501: I/FirstActivity(19845): onStart()方法被调用
09-04 23:07:03.503: I/FirstActivity(19845): onResume()方法被调用
09-04 23:07:06.535: I/FirstActivity(19845): onPause()方法被调用
09-04 23:07:07.062: I/FirstActivity(19845): onSaveInstanceState()方法被调用
09-04 23:07:11.547: I/FirstActivity(19845): onResume()方法被调用


7 点击idle界面上应用的图标，从而打开FirstActivity，然后按poweroff锁屏，然后按poweroff亮屏。
打印的log如下：
09-04 23:08:41.071: I/FirstActivity(20111): onCreate()方法被调用
09-04 23:08:41.074: I/FirstActivity(20111): onStart()方法被调用
09-04 23:08:41.076: I/FirstActivity(20111): onResume()方法被调用
09-04 23:08:44.738: I/FirstActivity(20111): onPause()方法被调用
09-04 23:08:44.799: I/FirstActivity(20111): onSaveInstanceState()方法被调用
09-04 23:08:44.801: I/FirstActivity(20111): onStop()方法被调用
09-04 23:09:03.580: I/FirstActivity(20111): onRestart()方法被调用
09-04 23:09:03.611: I/FirstActivity(20111): onStart()方法被调用
09-04 23:09:03.616: I/FirstActivity(20111): onResume()方法被调用


8 当清单文件中FirstActivity的configChanges属性没有被设置为orientation|screensize时，
点击idle界面上应用的图标，从而打开FirstActivity（此时竖屏），然后横屏，然后竖屏。
打印的log如下：
09-04 23:16:37.958: I/FirstActivity(20739): onCreate()方法被调用
09-04 23:16:37.962: I/FirstActivity(20739): onStart()方法被调用
09-04 23:16:37.963: I/FirstActivity(20739): onResume()方法被调用
09-04 23:16:40.629: I/FirstActivity(20739): onPause()方法被调用
09-04 23:16:40.648: I/FirstActivity(20739): onSaveInstanceState()方法被调用
09-04 23:16:40.649: I/FirstActivity(20739): onStop()方法被调用
09-04 23:16:40.655: I/FirstActivity(20739): onDestroy()方法被调用
09-04 23:16:40.861: I/FirstActivity(20739): onCreate()方法被调用
09-04 23:16:40.864: I/FirstActivity(20739): onStart()方法被调用
09-04 23:16:40.873: I/FirstActivity(20739): onRestoreInstanceState()方法被调用
09-04 23:16:40.875: I/FirstActivity(20739): onResume()方法被调用
09-04 23:17:01.746: I/FirstActivity(20739): onPause()方法被调用
09-04 23:17:01.747: I/FirstActivity(20739): onSaveInstanceState()方法被调用
09-04 23:17:01.750: I/FirstActivity(20739): onStop()方法被调用
09-04 23:17:01.751: I/FirstActivity(20739): onDestroy()方法被调用
09-04 23:17:01.925: I/FirstActivity(20739): onCreate()方法被调用
09-04 23:17:01.928: I/FirstActivity(20739): onStart()方法被调用
09-04 23:17:01.937: I/FirstActivity(20739): onRestoreInstanceState()方法被调用
09-04 23:17:01.938: I/FirstActivity(20739): onResume()方法被调用


9 当清单文件中FirstActivity的configChanges属性被设置为orientation|screensize时，
点击idle界面上应用的图标，从而打开FirstActivity（此时竖屏），然后横屏，然后竖屏。
打印的log如下：
09-04 23:21:34.121: I/FirstActivity(21248): onCreate()方法被调用
09-04 23:21:34.129: I/FirstActivity(21248): onStart()方法被调用
09-04 23:21:34.132: I/FirstActivity(21248): onResume()方法被调用
09-04 23:21:44.752: I/FirstActivity(21248): onConfigurationChanged()方法被调用 newConfig.orientation = 2
09-04 23:21:49.774: I/FirstActivity(21248): onConfigurationChanged()方法被调用 newConfig.orientation = 1


10 点击idle界面上应用的图标，从而打开FirstActivity，然后点击Start ThreeActivity按钮，从而打开ThreeActivity,接着
点击Home键，从而回到FirstActivity界面。
打印的log如下：
09-05 18:50:49.002: I/FirstActivity(5898): onCreate()方法被调用
09-05 18:50:49.004: I/FirstActivity(5898): onStart()方法被调用
09-05 18:50:49.006: I/FirstActivity(5898): onResume()方法被调用
09-05 18:51:01.101: I/FirstActivity(5898): onPause()方法被调用
09-05 18:51:01.231: I/ThreeActivity(5898): onCreate()方法被调用
09-05 18:51:01.233: I/ThreeActivity(5898): onStart()方法被调用
09-05 18:51:01.235: I/ThreeActivity(5898): onResume()方法被调用
09-05 18:51:01.890: I/FirstActivity(5898): onSaveInstanceState()方法被调用
09-05 18:51:01.891: I/FirstActivity(5898): onStop()方法被调用
09-05 18:51:10.547: I/ThreeActivity(5898): onPause()方法被调用
09-05 18:51:10.581: I/FirstActivity(5898): onRestart()方法被调用
09-05 18:51:10.584: I/FirstActivity(5898): onStart()方法被调用
09-05 18:51:10.585: I/FirstActivity(5898): onResume()方法被调用
09-05 18:51:10.973: I/ThreeActivity(5898): onStop()方法被调用
09-05 18:51:10.973: I/ThreeActivity(5898): onDestroy()方法被调用




11 点击idle界面上应用的图标，从而打开FirstActivity，然后点击Start ThreeActivity按钮，从而打开ThreeActivity,接着在
ThreeActivity界面上点击Start FirstActivity按钮，从而再一次打开FirstActivity界面。
打印的log如下：
09-05 18:31:41.010: I/FirstActivity(4447): onCreate()方法被调用
09-05 18:31:41.014: I/FirstActivity(4447): onStart()方法被调用
09-05 18:31:41.016: I/FirstActivity(4447): onResume()方法被调用
09-05 18:31:59.234: I/FirstActivity(4447): onPause()方法被调用
09-05 18:31:59.347: I/ThreeActivity(4447): onCreate()方法被调用
09-05 18:31:59.350: I/ThreeActivity(4447): onStart()方法被调用
09-05 18:31:59.351: I/ThreeActivity(4447): onResume()方法被调用
09-05 18:31:59.988: I/FirstActivity(4447): onSaveInstanceState()方法被调用
09-05 18:31:59.988: I/FirstActivity(4447): onStop()方法被调用
09-05 18:32:24.539: I/ThreeActivity(4447): onPause()方法被调用
09-05 18:32:24.656: I/FirstActivity(4447): onCreate()方法被调用
09-05 18:32:24.659: I/FirstActivity(4447): onStart()方法被调用
09-05 18:32:24.660: I/FirstActivity(4447): onResume()方法被调用
09-05 18:32:25.300: I/ThreeActivity(4447): onSaveInstanceState()方法被调用
09-05 18:32:25.300: I/ThreeActivity(4447): onStop()方法被调用


12 以下情况下 只会触发onPause而不会触发onStop
 一个透明的包含Dialog的Activity 出现

显示一个非activity的Dialog,是不会调用onPause和onStop的，因为此Dialog属于activity

13 
注意1
系统只在Activity异常终止的时候才会调用onSaveInstanceState和onRestoreInstanceState来存储和恢复数据，其他情况不会
触发这个过程。但是按Home键或者启动新Activity仍然会单独触发onSaveInstanceState的调用。

注意2
由10中的log可以看出，当从一个Activity界面启动另一个Activity时，旧的Activity的onpause方法会先执行，然后才会创建新的
Activity。Android官方文档对onpause的解释有这么一句：不能在onpause方法中做重量级的操作，因为必须在旧的Activity的
onpause方法执行完成以后新的Activity才能被创建。
When activity B is launched in front of activity A, this callback will be invoked on A. B will not be 
created until A's onPause() returns, so be sure to not do anything lengthy here. 








