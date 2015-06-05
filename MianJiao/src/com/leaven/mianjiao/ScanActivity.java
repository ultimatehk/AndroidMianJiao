package com.leaven.mianjiao;

import android.os.Bundle;
import android.widget.Toast;

import com.dtr.zxing.activity.CaptureActivity;
import com.google.zxing.Result;
import com.leaven.mianjiao.view.CustomToast;

public class ScanActivity extends CaptureActivity implements CaptureActivity.OnDecodeCompleteListener {
	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setOnDecodeCompleteListener(this);
	}

	@Override
	public void onDecodeComplete(Result rawResult, Bundle bundle) {
		// TODO gettxt
		CustomToast.showToast(this, rawResult.getText(), Toast.LENGTH_LONG);
		finish();
	}
}
