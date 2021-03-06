package com.citizen.app.cpcl;

import com.citizen.app.assist.AlertView;
import com.citizen.app.assist.ResourceInstaller;
import com.citizen.app.port.bluetooth.BluetoothConnectMenu;
import com.citizen.app.port.wifi.WiFiConnectMenu;
import com.citizen.port.android.BluetoothPort;
import com.citizen.port.android.WiFiPort;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class CPCLTester extends TabActivity implements OnTabChangeListener
{
	private TabHost mTabHost;
	private String lastTabID;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// copy image file to sd-card.
		ResourceInstaller ri = new ResourceInstaller();
		ri.copyAssets(getAssets(),"temp/citizen");
		
		mTabHost = getTabHost();			
		mTabHost.addTab(mTabHost.newTabSpec("sample2").setIndicator("2\"").setContent(
				new Intent(this, CPCL2Menu.class)));
		mTabHost.addTab(mTabHost.newTabSpec("sample3").setIndicator("3\"").setContent(
				new Intent(this, CPCL3Menu.class)));
		mTabHost.addTab(mTabHost.newTabSpec("sample4").setIndicator("4\"").setContent(
				new Intent(this, CPCL4Menu.class)));
		mTabHost.addTab(mTabHost.newTabSpec("bluetoothMenu").setIndicator("Bluetooth").setContent(
				new Intent(this, BluetoothConnectMenu.class)));
		mTabHost.addTab(mTabHost.newTabSpec("wifiMenu").setIndicator("Wi-Fi").setContent(
				new Intent(this, WiFiConnectMenu.class)));
	
		// 0,1,2,3,4
		mTabHost.setCurrentTabByTag("wifiMenu");
		mTabHost.setOnTabChangedListener(this);
		lastTabID = mTabHost.getCurrentTabTag();
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		finish();
	}
	
	public static Handler handler = new Handler() 
	{
        @Override
        public void handleMessage(Message msg) 
        {
        	// Just Dummy Message.
        }
	};

	@Override
	public void onTabChanged(String tabId)
	{
		Log.d("onTabChanged",tabId);
		if((!BluetoothPort.getInstance().isConnected()) && (!WiFiPort.getInstance().isConnected()))
		{
			int index = mTabHost.getCurrentTab();
			if(index < 3)
			{
				mTabHost.setCurrentTabByTag(lastTabID);
				AlertView.showAlert("Interface not connected.", this);
			}
			else
			{
				lastTabID = tabId;
			}
		}
		else if(BluetoothPort.getInstance().isConnected() && (tabId.compareTo("wifiMenu") == 0))
		{
			mTabHost.setCurrentTabByTag(lastTabID);
			AlertView.showAlert("Bluetooth already connected.", this);
		}
		else if(WiFiPort.getInstance().isConnected() && (tabId.compareTo("bluetoothMenu") == 0))
		{
			mTabHost.setCurrentTabByTag(lastTabID);
			AlertView.showAlert("Wi-Fi already connected.", this);
		}
		else
		{
			lastTabID = tabId;
		}
	}
}