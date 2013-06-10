package com.taboca.alarm;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import android.content.Intent;
import android.provider.AlarmClock;

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


         Vibrator vibrator = (Vibrator) getSystemService(this.VIBRATOR_SERVICE);
                    vibrator.vibrate(100000);

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
