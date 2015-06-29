package com.leaven.mianjiao.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import com.leaven.mianjiao.R;
import com.leaven.mianjiao.tools.CommonUtils;
import com.leaven.mianjiao.view.LoginPopupWindow.OnLoginBtnClickListener;
import com.leaven.mianjiao.view.RegisterPopupWindow.OnRegisterBtnClickListener;

public class LoginRegisterPopupWindow extends PopupWindow implements View.OnClickListener, OnLoginBtnClickListener,
		OnRegisterBtnClickListener {
	public static final int ID_BTN_LOGIN = R.id.btnLogin;
	public static final int ID_BTN_REGISTER = R.id.btnRegister;

	private Context mContext;

	private View btnLogin, btnRegister, btnCancel;
	private View mMenuView;

	private OnBtnClickListener listener;

	@SuppressLint("InflateParams")
	public LoginRegisterPopupWindow(Context context) {
		super(context);
		this.mContext = context;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.popup_window_login_register, null);
		btnLogin = mMenuView.findViewById(R.id.btnLogin);
		btnRegister = mMenuView.findViewById(R.id.btnRegister);
		btnCancel = mMenuView.findViewById(R.id.btnCancel);
		listener = new OnBtnClickListener() {

			@Override
			public void onClick(View view) {
				CommonUtils.hideSoftInputFromWindow(view);
				switch (view.getId()) {
				case ID_BTN_LOGIN:
					openLoginPopupWindow();
					break;
				case ID_BTN_REGISTER:
					openRegisterPopupWindow();
					break;
				}
			}
		};
		btnLogin.setOnClickListener(this);
		btnRegister.setOnClickListener(this);
		btnCancel.setOnClickListener(this);

		this.setContentView(mMenuView);

		this.setWidth(LayoutParams.MATCH_PARENT);

		this.setHeight(LayoutParams.MATCH_PARENT);

		this.setFocusable(true);

		Drawable dw = mMenuView.getResources().getDrawable(R.color.default_dialog_bg);
		setBackgroundDrawable(dw);
		mMenuView.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				int screenHeight = mMenuView.getResources().getDisplayMetrics().heightPixels;
				int top = mMenuView.findViewById(R.id.popLayout).getTop();
				int bottom = mMenuView.findViewById(R.id.popLayout).getBottom();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < top || y > screenHeight - bottom) {
						dismiss();
					}
				} else {
					v.performClick();
				}
				return true;
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogin:
		case R.id.btnRegister:
			if (listener != null) {
				listener.onClick(v);
			}
			break;
		}
		if (this.isShowing()) {
			this.dismiss();
		}
	}

	private void openLoginPopupWindow() {
		LoginPopupWindow window = new LoginPopupWindow(mContext, this);
		window.showAtLocation(((Activity) mContext).getWindow().getDecorView(), Gravity.CENTER, 0, 0);
	}

	private void openRegisterPopupWindow() {
		RegisterPopupWindow window = new RegisterPopupWindow(mContext, this);
		window.showAtLocation(((Activity) mContext).getWindow().getDecorView(), Gravity.CENTER, 0, 0);
	}

	public interface OnBtnClickListener {
		/**
		 * 用本类的ID_BTN_LOGIN和ID_BTN_REGISTER获得View的id
		 * 
		 * @param view
		 */
		public abstract void onClick(View view);

	}

	@Override
	public void doLogin(String username, String password) {
		// TODO 登录
		CustomToast.showToast(mContext, username + ":" + password);
	}

	@Override
	public void doRegister(String username, String password, String identifyCode) {
		// TODO 注册
		CustomToast.showToast(mContext, username + ":" + password + ":" + identifyCode);
	}

	@Override
	public void doSendIdentifyCode() {
		// TODO 发送验证码
		CustomToast.showToast(mContext, "发送验证码");
	}
}
