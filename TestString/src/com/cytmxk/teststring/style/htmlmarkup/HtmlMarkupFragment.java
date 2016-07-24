package com.cytmxk.teststring.style.htmlmarkup;

import com.cytmxk.teststring.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HtmlMarkupFragment extends Fragment {

	private TextView mTvFormat = null;
	private TextView mTvStyleForHtmlmarkup = null;
	private TextView mTvFormatStyle = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_html_markup, container,
				false);
		mTvFormat = (TextView) view.findViewById(R.id.tv_show_format_string);
		mTvStyleForHtmlmarkup = (TextView) view
				.findViewById(R.id.tv_show_style_html_string);
		mTvFormatStyle = (TextView) view.findViewById(R.id.tv_show_format_style_string);
		mTvFormat.setText(getFormatString());
		mTvStyleForHtmlmarkup.setText(getStyleForHtmlMarkupString());
		mTvFormatStyle.setText(getFormatAndStyle());
		return view;
	}

	private String getFormatString() {
		String username = "chenyang";
		int mailCount = 9;
		return String.format(getResources()
				.getString(R.string.welcome_messages), username, mailCount);
	}

	private Spanned getStyleForHtmlMarkupString() {
		return Html.fromHtml(getResources().getString(R.string.welcome));
	}
	
	private Spanned getFormatAndStyle() {
		String username = "chenyang<b>";
		int mailCount = 9;
		String string = getResources().getString(R.string.welcome_messages_format_style);
		String formatString = String.format(string, TextUtils.htmlEncode(username), mailCount);
		Spanned fromStyleHtml = Html.fromHtml(formatString);
		return fromStyleHtml;
	}
}
