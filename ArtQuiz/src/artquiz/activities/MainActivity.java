package artquiz.activities;

import com.artquiz.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
	Button start_butt;
	Button settings_butt;
	Button about_butt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        start_butt = (Button) findViewById(R.id.start_butt);
        start_butt.setOnClickListener(this);
        settings_butt = (Button) findViewById(R.id.settings_butt);
        settings_butt.setOnClickListener(this);
        about_butt = (Button) findViewById(R.id.about_butt);
        about_butt.setOnClickListener(this);
        
        
      }

      public void onClick(View v) {
        switch (v.getId()) {
        case R.id.start_butt:
        	Intent intent1 = new Intent(this, GameScreenActivity.class);
            startActivity(intent1);
          break;
        case R.id.settings_butt:
        	intent1 = new Intent(this, SettingsActivity.class);
            startActivity(intent1);
            break;
        case R.id.about_butt:
        	intent1 = new Intent(this, AboutActivity.class);
            startActivity(intent1);
            break;
        default:
          break;
        }
      }
}
