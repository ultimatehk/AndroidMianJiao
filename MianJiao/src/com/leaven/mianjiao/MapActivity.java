package com.leaven.mianjiao;

import com.baidu.mapapi.map.MapView;
import com.leaven.mianjiao.tools.CommonUtils;

import android.os.Bundle;

public class MapActivity extends BaseActivity {
	MapView mMapView = null;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		CommonUtils.initBaiduMap(this);
		setContentView(R.layout.activity_map);
		mMapView = (MapView) findViewById(R.id.bmapView);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// 在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
		mMapView.onDestroy();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// 在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
		mMapView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// 在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
		mMapView.onPause();
	}
}
