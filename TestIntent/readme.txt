1 Intent概念

Intent是一种运行时绑定（run-time binding）机制，它能在程序运行过程中连接两个不同的组件。通过Intent，你的程序
可以向Android表达某种请求或者意愿，Android会根据意愿的内容选择适当的组件来完成请求。比如，有一个Activity希望打
开网页浏览器查看某一网页的内容，那么这个Activity只需要发出WEB_SEARCH_ACTION给Android，Android就会根据Intent的
请求内容，查询各组件注册时声明的IntentFilter，找到网页浏览器的Activity来浏览网页。

Android的三个基本组件——Activity，Service和Broadcast Receiver——都是通过Intent机制激活的，不同类型的组件有不同
的传递Intent方式：

1.1 要激活一个新的Activity，或者让一个现有的Activity做新的操作，可以通过调用Context.startActivity()或者、
Activity.startActivityForResult()方法。
1.2 要启动一个新的Service，或者向一个已有的Service传递新的指令，调用Context.startService()方法或者调用
Context.bindService()方法将调用此方法的上下文对象与Service绑定。
1.3 Context.sendBroadcast()、Context.sendOrderBroadcast()、Context.sendStickBroadcast()这三个
方法可以发送Broadcast Intent。发送之后，所有已注册的并且拥有与之相匹配IntentFilter的BroadcastReceiver就会被激活。

Intent一旦发出，Android都会准确找到相匹配的一个或多个Activity，Service或者BroadcastReceiver作响应。
所以，不同类型的Intent消息不会出现重叠，即Broadcast的Intent消息只会发送给BroadcastReceiver，而决不会
发送给Activity或者Service。由startActivity()传递的消息也只会发给Activity，由startService()传递的
Intent只会发送给Service。


2 Intent的构成

说道Intent的构成，就会想到Intent的构造函数：
Intent() 空构造函数
Intent(Intent o) 拷贝构造函数
Intent(String action) 指定action类型的构造函数
Intent(String action, Uri uri) 指定Action类型和Uri的构造函数，URI主要是结合程序之间的数据共享ContentProvider
Intent(Context packageContext, Class<?> cls) 传入组件的构造函数，也就是上文提到的
Intent(String action, Uri uri, Context packageContext, Class<?> cls) 前两种结合体

Intent有以下6部分构成：
动作(Action),数据(Data),分类(Category),组件(Compent)以及扩展信(Extra)。
其中最常用的是Action和Data。

2.1 Action：用来指明要实施的动作是什么，比如说ACTION_VIEW, ACTION_EDIT等。具体的可以查阅android SDK-> 
reference中的Android.content.intent类，里面的constants中定义了所有的action。

一些常用的Action:
ACTION_CALL activity 启动一个电话.
ACTION_EDIT activity 显示用户编辑的数据.
ACTION_MAIN activity 作为Task中第一个Activity启动
ACTION_SYNC activity 同步手机与数据服务器上的数据.
ACTION_BATTERY_LOW broadcast receiver 电池电量过低警告.
ACTION_HEADSET_PLUG broadcast receiver 插拔耳机警告
ACTION_SCREEN_ON broadcast receiver 屏幕变亮警告.
ACTION_TIMEZONE_CHANGED broadcast receiver 改变时区警告.


2.2 Data：Intent的Data属性是执行动作的URI，不同的Action有不同的Data数据指定。
例如：
//叫出拨号程序
Uri uri = Uri.parse("tel:0800000123");
Intent it = new Intent(Intent.ACTION_DIAL, uri);
startActivity(it);
//直接打电话出去
Uri uri = Uri.parse("tel:0800000123");
Intent it = new Intent(Intent.ACTION_CALL, uri);
startActivity(it);

2.3 Category：一个字符串，包含了关于处理该intent的组件的种类的信息。一个intent对象可以有任意个category。
intent类定义了许多category常数.


2.4 Type：Intent的Type属性显式指定Intent的数据类型（MIME）。一般Intent的数据类型能够根据数据本身进行判定，
但是通过设置这个属性，可以强制采用显式指定的类型而不再进行推导。

MIME类型有2种形式：
a  单个记录的格式： vnd.android.cursor.item/vnd.yourcompanyname.contenttype，
如：content://com.example.transportationprovider/trains/122（一条列车信息的uri）的MIME类型是
vnd.android.cursor.item/vnd.example.rail
b 多个记录的格式：vnd.android.cursor.dir/vnd.yourcompanyname.contenttype，
如：content://com.example.transportationprovider/trains （所有列车信息）的MIME类型是
vnd.android.cursor.dir/vnd.example.rail

2.5 component：指定Intent的目标组件的类名称。通常 Android会根据Intent 中包含的其它属性的信息，
比如action、data/type、category进行查找，最终找到一个与之匹配的目标组件。但是，如果 component这个属性有指定的话，
将直接使用它指定的组件，而不再执行上述查找过程。

2.6 extras：附加信息，例如ACTION_TIMEZONE_CHANGED的intent有一个"time-zone"附加信息来指明新的时区，
而ACTION_HEADSET_PLUG有一个“state”附加信息来指示耳机是被插入还是被拔出。

3 Intent的常用用法实例

显示网页
    Uri uri = Uri.parse("http://google.com");
    Intent it = new Intent(Intent.ACTION_VIEW, uri);
    startActivity(it);

显示地图
    Uri uri = Uri.parse("geo:38.899533,-77.036476");
    Intent it = new Intent(Intent.ACTION_VIEW, uri);
    startActivity(it);

路径规划
    Uri uri = Uri.parse("http://maps.google.com/maps?f=d&saddr=startLat%20startLng&daddr=endLat%20endLng&hl=en");
    Intent it = new Intent(Intent.ACTION_VIEW, uri);
    startActivity(it);
    //where startLat, startLng, endLat, endLng are a long with 6 decimals like: 50.123456

打电话
    //叫出拨号程序
    Uri uri = Uri.parse("tel:0800000123");
    Intent it = new Intent(Intent.ACTION_DIAL, uri);
    startActivity(it);
    //直接打电话出去
    Uri uri = Uri.parse("tel:0800000123");
    Intent it = new Intent(Intent.ACTION_CALL, uri);
    startActivity(it);
    //用這個，要在 AndroidManifest.xml 中，加上

传送SMS/MMS
    //调用短信程序
    Intent it = new Intent(Intent.ACTION_VIEW, uri);
    it.putExtra("sms_body", "The SMS text");
    it.setType("vnd.android-dir/mms-sms");
    startActivity(it);

    //传送消息
    Uri uri = Uri.parse("smsto://0800000123");
    Intent it = new Intent(Intent.ACTION_SENDTO, uri);
    it.putExtra("sms_body", "The SMS text");
    startActivity(it);

    //传送 MMS
    Uri uri = Uri.parse("content://media/external/images/media/23");
    Intent it = new Intent(Intent.ACTION_SEND);
    it.putExtra("sms_body", "some text");
    it.putExtra(Intent.EXTRA_STREAM, uri);
    it.setType("image/png");
    startActivity(it);

传送 Email
    Uri uri = Uri.parse("mailto:xxx@abc.com");
    Intent it = new Intent(Intent.ACTION_SENDTO, uri);
    startActivity(it);

    Intent it = new Intent(Intent.ACTION_SEND);
    it.putExtra(Intent.EXTRA_EMAIL, "me@abc.com");
    it.putExtra(Intent.EXTRA_TEXT, "The email body text");
    it.setType("text/plain");
    startActivity(Intent.createChooser(it, "Choose Email Client"));

    Intent it=new Intent(Intent.ACTION_SEND);
    String[] tos={"me@abc.com"};
    String[] ccs={"you@abc.com"};
    it.putExtra(Intent.EXTRA_EMAIL, tos);
    it.putExtra(Intent.EXTRA_CC, ccs);
    it.putExtra(Intent.EXTRA_TEXT, "The email body text");
    it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
    it.setType("message/rfc822");
    startActivity(Intent.createChooser(it, "Choose Email Client"));

    //传送附件
    Intent it = new Intent(Intent.ACTION_SEND);
    it.putExtra(Intent.EXTRA_SUBJECT, "The email subject text");
    it.putExtra(Intent.EXTRA_STREAM, "file:///sdcard/mysong.mp3");
    sendIntent.setType("audio/mp3");
    startActivity(Intent.createChooser(it, "Choose Email Client"));

播放多媒体
    Uri uri = Uri.parse("file:///sdcard/song.mp3");
    Intent it = new Intent(Intent.ACTION_VIEW, uri);
    it.setType("audio/mp3");
    startActivity(it);
    Uri uri = Uri.withAppendedPath(MediaStore.Audio.Media.INTERNAL_CONTENT_URI, "1");
    Intent it = new Intent(Intent.ACTION_VIEW, uri);
    startActivity(it);

Market 相关
    //寻找某个应用
    Uri uri = Uri.parse("market://search?q=pname:pkg_name");
    Intent it = new Intent(Intent.ACTION_VIEW, uri);
    startActivity(it);
    //where pkg_name is the full package path for an application

    //显示某个应用的相关信息
    Uri uri = Uri.parse("market://details?id=app_id");
    Intent it = new Intent(Intent.ACTION_VIEW, uri);
    startActivity(it);
    //where app_id is the application ID, find the ID
    //by clicking on your application on Market home
    //page, and notice the ID from the address bar

Uninstall 应用程序
    Uri uri = Uri.fromParts("package", strPackageName, null);
    Intent it = new Intent(Intent.ACTION_DELETE, uri);
    startActivity(it);
    
注意：
1 当要隐式启动一个Activity时，这个Activity组件的intent-filter中必须添加
<category android:name="android.intent.category.DEFAULT"/>分类，原因如下：
系统在调用startActivity或者startActivityForResult的时候会默认为Intent加上
android.intent.category.DEFAULT分类，
由于category的匹配规则是：它要求Intent中如果包含category，那么所有的category都必须和过滤规则中的其中
一个category相同。
所以，为了Activity组件能够接受隐式调用，就必须在intent-filter中添加
<category android:name="android.intent.category.DEFAULT"/>分类
而对于BroadcastReceiver或者Service的隐式调用没有上面的特点。

2 在L上Service只可以用显示调用，而不能隐式调用。

