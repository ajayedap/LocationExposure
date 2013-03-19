package edu.cs.elon.locationexposure;

import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LocationActivity extends Activity {

	private TextView textView;
	private RelativeLayout relativeLayout;
	private LocationManager locManager;
	private int[] colors = {Color.RED, Color.GREEN};
	private int whichColor;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        
        textView = (TextView) findViewById(R.id.textview);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	
    	locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    	locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	
    	locManager.removeUpdates(locationListener);
    }
    
    private LocationListener locationListener = new LocationListener() {

		@Override
		public void onLocationChanged(Location location) {
			double currentLatitude = location.getLatitude();
			double currentLogitude = location.getLongitude();
			
			textView.setText("Latitude: " + currentLatitude + "\n" + "Longitude: " + currentLogitude);
			
			whichColor = (whichColor + 1) % colors.length;
			
			relativeLayout.setBackgroundColor(colors[whichColor]);
		}

		@Override
		public void onProviderDisabled(String provider) {}

		@Override
		public void onProviderEnabled(String provider) {}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {}
    	
    };
    
}
