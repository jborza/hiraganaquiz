package eu.borzaindustries.hiraganaquiz;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity {
	private EditText edit;
	private TextView hiragana;
	Random random;
	RomajiHira data;
	Prefs prefs;
	private Typeface typeface;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.main);
		initialize();
	}

	@Override
	protected void onResume() {
		super.onResume();
		initialize(); // reload after prefs changed
	}

	private void initialize() {
		random = new Random();
		prefs = new Prefs();
		prefs.getPrefs(this);
		data = new RomajiHira(prefs);
		data.setRandom(random);
		typeface = Typeface
				.createFromAsset(getAssets(), "fonts/myfaircody.otf");
		edit = (EditText) findViewById(R.id.edit);
		edit.setTypeface(typeface);
		hiragana = (TextView) findViewById(R.id.hiragana);
		findViewById(R.id.button_validate).setOnClickListener(listener);
		((Button) findViewById(R.id.button_validate)).setTypeface(typeface);
		// findViewById(R.id.button_next).setOnClickListener(listener);
		// ((Button) findViewById(R.id.button_next)).setTypeface(typeface);
		findViewById(R.id.button_prefs).setOnClickListener(listener);
		((Button) findViewById(R.id.button_prefs)).setTypeface(typeface);
		((TextView) findViewById(R.id.title)).setTypeface(typeface);
		generateNew();
	}

	public OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button_validate:
				validate();
				break;
			// case R.id.button_next:
			// generateNew();
			// break;
			case R.id.button_prefs:
				Intent intent = new Intent(getBaseContext(),
						MyPreferenceActivity.class);
				startActivity(intent);
				break;
			}

		}
	};

	private void generateNew() {
		data.generate();
		hiragana.setText(data.lastHiragana);
		edit.setText("");
		Log.d("aa", data.lastRomaji);
	}

	private void validate() {
		String userText = edit.getText().toString();
		if (userText.trim().equals(data.lastRomaji)) {
			Toast.makeText(this, "CORRECT!!", Toast.LENGTH_SHORT).show();
			generateNew();
		} else {
			AlertDialog.Builder b = new Builder(this);
			b.setTitle("Sorry, wrong answer")
					.setMessage("It is \"" + data.lastRomaji + "\"")
					.setPositiveButton("Next",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									generateNew();
								}
							}).show();
		}
	}
}