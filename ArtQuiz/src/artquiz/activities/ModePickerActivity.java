package artquiz.activities;

import com.artquiz.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ModePickerActivity extends Activity implements OnClickListener{
	
	Button classic_butt;
	
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_modepicker);
	    classic_butt = (Button) findViewById(R.id.classic_butt);
        classic_butt.setOnClickListener(this);
      }

      public void onClick(View v) {
        switch (v.getId()) {
        case R.id.classic_butt:
        	Intent intent = new Intent(this, GameScreenActivity.class);
            startActivity(intent);
          break;
        default:
          break;
        }
      }

}
