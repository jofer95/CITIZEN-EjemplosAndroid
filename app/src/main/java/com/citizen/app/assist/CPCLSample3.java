package com.citizen.app.assist;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import android.graphics.Typeface;

import com.citizen.jpos.command.CPCLConst;
import com.citizen.jpos.printer.CPCLPrinter;

public class CPCLSample3
{
	private CPCLPrinter cpclPrinter;

	public CPCLSample3()
	{
		cpclPrinter = new CPCLPrinter();
	}

	public void Profile3(int count) throws UnsupportedEncodingException
	{
		cpclPrinter.setForm(0, 200, 200, 576, count);
		cpclPrinter.setMedia(CPCLConst.CMP_CPCL_CONTINUOUS);
		cpclPrinter.printCPCLText(0, 5, 2, 30, 1, "CITIZEN SYSTEMS CO.,LTD.", 0);
		cpclPrinter.printCPCLText(0, 0, 3, 30, 70, "Micro Human Tech", 0);
		cpclPrinter.printCPCLText(0, 0, 3, 30, 110, "CITIZEN MOBILE PRINTER", 0);
		cpclPrinter.printCPCLText(0, 0, 3, 30, 150, "CMP-20 , CMP-30", 0);
		// Telephone
		cpclPrinter.printCPCLText(CPCLConst.CMP_CPCL_0_ROTATION, 7, 1, 30, 220, "Copyright 2011 Citizen Systems", 0);
		// Homepage
		cpclPrinter.printCPCL2DBarCode(0, CPCLConst.CMP_CPCL_BCS_QRCODE, 30, 300, 6, 0, 1, 0, "http://citizen-systems.com");
		cpclPrinter.printCPCLText(CPCLConst.CMP_CPCL_0_ROTATION, 7, 1, 210, 300, "citizen-systems.com", 0);
		cpclPrinter.printCPCLText(CPCLConst.CMP_CPCL_0_ROTATION, 1, 1, 210, 390, "<-- Check This.", 0);
		cpclPrinter.printForm();
	}


	public void barcode3(int count) throws UnsupportedEncodingException
	{
		cpclPrinter.setForm(0, 200, 200, 576, count);
		cpclPrinter.setMedia(CPCLConst.CMP_CPCL_CONTINUOUS);
		cpclPrinter.setCPCLBarcode(0, 2, 0);
		// CODABAR
		cpclPrinter.printCPCLBarcode(CPCLConst.CMP_CPCL_0_ROTATION, CPCLConst.CMP_CPCL_BCS_CODABAR, 2, CPCLConst.CMP_CPCL_BCS_0RATIO, 50, 109, 45, "A1234567890B", 0);
		// Code 39
		cpclPrinter.printCPCLBarcode(CPCLConst.CMP_CPCL_0_ROTATION, CPCLConst.CMP_CPCL_BCS_39, 2, CPCLConst.CMP_CPCL_BCS_1RATIO, 50, 19, 150, "01234567890", 0);
		// Code 93
		cpclPrinter.printCPCLBarcode(CPCLConst.CMP_CPCL_0_ROTATION, CPCLConst.CMP_CPCL_BCS_93, 2, CPCLConst.CMP_CPCL_BCS_1RATIO, 50, 79, 255, "01234567890", 0);
		// Code 128
		cpclPrinter.printCPCLBarcode(CPCLConst.CMP_CPCL_0_ROTATION, CPCLConst.CMP_CPCL_BCS_128, 2, CPCLConst.CMP_CPCL_BCS_1RATIO, 50, 109, 360, "01234567890", 0);
		// Print
		cpclPrinter.printForm();
	}

	public void image3(int count) throws IOException
	{
		cpclPrinter.setForm(0, 200, 200, 576, count);
		cpclPrinter.setMedia(CPCLConst.CMP_CPCL_CONTINUOUS);
		cpclPrinter.setCPCLBarcode(0, 2, 0);
		cpclPrinter.printBitmap("//sdcard//temp//citizen//logo_m.jpg", 1, 1);
//		Print saved image in Printer Memory. (NV LOGO Image)
//    	cpclPrinter.printCPCLImage("LOGO1.PCX", 1, 50);
		cpclPrinter.printBitmap("//sdcard//temp//citizen//car_s.jpg", 168, 140);
		cpclPrinter.printCPCL2DBarCode(0, CPCLConst.CMP_CPCL_BCS_PDF417, 150, 440, 2, 7, 2, 1, "http://citizen-systems.com");
		// Print
		cpclPrinter.printForm();
	}

	public void fontTypeTest(int count) throws UnsupportedEncodingException
	{
		String test = "ABCDEFGHI;1234567890";
		cpclPrinter.setForm(0, 200, 200, 576, count);
		cpclPrinter.setMedia(CPCLConst.CMP_CPCL_CONTINUOUS);
		// FONT 0,1,2,4,5,6,7
		cpclPrinter.printCPCLText(0, 0, 1, 1, 1, "FONT 0", 0);
		cpclPrinter.printCPCLText(0, 0, 1, 1, 20, test, 0);
		cpclPrinter.printCPCLText(0, 0, 1, 1, 70, "FONT 1", 0);
		cpclPrinter.printCPCLText(0, 1, 0, 1, 90, test, 0);
		cpclPrinter.printCPCLText(0, 0, 1, 1, 140, "FONT 2", 0);
		cpclPrinter.printCPCLText(0, 2, 0, 1, 160, test, 0);
		cpclPrinter.printCPCLText(0, 0, 1, 1, 210, "FONT 4", 0);
		cpclPrinter.printCPCLText(0, 4, 0, 1, 230, test, 0);
		cpclPrinter.printCPCLText(0, 0, 1, 1, 280, "FONT 5", 0);
		cpclPrinter.printCPCLText(0, 5, 0, 1, 300, test, 0);
		cpclPrinter.printCPCLText(0, 0, 1, 1, 350, "FONT 6", 0);
		cpclPrinter.printCPCLText(0, 6, 0, 1, 370, test, 0);
		cpclPrinter.printCPCLText(0, 0, 1, 1, 420, "FONT 7", 0);
		cpclPrinter.printCPCLText(0, 7, 0, 1, 440, test, 0);
		// Print
		cpclPrinter.printForm();
	}

	public void multiLineTest(int count) throws UnsupportedEncodingException
	{
		String data = "ABCDEFGHIJKLMNOPQRSTUV\r\n";
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<9;i++)
		{
			sb.append(data);
		}
		cpclPrinter.setForm(0, 200, 200, 576, count);
		cpclPrinter.setMedia(CPCLConst.CMP_CPCL_CONTINUOUS);
		// MultiLine mode.
		cpclPrinter.setMultiLine(45);
		cpclPrinter.multiLineText(0, 1, 0, 10, 20);
		cpclPrinter.multiLineData(sb.toString());
		cpclPrinter.resetMultiLine();
		// Print
		cpclPrinter.printForm();
	}

	public void countryTest(int count) throws UnsupportedEncodingException
	{
		final String [] country = {"USA","GERMANY","FRANCE","SWEDEN","SPAIN",
				"NORWAY","ITALY","UK","CP850","LATIN9"};
		for(int i=0;i<country.length;i++)
			countryTestForm(country[i]);
	}

	private void countryTestForm(String country) throws UnsupportedEncodingException
	{
		final char [] diff = {0x23,0x24,0x40,0x5B,0x5C,0x5D,0x5E,0x6C,0x7B,0x7C,0x7D,0x7E,
				0xA4,0xA6,0xA8,0xB4,0xB8,0xBC,0xBD,0xBE};
		cpclPrinter.setForm(0, 200, 200, 30, 1);
		cpclPrinter.setMedia(CPCLConst.CMP_CPCL_CONTINUOUS);
		cpclPrinter.setCountry(country);
		cpclPrinter.printCPCLText(0, 0, 1, 10, 10, new String(diff)+"   "+country, 0);
		cpclPrinter.resetCountry();
		cpclPrinter.printForm();
	}

	public void printAndroidFont(int count) throws UnsupportedEncodingException
	{
		int nLineWidth = 576;
		String data = "Receipt";
//    	String data = "영수증";
		Typeface typeface = null;

		try
		{
			cpclPrinter.setForm(0, 200, 200, 406, count);
			cpclPrinter.setMedia(CPCLConst.CMP_CPCL_CONTINUOUS);

			cpclPrinter.printAndroidFont(data, nLineWidth, 100, 0, CPCLConst.CMP_CPCL_CENTER);
			cpclPrinter.printAndroidFont("Left Alignment", nLineWidth, 24, 120, CPCLConst.CMP_CPCL_LEFT);
			cpclPrinter.printAndroidFont("Center Alignment", nLineWidth, 24, 150, CPCLConst.CMP_CPCL_CENTER);
			cpclPrinter.printAndroidFont("Right Alignment", nLineWidth, 24, 180, CPCLConst.CMP_CPCL_RIGHT);

			cpclPrinter.printAndroidFont(Typeface.SANS_SERIF, "SANS_SERIF : 1234iwIW", nLineWidth, 24, 210, CPCLConst.CMP_CPCL_LEFT);
			cpclPrinter.printAndroidFont(Typeface.SERIF, "SERIF : 1234iwIW", nLineWidth, 24, 240, CPCLConst.CMP_CPCL_LEFT);
			cpclPrinter.printAndroidFont(typeface.MONOSPACE, "MONOSPACE : 1234iwIW", nLineWidth, 24, 270, CPCLConst.CMP_CPCL_LEFT);

			// Print
			cpclPrinter.printForm();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void printMultilingualFont(int count) throws UnsupportedEncodingException
	{
		int nLineWidth = 576;
		String Koreandata = "영수증";
		String Turkishdata = "Turkish(İ,Ş,Ğ)";
		String Russiandata = "Получение";
		String Arabicdata = "الإيصال";
		String Greekdata = "Παραλαβή";
		String Japanesedata = "領収書";
		String GB2312data = "收据";
		String BIG5data = "收據";

		try
		{
			cpclPrinter.setForm(0, 200, 200, 1000, count);
			cpclPrinter.setMedia(CPCLConst.CMP_CPCL_CONTINUOUS);

			cpclPrinter.printAndroidFont("Korean Font", nLineWidth, 24, 0, CPCLConst.CMP_CPCL_LEFT);
			// Korean 100-dot size font in android device.
			cpclPrinter.printAndroidFont(Koreandata, nLineWidth, 100, 30, CPCLConst.CMP_CPCL_CENTER);

			cpclPrinter.printAndroidFont("Turkish Font", nLineWidth, 24, 140, CPCLConst.CMP_CPCL_LEFT);
			// Turkish 50-dot size font in android device.
			cpclPrinter.printAndroidFont(Turkishdata, nLineWidth, 50, 170, CPCLConst.CMP_CPCL_CENTER);

			cpclPrinter.printAndroidFont("Russian Font", nLineWidth, 24, 230, CPCLConst.CMP_CPCL_LEFT);
			// Russian 60-dot size font in android device.
			cpclPrinter.printAndroidFont(Russiandata, nLineWidth, 60, 260, CPCLConst.CMP_CPCL_CENTER);

			cpclPrinter.printAndroidFont("Arabic Font", nLineWidth, 24, 330, CPCLConst.CMP_CPCL_LEFT);
			// Arabic 100-dot size font in android device.
			cpclPrinter.printAndroidFont(Arabicdata, nLineWidth, 100, 360, CPCLConst.CMP_CPCL_CENTER);

			cpclPrinter.printAndroidFont("Greek Font", nLineWidth, 24, 470, CPCLConst.CMP_CPCL_LEFT);
			// Greek 60-dot size font in android device.
			cpclPrinter.printAndroidFont(Greekdata, nLineWidth, 60, 500, CPCLConst.CMP_CPCL_CENTER);

			cpclPrinter.printAndroidFont("Japanese Font", nLineWidth, 24, 570, CPCLConst.CMP_CPCL_LEFT);
			// Japanese 100-dot size font in android device.
			cpclPrinter.printAndroidFont(Japanesedata, nLineWidth, 100, 600, CPCLConst.CMP_CPCL_CENTER);

			cpclPrinter.printAndroidFont("GB2312 Font", nLineWidth, 24, 710, CPCLConst.CMP_CPCL_LEFT);
			// GB2312 100-dot size font in android device.
			cpclPrinter.printAndroidFont(GB2312data, nLineWidth, 100, 740, CPCLConst.CMP_CPCL_CENTER);

			cpclPrinter.printAndroidFont("BIG5 Font", nLineWidth, 24, 850, CPCLConst.CMP_CPCL_LEFT);
			// BIG5 100-dot size font in android device.
			cpclPrinter.printAndroidFont(BIG5data, nLineWidth, 100, 880, CPCLConst.CMP_CPCL_CENTER);

			// Print
			cpclPrinter.printForm();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String statusCheck()
	{
		String result = "";
		if(!(cpclPrinter.printerCheck() < 0))
		{
			int sts = cpclPrinter.status();
			if(sts == CPCLConst.CMP_STS_CPCL_NORMAL)
				return "Normal";
			if((sts & CPCLConst.CMP_STS_CPCL_BUSY) > 0)
				result = result + "Busy\r\n";
			if((sts & CPCLConst.CMP_STS_CPCL_PAPER_EMPTY) > 0)
				result = result + "Paper empty\r\n";
			if((sts & CPCLConst.CMP_STS_CPCL_COVER_OPEN) > 0)
				result = result + "Cover open\r\n";
			if((sts & CPCLConst.CMP_STS_CPCL_BATTERY_LOW) > 0)
				result = result + "Battery low\r\n";
//			result = result + cpclPrinter.voltage()+ "\r\n";
//			result = result + cpclPrinter.temperature();
		}
		else
		{
			result = "Not CPCL";
		}
		return result;
	}

	public int printResults(int count) throws UnsupportedEncodingException
	{
		int nLineWidth = 576, pResults = 0;
		String data = "Receipt";
//    	String data = "영수증";
		Typeface typeface = null;

		try
		{
			cpclPrinter.setForm(0, 200, 200, 406, count);
			cpclPrinter.setMedia(CPCLConst.CMP_CPCL_CONTINUOUS);

			cpclPrinter.printAndroidFont(data, nLineWidth, 100, 0, CPCLConst.CMP_CPCL_CENTER);
			cpclPrinter.printAndroidFont("Left Alignment", nLineWidth, 24, 120, CPCLConst.CMP_CPCL_LEFT);
			cpclPrinter.printAndroidFont("Center Alignment", nLineWidth, 24, 150, CPCLConst.CMP_CPCL_CENTER);
			cpclPrinter.printAndroidFont("Right Alignment", nLineWidth, 24, 180, CPCLConst.CMP_CPCL_RIGHT);

			cpclPrinter.printAndroidFont(Typeface.SANS_SERIF, "SANS_SERIF : 1234iwIW", nLineWidth, 24, 210, CPCLConst.CMP_CPCL_LEFT);
			cpclPrinter.printAndroidFont(Typeface.SERIF, "SERIF : 1234iwIW", nLineWidth, 24, 240, CPCLConst.CMP_CPCL_LEFT);
			cpclPrinter.printAndroidFont(typeface.MONOSPACE, "MONOSPACE : 1234iwIW", nLineWidth, 24, 270, CPCLConst.CMP_CPCL_LEFT);

			// Print
			cpclPrinter.printForm();
			pResults = cpclPrinter.printerResults(5000);
			return pResults;
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return CPCLConst.CMP_FAIL;
		}
	}

}