/**
 * 网络通信基类
 */
package com.leaven.mianjiao.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.leaven.mianjiao.tools.DebugLog;

import android.os.AsyncTask;

public class NetConnection {
	public NetConnection(final String url,final HttpMethod httpmethod,final SuccessCallback successcallback,final FailCallback failcallback,final String ...keyValues){
		new AsyncTask<Void, Void, String>() {

			@Override
			protected String doInBackground(Void... params) {
				// TODO Auto-generated method stub
				StringBuffer content = new StringBuffer();
				
				for(int i=0;i<params.length;i=i+2){
					content.append(keyValues[i]).append("=").append(keyValues[i+1]).append("&");
				}
				
				try{
					URLConnection mConnection = null;
					switch(httpmethod){
					case POST:
						mConnection = new URL(url).openConnection();
						mConnection.setDoInput(true);
						mConnection.setDoOutput(true);
						OutputStream os = mConnection.getOutputStream();
						os.write(content.toString().getBytes(Config.CHARSET));
						os.flush();
						break;
					case GET:
						mConnection = new URL(url+"?"+content.toString()).openConnection();
						mConnection.setDoInput(true);
						mConnection.setDoOutput(true);
						break;
					}
					DebugLog.i(DebugLog.LOG_Net, "请求地址"+mConnection.getURL());
					DebugLog.i(DebugLog.LOG_Net, "请求内容"+content);
					
					InputStream is = mConnection.getInputStream();
					StringBuffer resultBuffer = new StringBuffer();
					BufferedReader bfr = new BufferedReader(new InputStreamReader(is, Config.CHARSET));
					String line = null;
					while((line = bfr.readLine())!=null){
						resultBuffer.append(line);
					}
					DebugLog.i(DebugLog.LOG_Net, "请求结果"+resultBuffer.toString());
					return resultBuffer.toString();
					
				}catch(MalformedURLException e){
					e.printStackTrace();
				}catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
			
			@Override
			protected void onPostExecute(String result) {
				// TODO Auto-generated method stub
				
				if(result!=null){
					if(successcallback!=null){
						successcallback.onResult(result);
					}
				}else{
					if(failcallback!=null){
						failcallback.onFail();
					}
				}
				super.onPostExecute(result);
			}
			
			
		}.execute();
	}
	
	public static interface SuccessCallback{
		void onResult(String result);
	}
	
	public static interface FailCallback{
		void onFail();
	}
}
