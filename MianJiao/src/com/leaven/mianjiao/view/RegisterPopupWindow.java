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

public class RegisterPopupWindow extends PopupWindow implements View.OnClickListener {
	public static final int ID_BTN_LOGIN = R.id.btnLogin;
	public static final int ID_BTN_REGISTER = R.id.btnRegister;

	private EditText edtUserName, edtPassword, edtIdentifyCode;
	private View btnCancel, btnSure, btnSendIdentifyCode;
	private View mMenuView;

	private OnRegisterBtnClickListener listener;

	@SuppressLint("InflateParams")
	public RegisterPopupWindow(Context context, OnRegisterBtnClickListener listener) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.popup_window_register, null);
		edtUserName = (EditText) mMenuView.findViewById(R.id.edtUserName);
		edtIdentifyCode = (EditText) mMenuView.findViewById(R.id.edtIdentifyCode);
		edtPassword = (EditText) mMenuView.findViewById(R.id.edtPassword);
		btnCancel = mMenuView.findViewById(R.id.btnCancel);
		btnSure = mMenuView.findViewById(R.id.btnSure);
		btnSendIdentifyCode = mMenuView.findViewById(R.id.btnSendIdentifyCode);
		this.listener = listener;
		btnSure.setOnClickListener(this);
		btnCancel.setOnClickListener(this);
		btnSendIdentifyCode.setOnClickListener(this);

		this.setContentView(mMenuView);

		this.setWidth(LayoutParams.MATCH_PARENT);

		this.setHeight(LayoutParams.MATCH_PARENT);

		this.setFocusable(true);
		this.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
		this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
		this.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		Drawable dw = mMenuView.getResources().getDrawable(R.color.default_dialog_bg);
		setBackgroundDrawable(dw);
	}

	@Override
	public void onClick(View v) {
		CommonUtils.hideSoftInputFromWindow(v);
		switch (v.getId()) {
		case R.id.btnSure:
			String username = edtUserName.getText().toString();
			String password = edtPassword.getText().toString();
			String identifyCode = edtIdentifyCode.getText().toString();
			if (TextUtils.isEmpty(username)) {
				CustomToast.showToast(v.getContext(), R.string.tips_empty_username);
				return;
			}
			if (TextUtils.isEmpty(password)) {
				CustomToast.showToast(v.getContext(), R.string.tips_empty_password);
				return;
			}
			if (TextUtils.isEmpty(identifyCode)) {
				CustomToast.showToast(v.getContext(), R.string.tips_error_identify_code);
				return;
			}
			if (listener != null) {
				listener.doRegister(username, password, identifyCode);
			}
		case R.id.btnCancel:
			if (this.isShowing()) {
				this.dismiss();
			}
			break;
		case R.id.btnSendIdentifyCode:
			if (listener != null) {
				listener.doSendIdentifyCode();
			}
			break;
		}

	}

	public interface OnRegisterBtnClickListener {
		/**
		 * 执行注册操作
		 * 
		 * @param username
		 *            用户名
		 * @param password
		 *            密码
		 * @param identifyCode
		 *            验证码
		 */
		public abstract void doRegister(String username, String password, String identifyCode);

		public abstract void doSendIdentifyCode();
	}
}
