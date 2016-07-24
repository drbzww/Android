1 ImageView的scaleType的属性有好几种，分别是matrix（默认）、center、centerCrop、centerInside、fitCenter、
fitEnd、fitStart、fitXY

android:scaleType="center"
保持原图的大小，居中显示在ImageView中。当原图的size大于ImageView的size，超过部分裁剪处理。

android:scaleType="centerCrop"
以填满整个ImageView为目的，将原图的中心对准ImageView的中心，等比例放大原图，直到填满ImageView为止
（指的是ImageView的宽和高都要填满），原图超过ImageView的部分作裁剪处理。

android:scaleType="centerInside"
以原图完全显示为目的，将图片的内容完整居中显示，通过按比例缩小/扩大原图的size宽(高)等于或小于ImageView的宽(高)。
如果原图的size本身就小于ImageView的size，则原图的size不作任何处理，居中显示在ImageView。

android:scaleType="matrix"
不改变原图的大小，从ImageView的左上角开始绘制原图，原图超过ImageView的部分作裁剪处理。

android:scaleType="fitCenter"
与centerInside相同

android:scaleType="fitStart"
把centerInside类似，只不过是向左靠齐，不是居中显示。

android:scaleType="fitEnd"
把centerInside类似，只不过是向右靠齐，不是居中显示。

android:scaleType="fitXY"
把原图按照指定的大小在View中显示，拉伸显示图片，不保持原比例，填满ImageView.