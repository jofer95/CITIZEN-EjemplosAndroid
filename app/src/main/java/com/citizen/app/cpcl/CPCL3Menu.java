package com.citizen.app.cpcl;

import com.citizen.app.assist.CPCLSample3;
import com.citizen.app.cpcl.R;
import com.citizen.jpos.command.CPCLConst;

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

public class CPCL3Menu extends ListActivity
{	
	final static String[] arr = 
	{
		"Barcode",
		"CITIZEN SYSTEM",
		"Image Test",
		"Font Type Test",
		"MULTILINE",
		"COUNTRY",
		"Print Android Font",
		"Print Multilingual",
		"Print Results"
	};
	
	private String strCount;
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Log.w("OnDestroy()", "Activity Destroyed.");
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
			View.inflate(CPCL3Menu.this, R.layout.input_popup, null);
		
		final EditText number = (EditText)linear.findViewById(R.id.EditTextPopup);
		if(strCount == null)
			strCount = "1";
		number.setText(strCount);
		
		new AlertDialog.Builder(CPCL3Menu.this)
		.setTitle("Test Count.")
		.setIcon(R.drawable.icon)
		.setView(linear)
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) 
			{
				try
				{
					CPCLSample3 sample = new CPCLSample3();
					strCount = number.getText().toString();
					int count = Integer.parseInt(strCount);
					Log.d("NUM",count+"");

					String temp = sample.statusCheck();
					Toast toast = Toast.makeText(CPCL3Menu.this, temp, Toast.LENGTH_LONG);
					toast.show();

					switch(arg2)
					{
						case 0:
							sample.barcode3(count);
							break;
						case 1:
							sample.Profile3(count);
							break;
						case 2:
							sample.image3(count);
							break;
						case 3:
							sample.fontTypeTest(count);
							break;
						case 4:
							sample.multiLineTest(count);
							break;
						case 5:
							sample.countryTest(count);
							break;
						case 6:
							sample.printAndroidFont(count);
							break;
						case 7:
							sample.printMultilingualFont(count);
							break;	
						case 8:
							int results;
							Toast tst = null;
							results = sample.printResults(count);
							switch(results)
							{
							case CPCLConst.CMP_STS_CPCL_NORMAL:
								tst = Toast.makeText(CPCL3Menu.this, "Printing success", Toast.LENGTH_LONG);
								break;
							case CPCLConst.CMP_STS_CPCL_COVER_OPEN:
								tst = Toast.makeText(CPCL3Menu.this, "Cover Open", Toast.LENGTH_LONG);
								break;
							case CPCLConst.CMP_STS_CPCL_PAPER_EMPTY:
								tst = Toast.makeText(CPCL3Menu.this, "Paper Empty", Toast.LENGTH_LONG);
								break;
							case CPCLConst.CMP_STS_CPCL_TIMEOUT:
								tst = Toast.makeText(CPCL3Menu.this, "No response\r\nCheck the printer", Toast.LENGTH_LONG);
								break;
							default:
								tst = Toast.makeText(CPCL3Menu.this, "Unknown Error", Toast.LENGTH_LONG);
								break;
							}
							tst.show();
							break;	
						default:
					}
					if(arg2 != 8)
					{
						int i = 0;
						while(!(temp = sample.statusCheck()).equals("Normal"))
						{
							Log.d("CPCL3 Status["+i+"]",""+temp);
							toast = Toast.makeText(CPCL3Menu.this, i+" "+temp, Toast.LENGTH_LONG);
							toast.show();
							i++;
							Thread.sleep(2000);
						}
						Log.d("CPCL3 Status["+i+"]",""+temp);
						toast = Toast.makeText(CPCL3Menu.this, i+" "+temp, Toast.LENGTH_LONG);
						toast.show();
					}

				}
				catch(NumberFormatException e)
				{
					Log.e("NumberFormatException","Invalid Input Number.",e);
				}
				catch(Exception e)
				{
					Log.e("Exception","Unknown Exception.",e);	
				}
			}
		})
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) 
			{
				Log.d("Cancel", "Print Canceled.");
			}
		})
		.show();
	}
}