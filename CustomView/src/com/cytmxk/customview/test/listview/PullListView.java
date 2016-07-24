package com.cytmxk.customview.test.listview;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

public class PullListView extends ListView implements
		AbsListView.OnScrollListener {

	private static final int PULL_BACK_REDUCE_STEP = 1; // 回弹时每次减少的高度
	private static final int PULL_BACK_TASK_PERIOD = 100;// 回弹时递减headview高度的频率,
															// 注意以纳秒为单位
	private static final float PULL_FACTOR = 0.6F;// 下拉因子,实现下拉时的延迟效果

	private View mHeadView = null;
	private int firstItemIndex; // 第一个可见条目的索引
	private boolean isRecored;// 记录下拉的起始点
	private int startY; // 记录刚开始下拉时的触摸位置的Y坐标
	private ScheduledExecutorService schedulor; // 实现回弹效果的调度器

	// 实现回弹效果的handler,用于递减headview的高度并请求重绘
	private Handler handler = new Handler(Looper.getMainLooper()) {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			AbsListView.LayoutParams params = (LayoutParams) mHeadView
					.getLayoutParams();

			// 递减高度
			params.height -= PULL_BACK_REDUCE_STEP;
			mHeadView.setLayoutParams(params);

			// 重绘
			mHeadView.invalidate();

			// 停止回弹时递减headView高度的任务
			if (params.height <= 0) {
				schedulor.shutdownNow();
			}
		}
	};

	public PullListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		setOnScrollListener(this);

		// 创建PullListView的headerview
		mHeadView = new View(this.getContext());
		mHeadView.setBackgroundColor(Color.WHITE);
		mHeadView.setLayoutParams(new AbsListView.LayoutParams(
				LayoutParams.MATCH_PARENT, 0));
		this.addHeaderView(mHeadView);
	}

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		// TODO Auto-generated method stub
		firstItemIndex = firstVisibleItem;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:

			// 记录下拉起点状态
			if (firstItemIndex == 0) {

				isRecored = true;
				startY = (int) event.getY();

			}
			break;

		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:

			if (!isRecored) {
				break;
			}
			// 以一定的频率递减headview的高度,实现平滑回弹
			schedulor = Executors.newScheduledThreadPool(1);
			schedulor.scheduleAtFixedRate(new Runnable() {

				@Override
				public void run() {
					handler.obtainMessage().sendToTarget();

				}
			}, 0, PULL_BACK_TASK_PERIOD, TimeUnit.NANOSECONDS);

			isRecored = false;
			break;

		case MotionEvent.ACTION_MOVE:

			if (!isRecored && firstItemIndex == 0) {
				isRecored = true;
				startY = (int) event.getY();
			}

			if (!isRecored) {
				break;
			}

			int tempY = (int) event.getY();
			int moveY = tempY - startY;

			if (moveY < 0) {
				isRecored = false;
				break;
			}

			mHeadView.setLayoutParams(new AbsListView.LayoutParams(
					LayoutParams.MATCH_PARENT, (int) (moveY * PULL_FACTOR)));
			mHeadView.invalidate();

			break;
		}
		return super.onTouchEvent(event);
	}

}
