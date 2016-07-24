package com.cytmxk.testanimation.popupwindow;

import java.util.ArrayList;
import java.util.Arrays;
import com.cytmxk.testanimation.R;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

public class SlidingMenuWindow extends PopupWindow {

	private ListView mlvPopupMenu = null;

	public interface Callbacks {
		public ArrayList<String> buildPopmenuItems(String[] popmenuItems);
	}

	public SlidingMenuWindow(final Activity context, final Callbacks callback,
			OnItemClickListener itemsOnClick, int animationStyle) {
		super(context);
		backgroundAlpha(context, (float) 0.5);
		LayoutInflater inflater = LayoutInflater.from(context);
		View slidingMenu = inflater.inflate(R.layout.sliding_menu, null);
		mlvPopupMenu = (ListView) slidingMenu.findViewById(R.id.lv_popup_menu);
		mlvPopupMenu.setOnItemClickListener(itemsOnClick);
		String[] mpopmenuItems = context.getResources().getStringArray(
				R.array.menu_dialpad_options);
		mlvPopupMenu.setAdapter(new ArrayAdapter<String>(context,
				android.R.layout.simple_list_item_1, mpopmenuItems) {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				if (convertView == null) {
					convertView = super.getView(position, convertView, parent);
				}
				String item = getItem(position);
				int index = Arrays.asList(
						context.getResources().getStringArray(
								R.array.menu_dialpad_options)).indexOf(item);
				switch (index) {
				case 0:
					convertView.setId(R.id.menu_volte_conf_call);
					break;
				case 1:
					convertView.setId(R.id.menu_ip_dial);
					break;
				case 2:
					convertView.setId(R.id.menu_2s_pause);
					break;
				case 3:
					convertView.setId(R.id.menu_add_wait);
					break;

				default:
					break;
				}

				return convertView;
			}

		});

		// 设置SelectPicPopupWindow的View
		this.setContentView(slidingMenu);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(animationStyle);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0xffffffff);
		// 设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);
		// mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
		this.setOnDismissListener(new PopupWindow.OnDismissListener() {

			@Override
			public void onDismiss() {
				// TODO Auto-generated method stub
				backgroundAlpha(context, (float) 1.0);
			}
		});
		mlvPopupMenu.setOnTouchListener(new View.OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {

				int height = mlvPopupMenu.findViewById(R.id.lv_popup_menu)
						.getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return false;
			}
		});

	}

	/**
	 * 设置添加屏幕的背景透明度
	 * 
	 * @param bgAlpha
	 */
	public static void backgroundAlpha(Activity context, float bgAlpha) {
		WindowManager.LayoutParams lp = context.getWindow().getAttributes();
		lp.alpha = bgAlpha; // 0.0-1.0
		context.getWindow().setAttributes(lp);
	}

}
