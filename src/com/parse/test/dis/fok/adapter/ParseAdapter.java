package com.parse.test.dis.fok.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.parse.ParseObject;
import com.parse.test.dis.fok.R;

public class ParseAdapter<T extends ParseObject> extends BaseAdapter{

	List<T> data;
	Context ctx;
	LayoutInflater inflater;
	final String LOG_TAG="myLogs";

	
	public ParseAdapter(Context context) {
		 data= new ArrayList<T>();
		 ctx=context;
		 inflater=LayoutInflater.from(ctx);

	}
	
	public void setData(List<T> newData){
		this.data.clear();
		this.data.addAll(newData);
	}
	
	public void cleanData(){
		if(data!=null){
			data.clear();
		}
	}
	
	
	@Override
	public int getCount() {
		
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return data.get(position);
	}


	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		T item=(T) getItem(position);
		
		Holder holder;
		if (convertView==null) {
			Log.d(LOG_TAG, "position: "+position);
			convertView= inflater.inflate(R.layout.parse_item, parent, false);
			
			holder= new Holder();
			holder.tvObject= (TextView) convertView.findViewById(R.id.tvParseItemName);	
			Log.d(LOG_TAG, "item name: "+item.getString("name"));
			convertView.setTag(holder);
			
		}else{
			holder=(Holder) convertView.getTag();
		}
		holder.tvObject.setText(item.getString("name"));
		
		return convertView;
	}
	
	private class Holder{
		
		TextView tvObject;
		
	}

}
