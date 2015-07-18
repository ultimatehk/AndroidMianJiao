package com.leaven.mianjiao.view;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.leaven.mianjiao.R;

public class UserCenterViewSwitcher extends ViewSwitcher {

	private EditText switchViewEditText;
	private View switchViewConfirmButton;
	private TextView switchViewTextView;

	public UserCenterViewSwitcher(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public UserCenterViewSwitcher(Context context) {
		this(context, null);
	}

	private void init() {
		LayoutInflater.from(getContext()).inflate(R.layout.view_switch_text_and_edit, this);
		switchViewEditText = (EditText) findViewById(R.id.switchViewEditText);
		switchViewTextView = (TextView) findViewById(R.id.switchViewTextView);
		switchViewConfirmButton = findViewById(R.id.switchViewConfirmButton);

	}

	public String getEditText() {
		return switchViewEditText.getText().toString();
	}

	public void setText(String modifyString) {
		switchViewTextView.setText(modifyString);
	}

	public void setEditChangeListener(TextWatcher watcher) {
		switchViewEditText.addTextChangedListener(watcher);
	}

	public void setOnClickConfirmButtonListener(View.OnClickListener listener) {
		switchViewConfirmButton.setOnClickListener(listener);
	}
}
