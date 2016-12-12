package eu.borzaindustries.hiraganaquiz;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Prefs {
	public boolean a;
	public boolean k;
	public boolean s;
	public boolean t;
	public boolean n;
	public boolean h;
	public boolean m;
	public boolean r;
	public boolean y;
	public boolean di;
	
	public void getPrefs(Context context) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);
		a = prefs.getBoolean("a", true);
		k = prefs.getBoolean("k", true);		
		s = prefs.getBoolean("s", true);		
		t = prefs.getBoolean("t", true);		
		n = prefs.getBoolean("n", true);		
		h = prefs.getBoolean("h", true);		
		m = prefs.getBoolean("m", true);		
		r = prefs.getBoolean("r", true);		
		y = prefs.getBoolean("y", true);	
		di = prefs.getBoolean("di", true);
	}
}
