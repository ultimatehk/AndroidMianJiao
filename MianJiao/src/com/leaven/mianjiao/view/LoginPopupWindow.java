package com.leaven.mianjiao.view;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.tools.CommonUtils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.PopupWindow;

public class LoginPopupWindow extends PopupWindow implements View.OnClickListener {
	public static final int ID_BTN_LOGIN = R.id.btnLogin;
	public static final int ID_BTN_REGISTER = R.id.btnRegister;

	private EditText edtUserName, edtPassword;
	private View btnCancel, btnSure;
	private View mMenuView;

	private OnLoginBtnClickListener listener;

	@SuppressLint("InflateParams")
	public LoginPopupWindow(Context context, OnLoginBtnClickListener listener) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.popup_window_login, null);
		edtUserName = (EditText) mMenuView.findViewById(R.id.edtUserName);
		edtPassword = (EditText) mMenuView.findViewById(R.id.edtPassword);
		btnCancel = mMenuView.findViewById(R.id.btnCancel);
		btnSure = mMenuView.findViewById(R.id.btnSure);
		this.listener = listener;
		btnSure.setOnClickListener(this);
		btnCancel.setOnClickListener(this);

		this.setContentView(mMenuView);

		this.setWidth(LayoutParams.MATCH_PARENT);

		this.setHeight(LayoutParams.MATCH_PARENT);

		this.setFocusable(true);
		this.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
		this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		Drawable dw = mMenuView.getResources().getDrawable(R.color.default_dialog_bg);
		setBackgroundDrawable(dw);
		// mMenuView.setOnTouchListener(new OnTouchListener() {
		// public boolean onTouch(View v, MotionEvent event) {
		// int screenHeight = mMenuView.getResources().getDisplayMetrics().heightPixels;
		// int top = mMenuView.findViewById(R.id.popLayout).getTop();
		// int bottom = mMenuView.findViewById(R.id.popLayout).getBottom();
		// int y = (int) event.getY();
		// if (event.getAction() == MotionEvent.ACTION_UP) {
		// if (y < top || y > screenHeight - bottom) {
		// dismiss();
		// return true;
		// }
		// } else {
		// v.performClick();
		// }
		// return true;
		// }
		// });
	}

	@Override
	public void onClick(View v) {
		CommonUtils.hideSoftInputFromWindow(v);
		switch (v.getId()) {
		case R.id.btnSure:
			String username = edtUserName.getText().toString();
			String password = edtPassword.getText().toString();
			if (TextUtils.isEmpty(username)) {
				CustomToast.showToast(v.getContext(), R.string.tips_empty_username);
				return;
			}
			if (TextUtils.isEmpty(password)) {
				CustomToast.showToast(v.getContext(), R.string.tips_empty_password);
				return;
			}
			if (listener != null) {
				listener.doLogin(username, password);
			}
		case R.id.btnCancel:
			if (this.isShowing()) {
				this.dismiss();
			}
			break;
		}

	}

	public interface OnLoginBtnClickListener {
		public abstract void doLogin(String username, String password);
	}
}
