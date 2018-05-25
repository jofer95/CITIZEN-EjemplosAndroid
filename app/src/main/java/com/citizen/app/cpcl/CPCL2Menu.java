package com.citizen.app.cpcl;

import java.io.IOException;

import com.citizen.app.assist.CPCLSample2;
import com.citizen.app.cpcl.R;
import com.citizen.jpos.command.CPCLConst;
import com.citizen.jpos.command.ESCPOSConst;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class CPCL2Menu extends ListActivity
{
	// Menu
	final static String[] arr = 
	{
		"Barcode",
		"CITIZEN SYSTEM",
		"2D Barcode",
		"Denmark Stamp",
		"Font Test",
		"Font Type Test",
		"Setting Test1",
		"Setting Test2",
		"MULTILINE",
		"Status Check",
		"Emulation Check",
		"Print Android Font",
		"Print Multilingual",
		"Print Results"
	};
	
	private String strCount;
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Log.d("CPCL2Menu", "OnDestroy");
	}
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1 , arr));
		getListView().setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				dialog(arg2);
			}
		});
	}
	
	// Dialog
	private void dialog(int arg1)
	{
        final int arg2 = arg1; 
		final LinearLayout linear = (LinearLayout)
			View.inflate(CPCL2Menu.this, R.layout.input_popup, null);
		
		final EditText number = (EditText)linear.findViewById(R.id.EditTextPopup);
		if(strCount == null)
			strCount = "1";
		number.setText(strCount);
		
		// except Status. 
		new AlertDialog.Builder(CPCL2Menu.this)
		.setTitle("Test Count.")
		.setIcon(R.drawable.icon)
		.setView(linear)
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) 
			{
				try
				{
			        String Label = null;
					int count = 1;
					CPCLSample2 sample = new CPCLSample2();
					strCount = number.getText().toString();
					count = Integer.parseInt(strCount);
					Log.d("NUM",count+"");
					
					String temp = sample.statusCheck();
					Toast toast = Toast.makeText(CPCL2Menu.this, temp, Toast.LENGTH_LONG);
					toast.show();
					
					Log.d("CPCL2 Status",""+temp);
					
					switch(arg2)
					{
						case 0:
							sample.barcodeTest(count);
							break;
						case 1:
							sample.profile2(count);
							break;
						case 2:
							sample.barcode2DTest(count);
							break;
						case 3:
							sample.dmStamp(count);
//							sample.imageTest(count);
							break;
						case 4:
							sample.fontTest(count);
							break;
						case 5:
							sample.fontTypeTest(count);
							break;
						case 6:
							sample.settingTest1(count);
							break;
						case 7:
							sample.settingTest2(count);
							break;
						case 8:
							sample.multiLineTest(count);
							break;
						case 9:
							temp = sample.statusCheck();
							toast = Toast.makeText(CPCL2Menu.this, temp, Toast.LENGTH_LONG);
							toast.show();
							break;
						case 10:
							String emuls = sample.emulCheck();
							Toast toasts = Toast.makeText(CPCL2Menu.this, emuls, Toast.LENGTH_LONG);
							toasts.show();
							break;
						case 11:
							sample.printAndroidFont(count);
							break;
						case 12:
							sample.printMultilingualFont(count);
							break;	
						case 13:
							int results;
							Toast tst = null;
							results = sample.printResults(count);
							switch(results)
							{
							case CPCLConst.CMP_STS_CPCL_NORMAL:
								tst = Toast.makeText(CPCL2Menu.this, "Printing success", Toast.LENGTH_LONG);
								break;
							case CPCLConst.CMP_STS_CPCL_COVER_OPEN:
								tst = Toast.makeText(CPCL2Menu.this, "Cover Open", Toast.LENGTH_LONG);
								break;
							case CPCLConst.CMP_STS_CPCL_PAPER_EMPTY:
								tst = Toast.makeText(CPCL2Menu.this, "Paper Empty", Toast.LENGTH_LONG);
								break;
							case CPCLConst.CMP_STS_CPCL_TIMEOUT:
								tst = Toast.makeText(CPCL2Menu.this, "No response\r\nCheck the printer", Toast.LENGTH_LONG);
								break;
							default:
								tst = Toast.makeText(CPCL2Menu.this, "Unknown Error", Toast.LENGTH_LONG);
								break;
							}
							tst.show();
							break;	
						default:
							break;
					}
					if(arg2 != 13)
					{
						int i = 0;
						while(!(temp = sample.statusCheck()).equals("Normal"))
						{
							Log.d("CPCL2 Status["+i+"]",""+temp);
							toast = Toast.makeText(CPCL2Menu.this, i+" "+temp, Toast.LENGTH_LONG);
							toast.show();
							i++;
							Thread.sleep(2000);
						}
						Log.d("CPCL2 Status["+i+"]",""+temp);
						toast = Toast.makeText(CPCL2Menu.this, i+" "+temp, Toast.LENGTH_LONG);
						toast.show();
					}
				}
				catch(NumberFormatException e)
				{
					Log.e("NumberFormatException","Invalid Input Nubmer.",e);
				}
				catch(IOException e)
				{
					Log.e("IO Exception", "IO Error",e);	
				}
				catch (InterruptedException e)
				{
					Log.e("InterruptedException", "IO Error",e);	
				}
			}
		})
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) 
			{
				Log.d("Cancel", "Cancel Button Clicked.");
			}
		})
		.show();
	}
}