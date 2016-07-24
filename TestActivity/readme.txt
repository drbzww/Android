1 前言
launchMode在多个Activity跳转的过程中扮演着重要的角色，它可以决定是否生成新的Activity实例，
是否重用已存在的Activity实例，是否和其他Activity实例公用一个task里。这里简单介绍一下task
的概念，task是一个具有栈结构的对象，一个task可以管理多个Activity，启动一个应用，也就创建
一个与之对应的task。
Activity一共有以下四种launchMode：
1.standard
2.singleTop
3.singleTask
4.singleInstance

2 standard的举例和结论

2.1 同一个应用中，一个Activity启动自己（launchMode设置为standard）。
在清单中将FirstActivity的launchMode设置为standard，然后在FirstActivity中通过一个按钮来启动自己，
并且在程序的界面显示FirstActivity实例的句柄信息、FirstActivity实例所在栈的ID、当前线程ID和当前进程ID。
运行结果：
每一次点击按钮后，前后界面上显示当前activity实例的句柄消息是不同的，但是前后界面上显示当前activity实例
所在栈的ID、当前线程ID和当前进程ID都是相同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下（点击两次启动自己的按钮）：
    Running activities (most recent first):
      TaskRecord{263b75b7 #12 A=com.cytmxk.testactivity U=0 sz=3}
        Run #2: ActivityRecord{f71c5db u0 com.cytmxk.testactivity/.FirstActivity t12}
        Run #1: ActivityRecord{14844501 u0 com.cytmxk.testactivity/.FirstActivity t12}
        Run #0: ActivityRecord{1a3b14b6 u0 com.cytmxk.testactivity/.FirstActivity t12}

    mResumedActivity: ActivityRecord{f71c5db u0 com.cytmxk.testactivity/.FirstActivity t12}


结论：
在应用中一个Activity的launchMode被设置为standard后，然后自己创建自己，系统会为该Activity创建两个实例，
并且这两个Activity实例在同一个栈中和在相同的进程中运行。

2.2 同一个应用中，一个Activity启动另一个Activity（launchMode设置为standard）。
2.2.1 在清单中将SecondActivity和ThreeActivity的launchMode设置为standard，然后在SecondActivity中通过一个按钮
来启动ThreeActivity，并且在程序的界面显示对应Activity实例的句柄消息、对应Activity实例所在栈的ID、当前线程ID和当前进程ID。
运行结果：
点击按钮后，前后界面上显示当前activity实例的句柄消息是不同的，但是前后界面上显示当前activity实例所在栈
的ID、当前线程ID和当前进程ID是相同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{3cd1e017 #13 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{11698cb u0 com.cytmxk.testactivity/.ThreeActivity t13}
        Run #0: ActivityRecord{36992c96 u0 com.cytmxk.testactivity/.SecondActivity t13}

    mResumedActivity: ActivityRecord{11698cb u0 com.cytmxk.testactivity/.ThreeActivity t13}
2.2.2 在清单中将SecondActivity的launchMode设置为singleTop，将ThreeActivity的launchMode设置为standard，
然后在SecondActivity中通过一个按钮来启动ThreeActivity，并且在程序的界面显示对应Activity实例的句柄消息、对应
Activity实例所在栈的ID、当前线程ID和当前进程ID。
运行结果：
点击按钮后，前后界面上显示当前activity实例的句柄消息是不同的，但是前后界面上显示当前activity实例所在栈
的ID、当前线程ID和当前进程ID是相同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{288ff0e5 #14 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{1294056 u0 com.cytmxk.testactivity/.ThreeActivity t14}
        Run #0: ActivityRecord{1ae0e7dc u0 com.cytmxk.testactivity/.SecondActivity t14}

    mResumedActivity: ActivityRecord{1294056 u0 com.cytmxk.testactivity/.ThreeActivity t14}
2.2.3 在清单中将SecondActivity的launchMode设置为singleTask，将ThreeActivity的launchMode设置为standard，
然后在SecondActivity中通过一个按钮来启动ThreeActivity，并且在程序的界面显示对应Activity实例的句柄消息、对应
Activity实例所在栈的ID、当前线程ID和当前进程ID。
运行结果：
点击按钮后，前后界面上显示当前activity实例的句柄消息是不同的，但是前后界面上显示当前activity实例所在栈
的ID、当前线程ID和当前进程ID是相同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{233af226 #15 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{32aa37a6 u0 com.cytmxk.testactivity/.ThreeActivity t15}
        Run #0: ActivityRecord{10781181 u0 com.cytmxk.testactivity/.SecondActivity t15}

    mResumedActivity: ActivityRecord{32aa37a6 u0 com.cytmxk.testactivity/.ThreeActivity t15}
2.2.4 在清单中将SecondActivity的launchMode设置为singleInstance，将ThreeActivity的launchMode设置为standard，
然后在SecondActivity中通过一个按钮来启动ThreeActivity，并且在程序的界面显示对应Activity实例的句柄消息、对应
Activity实例所在栈的ID、当前线程ID和当前进程ID。
运行结果：
点击按钮后，前后界面上显示当前activity实例的句柄消息和当前activity实例所在栈的ID是不同的，但是前后界面上显示当
前线程ID和当前进程ID是相同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{10e509d2 #18 A=com.cytmxk.testactivity U=0 sz=1}
        Run #1: ActivityRecord{3b9a4c5d u0 com.cytmxk.testactivity/.ThreeActivity t18}
      TaskRecord{2c30f05 #17 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{22d32a7c u0 com.cytmxk.testactivity/.SecondActivity t17}

    mResumedActivity: ActivityRecord{3b9a4c5d u0 com.cytmxk.testactivity/.ThreeActivity t18}
结论：
1> 在同一个应用中，当ActivityA启动一个launchMode被设置为standard的ActivityB，并且ActivityA的launchMode被设置为standard、
singleTop或者singleTask时，ActivityA和ActivityB的实例会被放到同一个栈中且按一下回退键会回到ActivityA。
2> 在同一个应用中，当ActivityA启动一个launchMode被设置为standard的ActivityB，并且ActivityA的launchMode被设置为
singleInstance时，ActivityA和ActivityB的实例会被放到不同的栈中，但是两个栈的taskAffinity都是包名且按一下回退键会
回到ActivityA。
3> 这两个Activity实例在相同的进程中运行。


2.3 两个应用中，A应用的Activity启动B应用的Activity（launchMode设置为standard）。
2.3.1 FourActivity和FirstActivity分别是在TestActivity和TestOtherActivity应用中，在清单中将
FourActivity和FirstActivity的launchMode都设置为standard，然后在FourActivity中通过一个按钮来启动FirstActivity，
并且在程序的界面显示对应Activity实例的句柄消息、对应Activity实例所在栈的ID、当前线程ID和当前进程ID。
运行结果：
点击按钮后，前后界面上显示当前activity实例的句柄消息是不同的，前后界面上显示当前activity实例所在栈
的ID是相同的，前后界面上显示当前线程ID是1（ui线程），前后界面上显示当前进程ID是不同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{a9c680a #6 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{91a6a4c u0 com.cytmxk.testotheractivity/.FirstActivity t6}
        Run #0: ActivityRecord{3ae7e075 u0 com.cytmxk.testactivity/.FourActivity t6}

    mResumedActivity: ActivityRecord{91a6a4c u0 com.cytmxk.testotheractivity/.FirstActivity t6}
注意：
1> FourActivity启动FirstActivity后，然后按home键，然后点击TestOtherActivity应用的图标，
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{29582306 #12 A=com.cytmxk.testotheractivity U=0 sz=1}
        Run #2: ActivityRecord{17b9f9e1 u0 com.cytmxk.testotheractivity/.MainActivity t12}
      TaskRecord{24e9bad9 #11 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{19da540e u0 com.cytmxk.testotheractivity/.FirstActivity t11}
        Run #0: ActivityRecord{20049120 u0 com.cytmxk.testactivity/.FourActivity t11}

    mResumedActivity: ActivityRecord{17b9f9e1 u0 com.cytmxk.testotheractivity/.MainActivity t12}
2> 将FirstActivity的allowTaskReparenting属性设置为true，然后执行1>中的步骤，
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{164fbaf #21 A=com.cytmxk.testotheractivity U=0 sz=2}
        Run #1: ActivityRecord{239c7e4d u0 com.cytmxk.testotheractivity/.FirstActivity t21}
      TaskRecord{c9d4fde #20 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{3a46f319 u0 com.cytmxk.testactivity/.FourActivity t20}

    mResumedActivity: ActivityRecord{239c7e4d u0 com.cytmxk.testotheractivity/.FirstActivity t21}
2.3.2 FourActivity和FirstActivity分别是在TestActivity和TestOtherActivity应用中，在清单中将
FourActivity的launchMode设置为singleTop,将FirstActivity的launchMode设置为standard，然后在FourActivity中
通过一个按钮来启动FirstActivity，并且在程序的界面显示对应Activity实例的句柄消息、对应Activity实例所在栈的ID、
当前线程ID和当前进程ID。
运行结果：
点击按钮后，前后界面上显示当前activity实例的句柄消息是不同的，前后界面上显示当前activity实例所在栈
的ID是相同的，前后界面上显示当前线程ID是1（ui线程），前后界面上显示当前进程ID是不同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{23cf71f8 #26 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{21c6ab0b u0 com.cytmxk.testotheractivity/.FirstActivity t26}
        Run #0: ActivityRecord{3726455b u0 com.cytmxk.testactivity/.FourActivity t26}

    mResumedActivity: ActivityRecord{21c6ab0b u0 com.cytmxk.testotheractivity/.FirstActivity t26}
注意：
1> FourActivity启动FirstActivity后，然后按home键，然后点击TestOtherActivity应用的图标，
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{1b0c56a6 #28 A=com.cytmxk.testotheractivity U=0 sz=1}
        Run #2: ActivityRecord{2dd63801 u0 com.cytmxk.testotheractivity/.MainActivity t28}
      TaskRecord{3fa297cb #27 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{2fb2822d u0 com.cytmxk.testotheractivity/.FirstActivity t27}
        Run #0: ActivityRecord{4a7e29a u0 com.cytmxk.testactivity/.FourActivity t27}

    mResumedActivity: ActivityRecord{2dd63801 u0 com.cytmxk.testotheractivity/.MainActivity t28}
2> 将FirstActivity的allowTaskReparenting属性设置为true，然后执行1>中的步骤，
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{d0f080e #33 A=com.cytmxk.testotheractivity U=0 sz=2}
        Run #1: ActivityRecord{59d2292 u0 com.cytmxk.testotheractivity/.FirstActivity t33}
      TaskRecord{1f0560b3 #32 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{34b61e22 u0 com.cytmxk.testactivity/.FourActivity t32}

    mResumedActivity: ActivityRecord{59d2292 u0 com.cytmxk.testotheractivity/.FirstActivity t33}
2.3.3 FourActivity和FirstActivity分别是在TestActivity和TestOtherActivity应用中，在清单中将
FourActivity的launchMode设置为singleTask,将FirstActivity的launchMode设置为standard，然后在FourActivity中
通过一个按钮来启动FirstActivity，并且在程序的界面显示对应Activity实例的句柄消息、对应Activity实例所在栈的ID、
当前线程ID和当前进程ID。
运行结果：
点击按钮后，前后界面上显示当前activity实例的句柄消息是不同的，前后界面上显示当前activity实例所在栈
的ID是相同的，前后界面上显示当前线程ID是1（ui线程），前后界面上显示当前进程ID是不同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{69cce52 #37 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{3c1f756c u0 com.cytmxk.testotheractivity/.FirstActivity t37}
        Run #0: ActivityRecord{1efc52dd u0 com.cytmxk.testactivity/.FourActivity t37}

    mResumedActivity: ActivityRecord{3c1f756c u0 com.cytmxk.testotheractivity/.FirstActivity t37}
注意：
1> FourActivity启动FirstActivity后，然后按home键，然后点击TestOtherActivity应用的图标，
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{29704a1a #39 A=com.cytmxk.testotheractivity U=0 sz=1}
        Run #2: ActivityRecord{2294c0c5 u0 com.cytmxk.testotheractivity/.MainActivity t39}
      TaskRecord{1a11da0b #38 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{37f99897 u0 com.cytmxk.testotheractivity/.FirstActivity t38}
        Run #0: ActivityRecord{349dc5da u0 com.cytmxk.testactivity/.FourActivity t38}

    mResumedActivity: ActivityRecord{2294c0c5 u0 com.cytmxk.testotheractivity/.MainActivity t39}
2> 将FirstActivity的allowTaskReparenting属性设置为true，然后执行1>中的步骤，
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{205678ec #44 A=com.cytmxk.testotheractivity U=0 sz=2}
        Run #1: ActivityRecord{155c8ea3 u0 com.cytmxk.testotheractivity/.FirstActivity t44}
      TaskRecord{1aeb07e5 #43 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{35edfadc u0 com.cytmxk.testactivity/.FourActivity t43}

    mResumedActivity: ActivityRecord{155c8ea3 u0 com.cytmxk.testotheractivity/.FirstActivity t44}
2.3.4 FourActivity和FirstActivity分别是在TestActivity和TestOtherActivity应用中，在清单中将
FourActivity的launchMode设置为singleInstance,将FirstActivity的launchMode设置为standard，然后在FourActivity中
通过一个按钮来启动FirstActivity，并且在程序的界面显示对应Activity实例的句柄消息、对应Activity实例所在栈的ID、
当前线程ID和当前进程ID。
运行结果：
点击按钮后，前后界面上显示当前activity实例的句柄消息是不同的，前后界面上显示当前activity实例所在栈
的ID是不同的，前后界面上显示当前线程ID是1（ui线程），前后界面上显示当前进程ID是不同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{122160c6 #48 A=com.cytmxk.testotheractivity U=0 sz=1}
        Run #1: ActivityRecord{a8e96a1 u0 com.cytmxk.testotheractivity/.FirstActivity t48}
      TaskRecord{12a977d #47 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{3aaa29d4 u0 com.cytmxk.testactivity/.FourActivity t47}

    mResumedActivity: ActivityRecord{a8e96a1 u0 com.cytmxk.testotheractivity/.FirstActivity t48}
注意：
1> FourActivity启动FirstActivity后，然后按home键，然后点击TestOtherActivity应用的图标，
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{a4fbe7 #52 A=com.cytmxk.testotheractivity U=0 sz=1}
        Run #1: ActivityRecord{3cbb15a6 u0 com.cytmxk.testotheractivity/.FirstActivity t52}
      TaskRecord{3245e60d #51 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{119e58a4 u0 com.cytmxk.testactivity/.FourActivity t51}

    mResumedActivity: ActivityRecord{3cbb15a6 u0 com.cytmxk.testotheractivity/.FirstActivity t52}
2> 将FirstActivity的allowTaskReparenting属性设置为true，然后执行1>中的步骤，
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{a03280a #56 A=com.cytmxk.testotheractivity U=0 sz=1}
        Run #1: ActivityRecord{10b1a075 u0 com.cytmxk.testotheractivity/.FirstActivity t56}
      TaskRecord{1d943689 #55 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{3d01ed90 u0 com.cytmxk.testactivity/.FourActivity t55}

    mResumedActivity: ActivityRecord{10b1a075 u0 com.cytmxk.testotheractivity/.FirstActivity t56}

结论：
1> 在两个应用中，当一个应用中ActivityA启动另一个应用中launchMode被设置为standard的ActivityB，并且ActivityA的
launchMode被设置为standard、singleTop或者singleTask时，ActivityA和ActivityB的实例会被放到同一个栈中且
按一下回退键会回到ActivityA。
2> 在两个应用中，当一个应用中ActivityA另一个应用中launchMode被设置为standard的ActivityB，并且ActivityA的
launchMode被设置为singleInstance时，ActivityA和ActivityB的实例会被放到不同的栈中，两个栈的taskAffinity分别是
对应应用包名且按一下回退键会回到ActivityA。
3> 这两个Activity实例在不同的进程中运行。

3 singleTop的举例和结论
3.1 同一个应用中，一个Activity启动自己（launchMode设置为singleTop）。
在清单中将FiveActivity的launchMode设置为singleTop，然后在FiveActivity中通过一个按钮来启动自己，
并且在程序的界面显示FirstActivity实例的句柄消息、FirstActivity实例所在栈的ID、当前线程ID和当前进程ID。
运行结果：
每一次点击按钮，不会有界面跳转，因此界面上显示当前activity实例的句柄消息是不变的、界面上显示当前activity
实例所在栈的ID是不变的、界面上显示当前线程ID和当前进程ID是不变的。
运行 adb shell dumpsys activity 命令，命令部分结果如下（点击两次启动自己的按钮）：
    Running activities (most recent first):
      TaskRecord{d525145 #62 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{2f690dbc u0 com.cytmxk.testactivity/.FiveActivity t62}

    mResumedActivity: ActivityRecord{2f690dbc u0 com.cytmxk.testactivity/.FiveActivity t62}
结论：
在应用中一个Activity的launchMode被设置为singleTop后，然后自己启动自己，系统会先在栈结构中寻找是否有
一个该Activity实例正位于栈顶，如果有则不再生成新的，而是直接使用。

3.2 同一个应用中，一个Activity启动另一个Activity（launchMode设置为singleTop）。
3.2.1 SixActivity和SevenActivity是在同一个应用中，在清单中将SixActivity的launchMode
设置为standard,将SevenActivity的launchMode设置为singleTop，然后在SixActivity中通过一个按钮来启动SevenActivity，
并且在程序的界面显示对应Activity实例的句柄消息和对应Activity实例所在栈的ID、当前线程ID和当前进程ID。
运行结果：
点击SixActivity中的按钮后，然后点击SevenActivity中的按钮，一共出现了两个界面，界面中的对应Activity实例
的句柄消息是不同的，而界面中的对应Activity实例所在栈的ID是相同的和当前线程ID和当前进程ID也是相同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下（点击两次启动自己的按钮）：
    Running activities (most recent first):
      TaskRecord{74acca5 #63 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{276e67cb u0 com.cytmxk.testactivity/.SevenActivity t63}
        Run #0: ActivityRecord{1012da9c u0 com.cytmxk.testactivity/.SixActivity t63}

    mResumedActivity: ActivityRecord{276e67cb u0 com.cytmxk.testactivity/.SevenActivity t63}

3.2.2 SixActivity和SevenActivity是在同一个应用中，在清单中将SevenActivity和SevenActivity的launchMode
设置为singleTop，然后在SixActivity中通过一个按钮来启动SevenActivity，并且在程序的界面显示对应Activity
实例的句柄消息和对应Activity实例所在栈的ID、当前线程ID和当前进程ID。
运行结果：
点击SixActivity中的按钮后，然后点击SevenActivity中的按钮，一共出现了两个界面，界面中的对应Activity实例
的句柄消息是不同的，而界面中的对应Activity实例所在栈的ID是相同的和当前线程ID和当前进程ID也是相同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下（点击两次启动自己的按钮）：
    Running activities (most recent first):
      TaskRecord{39f14f3b #66 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{2d9395dc u0 com.cytmxk.testactivity/.SevenActivity t66}
        Run #0: ActivityRecord{380a11ca u0 com.cytmxk.testactivity/.SixActivity t66}

    mResumedActivity: ActivityRecord{2d9395dc u0 com.cytmxk.testactivity/.SevenActivity t66}

3.2.3 SixActivity和SevenActivity是在同一个应用中，在清单中将SixActivity的launchMode
设置为singleTask,将SevenActivity的launchMode设置为singleTop，然后在SixActivity中通过一个按钮来启动SevenActivity，
并且在程序的界面显示对应Activity实例的句柄消息和对应Activity实例所在栈的ID、当前线程ID和当前进程ID。
运行结果：
点击SixActivity中的按钮后，然后点击SevenActivity中的按钮，一共出现了两个界面，界面中的对应Activity实例
的句柄消息是不同的，而界面中的对应Activity实例所在栈的ID是相同的和当前线程ID和当前进程ID也是相同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下（点击两次启动自己的按钮）：
    Running activities (most recent first):
      TaskRecord{23b9bd11 #69 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{f6f5528 u0 com.cytmxk.testactivity/.SevenActivity t69}
        Run #0: ActivityRecord{496d938 u0 com.cytmxk.testactivity/.SixActivity t69}

    mResumedActivity: ActivityRecord{f6f5528 u0 com.cytmxk.testactivity/.SevenActivity t69}
3.2.4 SixActivity和SevenActivity是在同一个应用中，在清单中将SixActivity的launchMode
设置为singleInstance,将SevenActivity的launchMode设置为singleTop，然后在SixActivity中通过一个按钮来启动SevenActivity，
并且在程序的界面显示对应Activity实例的句柄消息和对应Activity实例所在栈的ID、当前线程ID和当前进程ID。
运行结果：
点击SixActivity中的按钮后，然后点击SevenActivity中的按钮，一共出现了两个界面，界面中的对应Activity实例
的句柄消息和对应Activity实例所在栈的ID是不同的，而界面中当前线程ID和当前进程ID也是相同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下（点击两次启动自己的按钮）：
    Running activities (most recent first):
      TaskRecord{1e93fa10 #71 A=com.cytmxk.testactivity U=0 sz=1}
        Run #1: ActivityRecord{38f5fed3 u0 com.cytmxk.testactivity/.SevenActivity t71}
      TaskRecord{a80eee #70 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{12780469 u0 com.cytmxk.testactivity/.SixActivity t70}

    mResumedActivity: ActivityRecord{38f5fed3 u0 com.cytmxk.testactivity/.SevenActivity t71}
结论：
1> 在同一个应用中，当ActivityA启动一个launchMode被设置为singleTop的ActivityB，并且ActivityA的launchMode被设置为
standard、singleTop或者singleTask时，ActivityA和ActivityB的实例会被放到同一个栈中且按一下回退键会回到ActivityA。
2> 在同一个应用中，当ActivityA启动一个launchMode被设置为singleTop的ActivityB，并且ActivityA的launchMode被设置为
singleInstance时，ActivityA和ActivityB的实例会被放到不同的栈中，但是两个栈的taskAffinity都是包名且按一下回退键会
回到ActivityA。
3> 这两个Activity实例在相同的进程中运行。

3.3 两个应用中，A应用的Activity启动B应用的Activity（launchMode设置为singleTop）。
3.3.1 EightActivity和SecondActivity分别是在TestActivity和TestOtherActivity应用中，在清单中将EightActivity的
launchMode设置为standard,将SevenActivity的launchMode设置为singleTop，然后在EightActivity中通过一个按钮
来启动SecondActivity，并且在程序的界面显示对应Activity实例的句柄消息、对应Activity实例所在栈的ID、
当前线程ID和当前进程ID.
运行结果：
点击EightActivity中的按钮后,一共出现了两个界面，界面中的对应Activity
实例的句柄消息和进程ID是不同的，界面中的对应Activity实例所在栈的ID是相同的，当前线程ID是1（ui线程）。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{22e3c2bd #74 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{333dbd00 u0 com.cytmxk.testotheractivity/.SecondActivity t74}
        Run #0: ActivityRecord{4abfa14 u0 com.cytmxk.testactivity/.EightActivity t74}

    mResumedActivity: ActivityRecord{333dbd00 u0 com.cytmxk.testotheractivity/.SecondActivity t74}
注意：
1> EightActivity启动SecondActivity后，然后按home键，然后点击TestOtherActivity应用的图标，
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{1010e12e #76 A=com.cytmxk.testotheractivity U=0 sz=1}
        Run #2: ActivityRecord{1013b7a9 u0 com.cytmxk.testotheractivity/.MainActivity t76}
      TaskRecord{3a0bffc6 #75 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{2fdc406a u0 com.cytmxk.testotheractivity/.SecondActivity t75}
        Run #0: ActivityRecord{111451a1 u0 com.cytmxk.testactivity/.EightActivity t75}

    mResumedActivity: ActivityRecord{1013b7a9 u0 com.cytmxk.testotheractivity/.MainActivity t76}
2> 将SecondActivity的allowTaskReparenting属性设置为true，然后执行1>中的步骤，
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{165ed5a4 #80 A=com.cytmxk.testotheractivity U=0 sz=2}
        Run #1: ActivityRecord{2aa31ca9 u0 com.cytmxk.testotheractivity/.SecondActivity t80}
      TaskRecord{26e72e3b #79 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{fc00cca u0 com.cytmxk.testactivity/.EightActivity t79}

    mResumedActivity: ActivityRecord{2aa31ca9 u0 com.cytmxk.testotheractivity/.SecondActivity t80}

3.3.2 EightActivity和SecondActivity分别是在TestActivity和TestOtherActivity应用中，在清单中将
EightActivity和SecondActivity的launchMode设置为singleTop，然后在EightActivity中通过一个按钮
来启动SecondActivity，并且在程序的界面显示对应Activity实例的句柄消息、对应Activity实例所在栈的ID、
当前线程ID和当前进程ID.
运行结果：
点击EightActivity中的按钮后，一共出现了两个界面，界面中的对应Activity
实例的句柄消息和进程ID是不同的，界面中的对应Activity实例所在栈的ID是相同的，当前线程ID是1（ui线程）。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{2e9e3231 #83 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{1817bf61 u0 com.cytmxk.testotheractivity/.SecondActivity t83}
        Run #0: ActivityRecord{2c5d6ed8 u0 com.cytmxk.testactivity/.EightActivity t83}

    mResumedActivity: ActivityRecord{1817bf61 u0 com.cytmxk.testotheractivity/.SecondActivity t83}
注意：
1> EightActivity启动SecondActivity后，然后按home键，然后点击TestOtherActivity应用的图标，
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{2f2a6f23 #85 A=com.cytmxk.testotheractivity U=0 sz=1}
        Run #2: ActivityRecord{2685a052 u0 com.cytmxk.testotheractivity/.MainActivity t85}
      TaskRecord{33a5c5c2 #84 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{6f977e7 u0 com.cytmxk.testotheractivity/.SecondActivity t84}
        Run #0: ActivityRecord{e99c20d u0 com.cytmxk.testactivity/.EightActivity t84}

    mResumedActivity: ActivityRecord{2685a052 u0 com.cytmxk.testotheractivity/.MainActivity t85}
2> 将SecondActivity的allowTaskReparenting属性设置为true，然后执行1>中的步骤，
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{491d428 #89 A=com.cytmxk.testotheractivity U=0 sz=2}
        Run #1: ActivityRecord{34ac00f4 u0 com.cytmxk.testotheractivity/.SecondActivity t89}
      TaskRecord{18d8f50f #88 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{3cdc1a6e u0 com.cytmxk.testactivity/.EightActivity t88}

    mResumedActivity: ActivityRecord{34ac00f4 u0 com.cytmxk.testotheractivity/.SecondActivity t89}
3.3.3 EightActivity和SecondActivity分别是在TestActivity和TestOtherActivity应用中，在清单中将EightActivity的
launchMode设置为singleTask,将SevenActivity的launchMode设置为singleTop，然后在EightActivity中通过一个按钮
来启动SecondActivity，并且在程序的界面显示对应Activity实例的句柄消息、对应Activity实例所在栈的ID、
当前线程ID和当前进程ID.
运行结果：
点击EightActivity中的按钮后,一共出现了两个界面，界面中的对应Activity
实例的句柄消息和进程ID是不同的，界面中的对应Activity实例所在栈的ID是相同的，当前线程ID是1（ui线程）。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{15be1501 #92 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{33249dbb u0 com.cytmxk.testotheractivity/.SecondActivity t92}
        Run #0: ActivityRecord{2976dae8 u0 com.cytmxk.testactivity/.EightActivity t92}

    mResumedActivity: ActivityRecord{33249dbb u0 com.cytmxk.testotheractivity/.SecondActivity t92}
注意：
1> EightActivity启动SecondActivity后，然后按home键，然后点击TestOtherActivity应用的图标，
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{2a47e7f8 #94 A=com.cytmxk.testotheractivity U=0 sz=1}
        Run #2: ActivityRecord{1efed35b u0 com.cytmxk.testotheractivity/.MainActivity t94}
      TaskRecord{2eba80e8 #93 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{17e3a9ec u0 com.cytmxk.testotheractivity/.SecondActivity t93}
        Run #0: ActivityRecord{38097a0b u0 com.cytmxk.testactivity/.EightActivity t93}

    mResumedActivity: ActivityRecord{1efed35b u0 com.cytmxk.testotheractivity/.MainActivity t94}
2> 将SecondActivity的allowTaskReparenting属性设置为true，然后执行1>中的步骤，
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{2bab895c #98 A=com.cytmxk.testotheractivity U=0 sz=2}
        Run #1: ActivityRecord{34e71e62 u0 com.cytmxk.testotheractivity/.SecondActivity t98}
      TaskRecord{67ad2ec #97 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{1f17819f u0 com.cytmxk.testactivity/.EightActivity t97}

    mResumedActivity: ActivityRecord{34e71e62 u0 com.cytmxk.testotheractivity/.SecondActivity t98}
3.3.4 EightActivity和SecondActivity分别是在TestActivity和TestOtherActivity应用中，在清单中将EightActivity的
launchMode设置为singleInstance,将SevenActivity的launchMode设置为singleTop，然后在EightActivity中通过一个按钮
来启动SecondActivity，并且在程序的界面显示对应Activity实例的句柄消息、对应Activity实例所在栈的ID、
当前线程ID和当前进程ID.
运行结果：
点击EightActivity中的按钮后,一共出现了两个界面，界面中的对应Activity
实例的句柄消息、进程ID和所在栈的ID是不同的，当前线程ID是1（ui线程）。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{358db076 #102 A=com.cytmxk.testotheractivity U=0 sz=1}
        Run #1: ActivityRecord{27a09711 u0 com.cytmxk.testotheractivity/.SecondActivity t102}
      TaskRecord{35c9ad92 #101 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{1e0271d u0 com.cytmxk.testactivity/.EightActivity t101}

    mResumedActivity: ActivityRecord{27a09711 u0 com.cytmxk.testotheractivity/.SecondActivity t102}
注意：
1> EightActivity启动SecondActivity后，然后按home键，然后点击TestOtherActivity应用的图标，
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{12e719ce #107 A=com.cytmxk.testotheractivity U=0 sz=1}
        Run #1: ActivityRecord{32eec9 u0 com.cytmxk.testotheractivity/.SecondActivity t107}
      TaskRecord{179a8c55 #106 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{1ab00b0c u0 com.cytmxk.testactivity/.EightActivity t106}

    mResumedActivity: ActivityRecord{32eec9 u0 com.cytmxk.testotheractivity/.SecondActivity t107}
2> 将SecondActivity的allowTaskReparenting属性设置为true，然后执行1>中的步骤，
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{6ceec07 #111 A=com.cytmxk.testotheractivity U=0 sz=1}
        Run #1: ActivityRecord{31d43246 u0 com.cytmxk.testotheractivity/.SecondActivity t111}
      TaskRecord{34f91d29 #110 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{125cab0 u0 com.cytmxk.testactivity/.EightActivity t110}

    mResumedActivity: ActivityRecord{31d43246 u0 com.cytmxk.testotheractivity/.SecondActivity t111}
结论：
1> 在两个应用中，当一个应用中ActivityA启动另一个应用中launchMode被设置为singleTop的ActivityB，并且ActivityA的
launchMode被设置为standard、singleTop或者singleTask时，ActivityA和ActivityB的实例会被放到同一个栈中且
按一下回退键会回到ActivityA。
2> 在两个应用中，当一个应用中ActivityA另一个应用中launchMode被设置为singleTop的ActivityB，并且ActivityA的
launchMode被设置为singleInstance时，ActivityA和ActivityB的实例会被放到不同的栈中，两个栈的taskAffinity分别是
对应应用包名且按一下回退键会回到ActivityA。
3> 这两个Activity实例在不同的进程中运行。


4 singleTask的举例和结论
4.1 同一个应用中，一个Activity启动自己（launchMode设置为singleTask）。
在清单中将NineActivity的launchMode设置为singleTask，然后在NineActivity中通过一个按钮来启动自己，
并且在程序的界面显示NineActivity实例的句柄消息、NineActivity实例所在栈的ID、当前线程ID和当前进程ID。
运行结果：
每一次点击按钮，不会有界面跳转，因此界面上显示当前activity实例的句柄消息是不变的和界面上显示当前activity
实例所在栈的ID是不变的。
运行 adb shell dumpsys activity 命令，命令部分结果如下（点击两次启动自己的按钮）：
    Running activities (most recent first):
      TaskRecord{3afa6076 #112 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{1120711 u0 com.cytmxk.testactivity/.NineActivity t112}

    mResumedActivity: ActivityRecord{1120711 u0 com.cytmxk.testactivity/.NineActivity t112}
结论：
在应用中一个Activity的launchMode被设置为singleTask后，然后自己启动自己，系统会先在栈结构中寻找是否有
一个该Activity实例正位于栈中，如果有则不再生成新的，而是直接使用。

4.2 同一个应用中，一个Activity启动另一个Activity（launchMode设置为singleTop）。
4.2.1 TenActivity和ElevenActivity是在同一个应用中，在清单中将TenActivity的
launchMode设置为standard,将ElevenActivity的launchMode设置为singleTask，然后在TenActivity中通过一个按钮
来启动ElevenActivity，并且在程序的界面显示对应Activity实例的句柄消息和对应Activity实例所在栈的ID、当前线程ID
和当前进程ID。
运行结果：
点击TenActivity中的按钮后，一共出现了两个界面，界面中的对应Activity实例
的句柄消息是不同的，而界面中的对应Activity实例所在栈的ID、当前线程ID和当前进程ID都是相同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{189d3fa8 #113 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{1f9b7146 u0 com.cytmxk.testactivity/.ElevenActivity t113}
        Run #0: ActivityRecord{3c58fbcb u0 com.cytmxk.testactivity/.TenActivity t113}

    mResumedActivity: ActivityRecord{1f9b7146 u0 com.cytmxk.testactivity/.ElevenActivity t113}
注意：
将ElevenActivity的属性taskAffinity设置为 com.cytmxk.testactivity.ElevenActivity，与上面操作相同。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{23770465 #118 A=com.cytmxk.testactivity.ElevenActivity U=0 sz=1}
        Run #1: ActivityRecord{e72195c u0 com.cytmxk.testactivity/.ElevenActivity t118}
      TaskRecord{10a4de14 #117 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{39d3a067 u0 com.cytmxk.testactivity/.TenActivity t117}

    mResumedActivity: ActivityRecord{e72195c u0 com.cytmxk.testactivity/.ElevenActivity t118}
4.2.2 TenActivity和ElevenActivity是在同一个应用中，在清单中将TenActivity的
launchMode设置为singleTop,将ElevenActivity的launchMode设置为singleTask，然后在TenActivity中通过一个按钮
来启动ElevenActivity，并且在程序的界面显示对应Activity实例的句柄消息和对应Activity实例所在栈的ID、当前线程ID
和当前进程ID。
运行结果：
点击TenActivity中的按钮后，一共出现了两个界面，界面中的对应Activity实例
的句柄消息是不同的，而界面中的对应Activity实例所在栈的ID、当前线程ID和当前进程ID都是相同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{274e4c73 #129 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{1b6af74c u0 com.cytmxk.testactivity/.ElevenActivity t129}
        Run #0: ActivityRecord{7a460e2 u0 com.cytmxk.testactivity/.TenActivity t129}

    mResumedActivity: ActivityRecord{1b6af74c u0 com.cytmxk.testactivity/.ElevenActivity t129}
注意：
将ElevenActivity的属性taskAffinity设置为 com.cytmxk.testactivity.ElevenActivity，与上面操作相同。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{1c3805 #136 A=com.cytmxk.testactivity.ElevenActivity U=0 sz=1}
        Run #1: ActivityRecord{10a3577c u0 com.cytmxk.testactivity/.ElevenActivity t136}
      TaskRecord{808a378 #135 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{7a74cdb u0 com.cytmxk.testactivity/.TenActivity t135}

    mResumedActivity: ActivityRecord{10a3577c u0 com.cytmxk.testactivity/.ElevenActivity t136}    

4.2.3 TenActivity和ElevenActivity是在同一个应用中，在清单中将TenActivity的
launchMode设置为singleTask,将ElevenActivity的launchMode设置为singleTask，然后在TenActivity中通过一个按钮
来启动ElevenActivity，并且在程序的界面显示对应Activity实例的句柄消息和对应Activity实例所在栈的ID、当前线程ID
和当前进程ID。
运行结果：
点击TenActivity中的按钮后，一共出现了两个界面，界面中的对应Activity实例
的句柄消息是不同的，而界面中的对应Activity实例所在栈的ID、当前线程ID和当前进程ID都是相同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{3f81a73a #138 A=com.cytmxk.testactivity U=0 sz=2}
        Run #1: ActivityRecord{b503495 u0 com.cytmxk.testactivity/.ElevenActivity t138}
        Run #0: ActivityRecord{140ade65 u0 com.cytmxk.testactivity/.TenActivity t138}

    mResumedActivity: ActivityRecord{b503495 u0 com.cytmxk.testactivity/.ElevenActivity t138}
注意：
将ElevenActivity的属性taskAffinity设置为 com.cytmxk.testactivity.ElevenActivity，与上面操作相同。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{3e2cbb43 #140 A=com.cytmxk.testactivity.ElevenActivity U=0 sz=1}
        Run #1: ActivityRecord{316f08f2 u0 com.cytmxk.testactivity/.ElevenActivity t140}
      TaskRecord{c96b00f #139 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{345fe16e u0 com.cytmxk.testactivity/.TenActivity t139}

    mResumedActivity: ActivityRecord{316f08f2 u0 com.cytmxk.testactivity/.ElevenActivity t140}
4.2.4 TenActivity和ElevenActivity是在同一个应用中，在清单中将TenActivity的
launchMode设置为singleInstance,将ElevenActivity的launchMode设置为singleTask，然后在TenActivity中通过一个按钮
来启动ElevenActivity，并且在程序的界面显示对应Activity实例的句柄消息和对应Activity实例所在栈的ID、当前线程ID
和当前进程ID。
运行结果：
点击TenActivity中的按钮后，一共出现了两个界面，界面中的对应Activity实例
的句柄消息和所在栈的ID是不同的，而当前线程ID和当前进程ID都是相同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下：    
    Running activities (most recent first):
      TaskRecord{268e72a8 #144 A=com.cytmxk.testactivity U=0 sz=1}
        Run #1: ActivityRecord{1531acb u0 com.cytmxk.testactivity/.ElevenActivity t144}
      TaskRecord{2615c0cd #143 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{252de664 u0 com.cytmxk.testactivity/.TenActivity t143}

    mResumedActivity: ActivityRecord{1531acb u0 com.cytmxk.testactivity/.ElevenActivity t144}
注意：
将ElevenActivity的属性taskAffinity设置为 com.cytmxk.testactivity.ElevenActivity，与上面操作相同。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{3a425ec #151 A=com.cytmxk.testactivity.ElevenActivity U=0 sz=1}
        Run #1: ActivityRecord{2a96409f u0 com.cytmxk.testactivity/.ElevenActivity t151}
      TaskRecord{dcfc1da #150 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{28ec3f85 u0 com.cytmxk.testactivity/.TenActivity t150}

    mResumedActivity: ActivityRecord{2a96409f u0 com.cytmxk.testactivity/.ElevenActivity t151}
结论：
1> 在同一个应用中，当ActivityA启动一个launchMode被设置为singleTask的ActivityB，并且ActivityA的launchMode被设置为
standard、singleTop或者singleTask时，ActivityA和ActivityB的实例会被放到同一个栈中且按一下回退键会回到ActivityA。
2> 在同一个应用中，当ActivityA启动一个launchMode被设置为singleTop的ActivityB，并且ActivityA的launchMode被设置为
singleInstance时，ActivityA和ActivityB的实例会被放到不同的栈中，但是两个栈的taskAffinity都是包名且按一下回退键会
回到ActivityA。
3> 这两个Activity实例在相同的进程中运行。

4.3 两个应用中，A应用的Activity启动B应用的Activity（launchMode设置为singleTask）。
4.3.1 TwelveActivity和ThreeActivity分别是在TestActivity和TestOtherActivity应用中，在清单中将
TwelveActivity的launchMode设置为standard，将ThreeActivity的launchMode设置为singleTask，
然后在TwelveActivity中通过一个按钮来启动ThreeActivity，并且在程序的界面显示对应Activity实例的句柄消息、
对应Activity实例所在栈的ID、当前线程ID和当前进程ID
运行结果：
点击TwelveActivity中的按钮，一共出现了两个界面，界面中的对应Activity
实例的句柄消息是不同的，界面中的对应Activity实例所在栈的ID是不同的，当前线程ID是1（ui线程），
当前进程ID是不同的。
结论：
在不同一个应用中，启动一个aunchMode被设置为singleTask的Activity，前后两个Activity实例会被放到不同栈中。
且两个Activity实例是在不同的进程中运行的。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{3ab2c19e #13 A=com.cytmxk.testotheractivity U=0 sz=1}
        Run #1: ActivityRecord{be813d9 u0 com.cytmxk.testotheractivity/.ThreeActivity t13}
      TaskRecord{dc8a3ef #12 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{4d3b8ce u0 com.cytmxk.testactivity/.TwelveActivity t12}

    mResumedActivity: ActivityRecord{be813d9 u0 com.cytmxk.testotheractivity/.ThreeActivity t13}

4.3.2 TwelveActivity和ThreeActivity分别是在TestActivity和TestOtherActivity应用中，在清单中将
TwelveActivity的launchMode设置为singleTop，将ThreeActivity的launchMode设置为singleTask，
然后在TwelveActivity中通过一个按钮来启动ThreeActivity，并且在程序的界面显示对应Activity实例的句柄消息、
对应Activity实例所在栈的ID、当前线程ID和当前进程ID
运行结果：
点击TwelveActivity中的按钮，一共出现了两个界面，界面中的对应Activity
实例的句柄消息是不同的，界面中的对应Activity实例所在栈的ID是不同的，当前线程ID是1（ui线程），
当前进程ID是不同的。
结论：
在不同一个应用中，启动一个aunchMode被设置为singleTask的Activity，前后两个Activity实例会被放到不同栈中。
且两个Activity实例是在不同的进程中运行的。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{3fd3d387 #31 A=com.cytmxk.testotheractivity U=0 sz=1}
        Run #1: ActivityRecord{3cc607c6 u0 com.cytmxk.testotheractivity/.ThreeActivity t31}
      TaskRecord{3b15af8a #30 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{1ae395f5 u0 com.cytmxk.testactivity/.TwelveActivity t30}

    mResumedActivity: ActivityRecord{3cc607c6 u0 com.cytmxk.testotheractivity/.ThreeActivity t31}
4.3.3 TwelveActivity和ThreeActivity分别是在TestActivity和TestOtherActivity应用中，在清单中将
TwelveActivity的launchMode设置为singleTask，将ThreeActivity的launchMode设置为singleTask，
然后在TwelveActivity中通过一个按钮来启动ThreeActivity，并且在程序的界面显示对应Activity实例的句柄消息、
对应Activity实例所在栈的ID、当前线程ID和当前进程ID
运行结果：
点击TwelveActivity中的按钮，一共出现了两个界面，界面中的对应Activity
实例的句柄消息是不同的，界面中的对应Activity实例所在栈的ID是不同的，当前线程ID是1（ui线程），
当前进程ID是不同的。
结论：
在不同一个应用中，启动一个aunchMode被设置为singleTask的Activity，前后两个Activity实例会被放到不同栈中。
且两个Activity实例是在不同的进程中运行的。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{3ea7196a #35 A=com.cytmxk.testotheractivity U=0 sz=1}
        Run #1: ActivityRecord{252fb55 u0 com.cytmxk.testotheractivity/.ThreeActivity t35}
      TaskRecord{b85b525 #34 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{133e151c u0 com.cytmxk.testactivity/.TwelveActivity t34}

    mResumedActivity: ActivityRecord{252fb55 u0 com.cytmxk.testotheractivity/.ThreeActivity t35}
4.3.4 TwelveActivity和ThreeActivity分别是在TestActivity和TestOtherActivity应用中，在清单中将
TwelveActivity的launchMode设置为singleInstance，将ThreeActivity的launchMode设置为singleTask，
然后在TwelveActivity中通过一个按钮来启动ThreeActivity，并且在程序的界面显示对应Activity实例的句柄消息、
对应Activity实例所在栈的ID、当前线程ID和当前进程ID
运行结果：
点击TwelveActivity中的按钮，一共出现了两个界面，界面中的对应Activity
实例的句柄消息是不同的，界面中的对应Activity实例所在栈的ID是不同的，当前线程ID是1（ui线程），
当前进程ID是不同的。
结论：
在不同一个应用中，启动一个aunchMode被设置为singleTask的Activity，前后两个Activity实例会被放到不同栈中。
且两个Activity实例是在不同的进程中运行的。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{2b438939 #37 A=com.cytmxk.testotheractivity U=0 sz=1}
        Run #1: ActivityRecord{35451d00 u0 com.cytmxk.testotheractivity/.ThreeActivity t37}
      TaskRecord{3a32adb9 #36 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{a00380 u0 com.cytmxk.testactivity/.TwelveActivity t36}

    mResumedActivity: ActivityRecord{35451d00 u0 com.cytmxk.testotheractivity/.ThreeActivity t37}

5 singleInstance的举例和结论
5.1 同一个应用中，一个Activity启动自己（launchMode设置为singleInstance）。
在清单中将ThirteenActivity的launchMode设置为singleInstance，然后在ThirteenActivity中通过一个按钮
来启动自己，并且在程序的界面显示ThirteenActivity实例的句柄消息、ThirteenActivity实例所在栈的ID、当前线程
ID和当前进程ID。
运行结果：
每一次点击按钮，不会有界面跳转，因此界面上显示当前activity实例的句柄消息是不变的、界面上显示当前activity
实例所在栈的ID是不变的、界面上显示当前线程ID和当前进程ID是相同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下（点击两次启动自己的按钮）：
    Running activities (most recent first):
      TaskRecord{3757dcff #38 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{46ec1e u0 com.cytmxk.testactivity/.ThirteenActivity t38}

    mResumedActivity: ActivityRecord{46ec1e u0 com.cytmxk.testactivity/.ThirteenActivity t38}
结论：
当应用中一个Activity的launchMode被设置为singleInstance后，然后启动该Activity，系统会先在栈结构中
寻找是否有一个该Activity实例正位于某个栈中，如果有则不再生成新的，由于Activity实例位于栈顶，直接将
其显示到幕前，否则系统会启用一个新的栈结构，将该Acitvity实例放置于这个新的栈结构中，并保证不再有
其他Activity实例进入。

5.2 同一个应用中，一个Activity启动另一个Activity（launchMode设置为singleInstance）。
FourteenActivity和FifteenActivity是在同一个应用中，在清单中将FourteenActivity的launchMode设置为
standard，将FifteenActivity的launchMode设置为singleInstance，然后在FourteenActivity中通过一个
按钮来启动FifteenActivity，并且在程序的界面显示对应Activity实例的句柄消息和对应Activity实例所在栈的ID、
当前线程ID和当前进程ID。
运行结果：
点击FourteenActivity中的按钮，一共出现了两个界面，界面中的对应Activity实例的句柄消息是不同的，
而界面中的对应Activity实例所在栈的ID是不同的，界面上显示的当前线程ID和当前进程ID是相同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{1b08f7ad #46 A=com.cytmxk.testactivity U=0 sz=1}
        Run #1: ActivityRecord{34622cc4 u0 com.cytmxk.testactivity/.FifteenActivity t46}
      TaskRecord{1dd6aa79 #45 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{16fb40 u0 com.cytmxk.testactivity/.FourteenActivity t45}

    mResumedActivity: ActivityRecord{34622cc4 u0 com.cytmxk.testactivity/.FifteenActivity t46}
结论：
在同一个应用中，启动一个aunchMode被设置为singleInstance的Activity，系统会先在栈结构中寻找是否有
一个该Activity实例正位于某个栈中，如果有则不再生成新的，由于Activity实例位于栈顶，直接将其显示到幕前，
否则就会会启用一个新的栈结构，将该Acitvity实例放置于这个新的栈结构中，并保证不再有其他Activity实例进入。

5.3 SixteenActivity和FourActivity分别是在TestActivity和TestOtherActivity应用中，在清单中将
SixteenActivity的launchMode设置为standard，将FourActivity的launchMode设置为singleInstance，
然后在SixteenActivity中通过一个按钮来启动FourActivity，并且在程序的界面显示对应Activity实例的句柄消息、
对应Activity实例所在栈的ID、当前线程ID和当前进程ID
运行结果：
点击SixteenActivity中的按钮，一共出现了两个界面，界面中的对应Activity
实例的句柄消息是不同的，界面中的对应Activity实例所在栈的ID是不同的，当前线程ID是1（ui线程），
当前进程ID是不同的。
运行 adb shell dumpsys activity 命令，命令部分结果如下：
    Running activities (most recent first):
      TaskRecord{1c2e611a #52 A=com.cytmxk.testotheractivity U=0 sz=1}
        Run #1: ActivityRecord{5b0d3c5 u0 com.cytmxk.testotheractivity/.FourActivity t52}
      TaskRecord{c1a195 #51 A=com.cytmxk.testactivity U=0 sz=1}
        Run #0: ActivityRecord{12dd2d4c u0 com.cytmxk.testactivity/.SixteenActivity t51}

    mResumedActivity: ActivityRecord{5b0d3c5 u0 com.cytmxk.testotheractivity/.FourActivity t52}
结论：
在不同应用中，启动一个aunchMode被设置为singleInstance的Activity，系统会先在栈结构中寻找是否有
一个该Activity实例正位于某个栈中，如果有则不再生成新的，由于Activity实例位于栈顶，直接将其显示到幕前，
否则就会会启用一个新的栈结构，将该Acitvity实例放置于这个新的栈结构中，并保证不再有其他Activity实例进入。
且两个Activity实例是在不同的进程中运行的。


6 在清单文件中，Activity 元素一些属性如下：
android:name　　-------------　　Activity类名

android:label　　--------------　　Activity的名字，如果此项不设置，那么默认显示的Activity名则为类名

android:icon　　--------------　　Activity的图标

android:permission　　-------　　申明此Activity的权限，这意味着只有提供了该权限的应用才能启动此Activity

android:process　　----------　　表示该Activity是否运行在另外一个进程，如果设置了此项，那么将会在包名后面加上这段字符串表示另一进程的名字

7 全屏显示activity有两种方法：
7.1 在AndroidManifest.xml里面添加一句：
Android:theme="@android:style/Theme.NoTitleBar.Fullscreen"  
7.2 在activity的onCreate里面setContentView之前添加一下两行代码：
// 隐藏android系统的状态栏  
this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
// 隐藏应用程序的标题栏，即当前activity的标题栏
this.requestWindowFeature(Window.FEATURE_NO_TITLE);
