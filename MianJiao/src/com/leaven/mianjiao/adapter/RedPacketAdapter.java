package com.leaven.mianjiao.adapter;

import java.util.List;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.leaven.mianjiao.R;
import com.leaven.mianjiao.bean.RedPacketItem;

public class RedPacketAdapter extends BaseAdapter {
	private Context mContext;
	private List<RedPacketItem> list;

	public RedPacketAdapter(Context mContext, List<RedPacketItem> list) {
		this.mContext = mContext;
		this.list = list;
	}

	public List<RedPacketItem> getList() {
		return list;
	}

	@Override
	public int getCount() {
		return list == null ? 0 : list.size();
	}

	@Override
	public RedPacketItem getItem(int position) {
		return list == null || list.size() <= position ? null : list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		RedPacketViewHolder holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.item_red_packet, null);
			holder = new RedPacketViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (RedPacketViewHolder) convertView.getTag();
		}
		setBackGround(convertView, position);
		holder.bindData(getItem(position));
		return convertView;
	}

	private void setBackGround(View view, int position) {
		switch (position % 3) {
		case 0:
			view.setBackgroundResource(R.drawable.bg_red_packet_more_dark_blue);
			break;
		case 1:
			view.setBackgroundResource(R.drawable.bg_red_packet_dark_blue);
			break;
		case 2:
			view.setBackgroundResource(R.drawable.bg_red_packet_blue);
			break;
		}
	}

	class RedPacketViewHolder {
		private TextView tvRedPacketTitle, tvRedPacketDescription;

		public RedPacketViewHolder(View v) {
			tvRedPacketTitle = (TextView) v.findViewById(R.id.tvRedPacketTitle);
			tvRedPacketDescription = (TextView) v.findViewById(R.id.tvRedPacketDescription);
		}

		public void bindData(RedPacketItem data) {
			tvRedPacketTitle.setText(data.getTitle());
			tvRedPacketDescription.setText(data.getDescription());
		}
	}

}
