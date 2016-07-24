        默认情况下，同一应用程序的所有组件运行在同一进程中。不过，如果你需要控制某个组件属于哪个进程，也可以通过修改
    manifest文件来实现。在manifest文件中，四大组件都支持android:process属性，用来指定在某个进程中运行该组件．
    你可以通过设置这个属性使每个组件运行于不同的进程中、使一些组件共享一个进程或使不同应用的组件们可以运行于
    同一个进程—但是需要这些应用共享同一个用户ID并且有相同的数字证书.
    application元素也支持android:process属性,用于为所有的组件指定一个默认值.
       Android系统可能在某些时刻决定关闭一个进程，比如内存很少了并且另一个进程更迫切的需要启动时．进程被关闭时，
    其中的组件们都被销毁．如果重新需要这些组件工作时，进程又会被创建出来。
       当决定关闭哪些进程时，Android系统会衡量进程们与用户的紧密程度．例如，比起一个具有可见的activity的进程，那些所含
    activity全部不可见的进程更容易被关闭．如何决定一个进程是否被关闭，取决于进程中运行的组件们的状态．决定关闭进程的规则
    将在下面讨论．

    进程的运行状态
       Android系统会尽量维持一个进程的生命，直到最终需要为新的更重要的进程腾出内存空间。为了决定哪个进程该终止，
    系统会跟据运行于进程内的组件和组件的状态把进程置于不同的重要性等级。当需要系统资源时，重要性等级越低的先被淘汰。
    重要性等级被分为５个等级。下面列出了不同重要性等级对应的进程类型(第一个进程类型是最重要的，也是最后才会被终止的）
    １ Foreground Process (前台进程)
    用户当前正在做的事情需要这个进程。如果满足下面的条件，一个进程就被认为是前台进程：
    1)这个进程拥有一个正在与用户交互的Activity(这个Activity的onResume() 方法被调用)。
    2)这个进程正处于与绑定Service交互的状态。
    3)这个进程拥有一个前台运行的Service　—　service调用了方法 startForeground().
    4)这个进程拥有一个正在执行其任何一个生命周期回调方法（onCreate、onBind、onStartCommand、onStart、onUnbind、
      onDestroy）的Service。
    5)这个进程拥有正在执行其onReceive()方法的BroadcastReceiver。
    通常，在任何时间点，只有很少的前台进程存在。它们只有在达到无法调合的矛盾时才会被终止－－如果内存太小而不能继续运行时。
    通常，到了这时，设备就达到了一个内存分页调度状态，所以需要终止一些前台进程来保证用户界面的反应.杀死
    Foreground Process需要用户响应，因为这个安全优先级是最高的。
    ２ Visible Process（可见进程）
    一个进程不拥有运行于前台的组件，但是依然能影响用户所见。满足下列条件时，进程即为可见：
    1)这个进程拥有一个不在前台但仍可见的Activity(它的onPause()方法被调用)。例如当一个前台activity弹出
      一个对话框 (input Method)。
    2)这个进程拥有一个绑定在foreground（或者visible）Activity的Service。一个可见的进程是极其重要的，
      通常不会被终止，除非内存不够，需要释放内存以便前台进程运行。
    ３ Service Process（服务进程）
      一个进程不在上述两种之内，但它运行着一个不在上述两种状态的Service。
      尽管一个服务进程不直接影响用户所见，但是它们通常做一些用户关心的事情（比如播放音乐或下载数据），所以除非系统没有足够
      的空间运行前台进程和可见进程时才会终止一个服务进程。
    4 Background Process（后台进程）
      一个进程拥有一个当前不可见的activity(activity的onStop()方法被调用)。
      这样的进程不会直接影响到用户体验，所以系统可以在任意时刻杀了它们从而为前台、可见、以及服务进程们提供存储空间。
      通常有很多后台进程在运行。它们被保存在一个LRU(最近最少使用)列表中来确保拥有最近刚被看到的activity的进程最后被杀。
      如果一个activity正确的保存了它的当前状态，那么杀死它的进程将不会对用户的可视化体验造成影响。因为当用户返回到
      这个activity时，这个activity会恢复到它原来的状态。
    ５ Empty Process（空进程）
      没有运行任何Components的进程，保留这个进程主要是为了缓存的需要。
      保留这类进程的唯一理由是高速缓存，这样可以提高下一次一个组件要运行它时的启动速度。系统经常为了平衡在进程高速缓存
      和底层的内核高速缓存之间的整体系统资源而终止它们。

           一个进程的排名因为其他进程依赖它而上升。一个进程服务其它进程，它的排名从不会比它服务的进程低。
       例如，进程A中的一个内容提供者服务进程B中的一个客户，或者进程A中的一个服务绑定到进程B中的一个组件，
       进程A总是被认为比进程B重要。

　　       因为一个服务进程排名比后台活动的进程排名高，一个活动启动一个服务来初始化一个长时间运行操作，
       而不是简单地衍生一个线程——特别是如果操作很可能会拖垮活动（例如出现ANR）。这方面的例子是在后台播放
       音乐和上传相机拍摄的图片到一个网站。使用服务保证操作至少有“服务进程”的优先级，无论活动发生什么情况。
       这同样是广播接收者应该使用service而不是简单地使用一个线程的理由。

    
    通过Activity的运行状态决定Process的运行状态的举例
    
    1 使进程进入Visible状态
    a> 启动testprocess应用从而打开FirstActivity，然后按home键返回到homescreen界面,
    再一次启动testprocess应用从而打开FirstActivity，然后按home键返回到homescreen界面。
	01-01 01:55:45.186: I/chenyang(5989): onCreate 被调用
	01-01 01:55:45.232: I/chenyang(5989): process processName com.cytmxk.testprocess
	01-01 01:55:45.232: I/chenyang(5989): process importance = 100
	01-01 01:55:45.232: I/chenyang(5989): process importanceString = Foreground Process
	01-01 01:55:45.232: I/chenyang(5989): ------------------------------------------------------------------------
	01-01 01:55:45.235: I/chenyang(5989): onStart 被调用
	01-01 01:55:45.241: I/chenyang(5989): process processName com.cytmxk.testprocess
	01-01 01:55:45.241: I/chenyang(5989): process importance = 100
	01-01 01:55:45.241: I/chenyang(5989): process importanceString = Foreground Process
	01-01 01:55:45.241: I/chenyang(5989): ------------------------------------------------------------------------
	01-01 01:55:45.244: I/chenyang(5989): onResume 被调用
	01-01 01:55:45.250: I/chenyang(5989): process processName com.cytmxk.testprocess
	01-01 01:55:45.250: I/chenyang(5989): process importance = 100
	01-01 01:55:45.250: I/chenyang(5989): process importanceString = Foreground Process
	01-01 01:55:45.250: I/chenyang(5989): ------------------------------------------------------------------------
	01-01 01:55:47.381: I/chenyang(5989): onPause 被调用
	01-01 01:55:47.402: I/chenyang(5989): process processName com.cytmxk.testprocess
	01-01 01:55:47.402: I/chenyang(5989): process importance = 200
	01-01 01:55:47.402: I/chenyang(5989): process importanceString = Visible Process
	01-01 01:55:47.402: I/chenyang(5989): ------------------------------------------------------------------------
	01-01 01:55:48.277: I/chenyang(5989): onSaveInstanceState 被调用
	01-01 01:55:48.294: I/chenyang(5989): process processName com.cytmxk.testprocess
	01-01 01:55:48.294: I/chenyang(5989): process importance = 130
	01-01 01:55:48.294: I/chenyang(5989): process importanceString = UnKnown Process
	01-01 01:55:48.294: I/chenyang(5989): ------------------------------------------------------------------------
	01-01 01:55:48.298: I/chenyang(5989): onStop 被调用
	01-01 01:55:48.321: I/chenyang(5989): process processName com.cytmxk.testprocess
	01-01 01:55:48.321: I/chenyang(5989): process importance = 130
	01-01 01:55:48.321: I/chenyang(5989): process importanceString = UnKnown Process
	01-01 01:55:48.321: I/chenyang(5989): ------------------------------------------------------------------------
	01-01 01:55:48.634: I/chenyang(5989): onRestart 被调用
	01-01 01:55:48.647: I/chenyang(5989): process processName com.cytmxk.testprocess
	01-01 01:55:48.647: I/chenyang(5989): process importance = 100
	01-01 01:55:48.647: I/chenyang(5989): process importanceString = Foreground Process
	01-01 01:55:48.647: I/chenyang(5989): ------------------------------------------------------------------------
	01-01 01:55:48.650: I/chenyang(5989): onStart 被调用
	01-01 01:55:48.659: I/chenyang(5989): process processName com.cytmxk.testprocess
	01-01 01:55:48.659: I/chenyang(5989): process importance = 100
	01-01 01:55:48.659: I/chenyang(5989): process importanceString = Foreground Process
	01-01 01:55:48.659: I/chenyang(5989): ------------------------------------------------------------------------
	01-01 01:55:48.664: I/chenyang(5989): onResume 被调用
	01-01 01:55:48.669: I/chenyang(5989): process processName com.cytmxk.testprocess
	01-01 01:55:48.669: I/chenyang(5989): process importance = 100
	01-01 01:55:48.669: I/chenyang(5989): process importanceString = Foreground Process
	01-01 01:55:48.669: I/chenyang(5989): ------------------------------------------------------------------------
	01-01 01:55:49.592: I/chenyang(5989): onPause 被调用
	01-01 01:55:49.612: I/chenyang(5989): process processName com.cytmxk.testprocess
	01-01 01:55:49.612: I/chenyang(5989): process importance = 200
	01-01 01:55:49.612: I/chenyang(5989): process importanceString = Visible Process
	01-01 01:55:49.612: I/chenyang(5989): ------------------------------------------------------------------------
	01-01 01:55:50.385: I/chenyang(5989): onSaveInstanceState 被调用
	01-01 01:55:50.404: I/chenyang(5989): process processName com.cytmxk.testprocess
	01-01 01:55:50.404: I/chenyang(5989): process importance = 130
	01-01 01:55:50.404: I/chenyang(5989): process importanceString = UnKnown Process
	01-01 01:55:50.404: I/chenyang(5989): ------------------------------------------------------------------------
	01-01 01:55:50.414: I/chenyang(5989): onStop 被调用
	01-01 01:55:50.429: I/chenyang(5989): process processName com.cytmxk.testprocess
	01-01 01:55:50.429: I/chenyang(5989): process importance = 130
	01-01 01:55:50.429: I/chenyang(5989): process importanceString = UnKnown Process
	01-01 01:55:50.429: I/chenyang(5989): ------------------------------------------------------------------------
    
    b> 启动testprocess应用从而打开FirstActivity，然后点击Show Activity Dialog按钮弹出
    SecondActivity对话框（清单文件中该Activity的样式设置为Theme.Dialog，prcess属性设置为
    android:process=".SecondActivity"），然后点击回退键。
    01-01 02:09:45.753: I/chenyang(6634): onCreate 被调用
	01-01 02:09:45.767: I/chenyang(6634): process processName com.cytmxk.testprocess
	01-01 02:09:45.767: I/chenyang(6634): process importance = 100
	01-01 02:09:45.767: I/chenyang(6634): process importanceString = Foreground Process
	01-01 02:09:45.767: I/chenyang(6634): ------------------------------------------------------------------------
	01-01 02:09:45.770: I/chenyang(6634): onStart 被调用
	01-01 02:09:45.782: I/chenyang(6634): process processName com.cytmxk.testprocess
	01-01 02:09:45.782: I/chenyang(6634): process importance = 100
	01-01 02:09:45.782: I/chenyang(6634): process importanceString = Foreground Process
	01-01 02:09:45.782: I/chenyang(6634): ------------------------------------------------------------------------
	01-01 02:09:45.786: I/chenyang(6634): onResume 被调用
	01-01 02:09:45.796: I/chenyang(6634): process processName com.cytmxk.testprocess
	01-01 02:09:45.796: I/chenyang(6634): process importance = 100
	01-01 02:09:45.796: I/chenyang(6634): process importanceString = Foreground Process
	01-01 02:09:45.796: I/chenyang(6634): ------------------------------------------------------------------------
	01-01 02:09:47.018: I/chenyang(6634): onPause 被调用
	01-01 02:09:47.024: I/chenyang(6634): process processName com.cytmxk.testprocess
	01-01 02:09:47.024: I/chenyang(6634): process importance = 100
	01-01 02:09:47.024: I/chenyang(6634): process importanceString = Foreground Process
	01-01 02:09:47.024: I/chenyang(6634): ------------------------------------------------------------------------
	01-01 02:09:47.097: I/chenyang(6634): onSaveInstanceState 被调用
	01-01 02:09:47.108: I/chenyang(6634): process processName com.cytmxk.testprocess
	01-01 02:09:47.108: I/chenyang(6634): process importance = 200
	01-01 02:09:47.108: I/chenyang(6634): process importanceString = Visible Process
	01-01 02:09:47.108: I/chenyang(6634): ------------------------------------------------------------------------
	01-01 02:09:48.764: I/chenyang(6634): onResume 被调用
	01-01 02:09:48.791: I/chenyang(6634): process processName com.cytmxk.testprocess
	01-01 02:09:48.791: I/chenyang(6634): process importance = 100
	01-01 02:09:48.791: I/chenyang(6634): process importanceString = Foreground Process
	01-01 02:09:48.791: I/chenyang(6634): ------------------------------------------------------------------------
	    
    c> 启动testprocess应用从而打开FirstActivity，然后点击Bind Service按钮从而启动FirstService服务，等待3秒左右，然后点击how Activity Dialog按钮弹出
    SecondActivity对话框。
    
    
    