package com.leaven.mianjiao.fragment;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.tools.CommonUtils;
import com.leaven.mianjiao.view.LeftHeadLineView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutUsFragment extends Fragment implements View.OnClickListener {
	private LeftHeadLineView headLine;
	private View tvAboutUs;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_about_us, null);
		headLine = (LeftHeadLineView) v.findViewById(R.id.headLine);
		tvAboutUs = v.findViewById(R.id.tvAboutUs);
		headLine.setOnClickListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {
		CommonUtils.hideSoftInputFromWindow(v);
		switch (v.getId()) {
		case R.id.headLine:
			tvAboutUs.setVisibility(tvAboutUs.isShown() ? View.GONE : View.VISIBLE);
			break;
		}

	}
}
