1 概述
 在Android系统中，广播（Broadcast）是在组件之间传播数据（Intent）的一种机制；这些组件甚至是可以位于
 不同的进程中，这样它就像Binder机制一样，起到进程间通信的作用。

 在Android系统中，为什么需要广播机制呢？广播机制，本质上它就是一种组件间的通信方式，如果是两个组件位于
 不同的进程当中，那么可以用Binder机制来实现，如果两个组件是在同一个进程中，那么它们之间可以用来通信的
 方式就更多了，这样看来，广播机制似乎是多余的。然而，广播机制却是不可替代的，它和Binder机制不一样的地方
 在于，广播的发送者和接收者事先是不需要知道对方的存在的，这样带来的好处便是，系统的各个组件可以松耦合地
 组织在一起，这样系统就具有高度的可扩展性，容易与其它系统进行集成。
 
 从本质来说，它是一种消息订阅/发布机制，因此，使用这种消息驱动模型的第一步便是订阅消息（注册广播接收器），
 第二步是发布消息（通过sendBroadcast/sendOrderedBroadcast发送广播）。


2 广播的类型
 
BroadcastReceiver所对应的广播分两类：普通广播和有序广播。
 
2.1 普通广播是完全异步的，可以在同一时刻（逻辑上）被所有接收者接收到，消息传递的效率比较高，
但缺点是：接收者不能将处理结果传递给下一个接收者，并且无法终止广播Intent的传播。
 
普通广播通过Context.sendBroadcast()方法来发送，所有的receivers接收器接收broadcast的顺序不确定。
这种方式效率更高，但是BroadcastReceiver无法使用setResult系列，getResult系列及abort系列API。
 
2.2 有序广播是按照接收者声明的优先级别，被接收者依次接收广播。如：A的级别高于B,B的级别高于C,
那么广播先传给A，再传给B，最后传给C 。
优先级别声明在 intent-filter 元素的 android:priority 属性中，数越大优先级别越高。
可以通过在intent-filter中设置android:priority属性来设置receiver的优先级，
优先级相同的receiver其执行顺序不确定。如果BroadcastReceiver是代码中注册的话，
且其intent-filter拥有相同android:priority属性的话，先注册的将先收到广播。
 
有序广播的接收者可以终止广播Intent的传播，广播Intent的传播一旦终止，后面的接收者就无法接收到广播。

有序广播是通过Context.sendOrderedBroadcast来发送，所有的receiver依次执行。系统会根据接收者声明
的优先级别按顺序逐个执行接收者，前面的接收者有权终止广播(BroadcastReceiver.abortBroadcast())，
如果广播被前面的接收者终止，后面的接收者就再也无法获取到广播。
对于有序广播，前面的接收者可以将数据通过setResultExtras(Bundle)方法存放进结果对象，然后传给下一个接收者，
下一个接收者通过代码：
Bundle bundle = getResultExtras(true))
可以获取上一个接收者存入在结果对象中的数据。

3 BroadcastReceiver的权限限制

在Android应用开发中，有时会遇到以下两种情况，
a. 一些敏感的广播并不想让第三方的应用收到 ；
b. 要限制自己的Receiver接收某广播来源，避免被恶意的同样的ACTION的广播所干扰。

在这些场景下就需要用到广播的权限限制。
第一种场景： 谁有权收我的广播？
在这种情况下，可以在自己应用发广播时添加参数声明Receiver所需的权限。
首先，在Androidmanifest.xml中定义新的权限RECV_XXX，例如：
<permission android:name = "com.android.permission.RECV_XXX"/>  

然后，在Sender app发送广播时将此权限作为参数传入，如下：
sendBroadcast("com.android.XXX_ACTION", "com.android.permission.RECV_XXX");  

这样做之后就使得只有具有permission权限的Receiver才能接收此广播要接收该广播，
在Receiver应用的AndroidManifest.xml中要添加对应的RECV_XXX权限。
例如：
<uses-permission android:name="com.android.permission.RECV_XXX"></uses-permission>  


第二种场景： 谁有权给我发广播？
在这种情况下，需要在Receiver app的<receiver> tag中声明一下Sender app应该具有的权限。
首先同上，在AndroidManifest.xml中定义新的权限SEND_XXX，例如：
<permission android:name="com.android.SEND_XXX"/>  

然后，在Receiver app的Androidmanifest.xml中的<receiver>tag里添加权限SEND_XXX的声明，如下：
<receiver android:name=".XXXReceiver"   
          android:permission="com.android.permission.SEND_XXX">   
    <intent-filter>  
         <action android:name="com.android.XXX_ACTION" />   
    </intent-filter>  
</receiver>  

这样一来，该Receiver便只能接收来自具有该send_permission权限的应用发出的广播。
要发送这种广播，需要在Sender app的AndroidManifest.xml中也声明使用该权限即可，如下：
<uses-permission android:name="com.android.permission.SEND_XXX"></uses-permission>  

如此，可以用来对广播的来源与去处进行简单的控制。


4 普通广播的举例

4.1 在FirstActivity的onResume方法中动态注册一个广播接收器，然后通过执行一个AsyncTask（BeautyShow
的实例）任务，该任务每隔一秒发送一个广播，onReceive中根据intent中的index的值来更换图片。

4.2 在清单文件中注册一个广播接收器（广播接收器的类是SecondActivity的内部类），然后SecondActivity
初始化的过程中通过执行一个AsyncTask（BeautyShow的实例）任务，该任务每隔一秒发送一个广播，onReceive
中根据intent中的index的值来更换图片。

4.3 在清单文件中注册一个广播接收器（广播接收器的类在ShowBeautyReceiver.java文件中单独定义），然后
ThreeActivity初始化的过程中通过执行一个AsyncTask（BeautyShow的实例）任务，该任务每隔一秒发送一个广播，
onReceive中根据intent中的index的值来想主线程发送消息，ThreeActivity中的mHandler根据消息切换图片。

4.4 TestBroadcast应用中的FiveActivity、SixActivity、SevenActivity和EightActivity和
TestOtherBroadcast应用的FirstActivity、SecondActivity、ThreeActivity和FourActivity就是对
4.1、4.2、4.3和5举例中发送广播和接受广播放到不同的应用。

5 有序广播的举例
在FourActivity初始化的过程中动态注册五个广播接收器，并且设置不同的优先级，然后通过菜单发送一个广播，可以
看到图片切换的顺序与优先级的排序一致。

6 谁有权收我的广播的举例
TestBroadcast应用中的PermissionActivity1向TestOtherBroadcast应用的PermissionActivity1发送广播
，TestOtherBroadcast应用的PermissionActivity1中动态注册广播接收器。

7 谁有权给我发广播的举例
7.1 TestBroadcast应用中的PermissionActivity2向TestOtherBroadcast应用的PermissionActivity2发送广播
，TestOtherBroadcast应用的清单文件中注册广播接收器（广播接收器的类是PermissionActivity2的内部类）。

7.2 TestBroadcast应用中的PermissionActivity3向TestOtherBroadcast应用的PermissionActivity3发送广播
，TestOtherBroadcast应用的清单文件中注册广播接收器（广播接收器的类在ShowBeautyReceiver.java
文件中单独定义）。




