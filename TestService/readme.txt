1 在 AndroidManifest.xml 里 Service 元素的常见选项

android:name　　-------------　　服务类名

android:label　　--------------　　服务的名字，如果此项不设置，那么默认显示的服务名则为类名

android:icon　　--------------　　服务的图标

android:permission　　-------　　申明此服务的权限，这意味着只有提供了该权限的应用才能控制或连接此服务

android:process　　----------　　表示该服务是否运行在另外一个进程，如果设置了此项，那么将会在包名后面加上这段字符串表示另一进程的名字

android:enabled　　----------　　如果此项设置为 true，那么 Service 将会默认被系统启动，不设置默认此项为 false


2 基础知识

2.1 Service分为本地服务（LocalService）和远程服务（RemoteService）：

1、本地服务依附在主进程上而不是独立的进程，这样在一定程度上节约了资源，另外Local服务因为是在同一进程因此
不需要IPC，也不需要AIDL。相应bindService会方便很多。主进程被Kill后，服务便会终止。

2、远程服务为独立的进程，对应进程名格式为所在包名加上你指定的android:process字符串。由于是独立的进程，
因此在Activity所在进程被Kill的时候，该服务依然在运行，不受其他进程影响，有利于为多个进程提供服务具有较
高的灵活性。该服务是独立的进程，会占用一定资源，并且使用AIDL进行IPC稍微麻烦一点。

2.2 启动方式可以分为以下三种：

1> startService 启动的服务：主要用于启动一个服务执行后台任务，不进行通信。停止服务使用stopService；
2> bindService 启动的服务：该方法启动的服务可以进行通信。停止服务使用unbindService；
3> startService 和 bindService被依次调用：停止服务只需使用unbindService

3 对于Service的举例和结论

3.1 通过点击FirstActivity界面上的StartService按钮（通过StartService方法启动），从而启动Server
服务并且服务中通过发送消息将服务所在的进程ID和线程ID发给FirstActivity，接着就在FirstActivity界面
显示FirstActivity所在进程ID、FirstActivity所在线程ID、Server所在的进程ID和Server所在的线程ID。
在清单文件中没有为Server配置process属性。
运行结果：
显示的FirstActivity所在进程ID和Server所在的进程ID是相同的，显示的FirstActivity所在线程ID和Server
所在的线程ID都是1。
结论：
当通过StartService的方式启动服务并且服务的process属性没有设置时，服务与启动服务的组件在同一个进程中
并且运行在同一个主线程中。

3.2 通过点击SecondActivity界面上的StartService按钮（通过StartService方法启动），从而启动
SecondServer服务并且服务中通过发送广播将服务所在的进程ID和线程ID发给SecondActivity，接着就在
SecondActivity界面显示SecondActivity所在进程ID、SecondActivity所在线程ID、SecondServer所在的进程ID
和SecondServer所在的线程ID。在清单文件中为SecondServer配置process属性。
运行结果：
显示的SecondActivity所在进程ID和SecondServer所在的进程ID是不同的，显示的SecondActivity所在线程ID和
SecondServer所在的线程ID都是1。
结论：
当通过StartService的方式启动服务并且服务的process属性被设置时，服务与启动服务的组件在不同的进程中。
因为运行在不同的进程中，所以通过广播来实现进程间通信。

3.3 通过点击ThreeActivity界面上的StartService按钮（通过bindService方法启动），从而启动
ThreeServer服务并且服务中通过onBind方法将服务所在的进程ID和线程ID发给ThreeActivity，接着就在
ThreeActivity界面显示ThreeActivity所在进程ID、ThreeActivity所在线程ID、ThreeServer所在的进程ID
和ThreeServer所在的线程ID。在清单文件中没有为ThreeServer配置process属性。
运行结果：
显示的ThreeActivity所在进程ID和ThreeServer所在的进程ID是相同的，显示的ThreeActivity所在线程ID和
ThreeServer所在的线程ID都是1。
结论：
当通过bindService的方式启动服务并且服务的process属性没有被设置时，服务与启动服务的组件在相同的进程中
并且运行在同一个主线程中。

3.4 通过点击FourActivity界面上的StartService按钮（通过bindService方法启动），从而启动
FourServer服务并且服务中通过发送广播将服务所在的进程ID和线程ID发给FourActivity，接着就在
FourActivity界面显示FourActivity所在进程ID、FourActivity所在线程ID、FourServer所在的进程ID
和FourServer所在的线程ID。在清单文件中为FourServer配置process属性。
运行结果：
显示的FourActivity所在进程ID和FourServer所在的进程ID是不同的，显示的FourActivity所在线程ID和
FourServer所在的线程ID都是1。
结论：
当通过bindService的方式启动服务并且服务的process属性被设置时，服务与启动服务的组件在不同的进程中。
因为运行在不同的进程中，所以通过广播来实现进程间通信。

3.5 通过点击testservice应用的FiveActivity界面上的StartService按钮（通过startService方法启动），
从而启动testotherservice应用的FirstServer服务并且服务中通过发送广播将服务所在的进程ID和线程ID发给
FiveActivity，接着就在FiveActivity界面显示FiveActivity所在进程ID、FiveActivity所在线程ID、
FirstServer所在的进程ID和FirstServer所在的线程ID。在清单文件中没有为FirstServer配置process属性。
运行结果：
显示的FiveActivity所在进程ID和FirstServer所在的进程ID是不同的，显示的FiveActivity所在线程ID和
FirstServer所在的线程ID都是1。
结论：
当通过startService的方式启动其他应用中的服务并且服务的process属性没有被设置时，服务与启动服务的组件
在不同的进程中。因为运行在不同的进程中，所以通过广播来实现进程间通信。

3.6 通过点击testservice应用的SixActivity界面上的StartService按钮（通过bindService方法启动），
从而启动testotherservice应用的SecondServer服务并且服务中通过发送广播将服务所在的进程ID和线程ID发给
SixActivity，接着就在SixActivity界面显示SixActivity所在进程ID、SixActivity所在线程ID、
SecondServer所在的进程ID和SecondServer所在的线程ID。在清单文件中没有为SecondServer配置process属性。
运行结果：
显示的SixActivity所在进程ID和SecondServer所在的进程ID是不同的，显示的SixActivity所在线程ID和
SecondServer所在的线程ID都是1。
结论：
当通过bindService的方式启动其他应用中的服务并且服务的process属性没有被设置时，服务与启动服务的组件
在不同的进程中。因为运行在不同的进程中，所以通过广播来实现进程间通信。


3.7 通过点击testservice应用的FirstAIDLActivity界面上的StartService按钮（通过bindService方法启动），
从而启动testotherservice应用的FirstAIDLServer服务并且服务中通过onBind方法将服务所在的进程ID和线程ID
发给FirstAIDLActivity，接着就在FirstAIDLActivity界面显示FirstAIDLActivity所在进程ID、
FirstAIDLActivity所在线程ID、FirstAIDLServer所在的进程ID和FirstAIDLServer所在的线程ID。
在清单文件中没有为FirstAIDLServer配置process属性。
运行结果：
显示的SixActivity所在进程ID和SecondServer所在的进程ID是不同的，显示的SixActivity所在线程ID和
SecondServer所在的线程ID不相同。
结论：
当通过bindService的方式启动其他应用中的服务并且服务的process属性没有被设置时，服务与启动服务的组件
在不同的进程中。因为运行在不同的进程中，所以通过 AIDL来实现进程间通信。



4 添加关于startForeground的测试 -- 代码位置com.cytmxk.testservice.test.foreground


5 注意
5.1 你可以声明Service为私有的，从而可以防止其他应用的访问。
 you can ensure that your service is available to only your app by including the android:exported
  attribute and setting it to "false". This effectively stops other apps from starting your service,
   even when using an explicit intent.
   
5.2 在onStartCommand方法中，onStartCommand有4种返回值： 

START_STICKY：如果service进程被kill掉，保留service的状态为开始状态，但不保留递送的intent对象。随后系统会尝试
重新创建service，由于服务状态为开始状态，所以创建服务后一定会调用onStartCommand(Intent,int,int)方法。如果在
此期间没有任何启动命令被传递到service，那么参数Intent将为null。

START_NOT_STICKY：“非粘性的”。使用这个返回值时，如果在执行完onStartCommand后，服务被异常kill掉，系统不会自动
重启该服务。

START_REDELIVER_INTENT：重传Intent。使用这个返回值时，如果在执行完onStartCommand后，服务被异常kill掉，系统
会自动重启该服务，并将Intent的值传入。

START_STICKY_COMPATIBILITY：START_STICKY的兼容版本，但不保证服务被kill后一定能重启。

google文档中的解释：
START_NOT_STICKY
    If the system kills the service after onStartCommand() returns, do not recreate the service, 
    unless there are pending intents to deliver. This is the safest option to avoid running your 
    service when not necessary and when your application can simply restart any unfinished jobs.
START_STICKY
    If the system kills the service after onStartCommand() returns, recreate the service and call 
    onStartCommand(), but do not redeliver the last intent. Instead, the system calls 
    onStartCommand() with a null intent, unless there were pending intents to start the service, 
    in which case, those intents are delivered. This is suitable for media players
     (or similar services) that are not executing commands, but running indefinitely and waiting 
     for a job.
START_REDELIVER_INTENT
    If the system kills the service after onStartCommand() returns, recreate the service and call 
    onStartCommand() with the last intent that was delivered to the service. Any pending intents 
    are delivered in turn. This is suitable for services that are actively performing a job that 
    should be immediately resumed, such as downloading a file. 
    
    
5.3 如果service正在onStartCommand()中同时处理多个请求，此时有一个请求已经处理完毕，然后调用stopSelf()关闭自己，
这样就会中断掉其他的请求，为了避免这个问题，可以通过调用stopSelf(int startId)解决，
stopSelf(int startId)方法的作用：系统会为每一次请求生成一个startId，然后将startId传递给onStartCommand方法，
此时记录该id，当请求处理完成，调用stopSelf(int startId)方法并且将id传递进去，stopSelf(int startId)方法中得到
该id后会和最后一次请求的id进行比较，如果不匹配，则不会停止服务。

5.4 对于通过startService方法启动的服务，可以通过如下3种方式停止服务
1》 在其他组件中调用stopService(Intent name)方法停止服务，此方法有一个缺陷，无论该服务是否还在为其他请求服务，
此方法都会终止该服务。
2》在该服务中调用stopSelf()方法停止服务，缺点同上。
3》在该服务中调用stopSelf(int startId)方法停止服务，可以解决以上缺点。

5.5 These two paths are not entirely separate. That is, you can bind to a service that was already
 started with startService(). For example, a background music service could be started by calling
  startService() with an Intent that identifies the music to play. Later, possibly when the user
   wants to exercise some control over the player or get information about the current song, an 
   activity can bind to the service by calling bindService(). In cases like this, stopService() 
   or stopSelf() does not actually stop the service until all clients unbind. 







