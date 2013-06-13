package com.taboca.alarm;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.AlarmClock;
 
//import java.util.Calendar;

//marcio
import android.os.Vibrator;

import com.red_folder.phonegap.plugin.backgroundservice.BackgroundService;

public class MyService extends BackgroundService {
	
	private final static String TAG = MyService.class.getSimpleName();
	
	private String mHelloTo = "World";

	@Override
	protected JSONObject doWork() {
		JSONObject result = new JSONObject();
		
		try {
			SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
			String now = df.format(new Date(System.currentTimeMillis())); 

			String msg = "Hello " + this.mHelloTo + " - its currently " + now;
			result.put("Message", msg);


            /*
            Intent intent = new Intent(Intent.ACTION_VOICE_COMMAND);    
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
            */

            /*
            Intent i = new Intent(AlarmClock.ACTION_SET_ALARM); 
            i.putExtra(AlarmClock.EXTRA_MESSAGE, "New Alarm"); 
            i.putExtra(AlarmClock.EXTRA_HOUR, 18); 
            i.putExtra(AlarmClock.EXTRA_MINUTES, 32); 
            this.startActivity(i); 


            */

            /*
// works
            Intent i=new Intent(Intent.ACTION_SEND);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //i.putExtra(Intent.EXTRA_TEXT, "marcio");
            i.setType("marcio/text");
            i.putExtra(Intent.EXTRA_TEXT, "marcio");
            this.startActivity(i);
            */

    Intent i = new Intent();
    PackageManager manager = getPackageManager();
    i = manager.getLaunchIntentForPackage("com.taboca.alarm");
    i.addCategory(Intent.CATEGORY_LAUNCHER);
    this.startActivity(i);

            /*
                Calendar cal1 = Calendar.getInstance();

                String[] splits = this.mHelloTo.split(";");
                String date= splits[0];
                String hourStr = splits[1];

                String[] dates = date.split("/");
                String day = dates[0];
                String month = dates[1];
                String year = dates[2]; 

                String[] hours = hourStr.split(":");
                String hour = hours[0];
                String min = hours[1];
              
                cal1.set(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), Integer.parseInt(hour), Integer.parseInt(min));


                Calendar c = Calendar.getInstance();
                if(c.getTime() == cal1.getTime()) { 
                */

                Vibrator vibrator = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
                vibrator.vibrate(100000);
                 
                //} 
                //vibrator.cancel();

			Log.d(TAG, msg);
		} catch (JSONException e) {
		}
		
		return result;	
	}

	@Override
	protected JSONObject getConfig() {
		JSONObject result = new JSONObject();
		
		try {
			result.put("HelloTo", this.mHelloTo);
		} catch (JSONException e) {
		}
		
		return result;
	}

	@Override
	protected void setConfig(JSONObject config) {
		try {
			if (config.has("HelloTo"))
				this.mHelloTo = config.getString("HelloTo");
		} catch (JSONException e) {
		}
		
	}     

	@Override
	protected JSONObject initialiseLatestResult() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onTimerEnabled() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onTimerDisabled() {
		// TODO Auto-generated method stub
		
	}


}
