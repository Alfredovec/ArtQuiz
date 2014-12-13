package artquiz.activities;

import com.artquiz.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class SettingsActivity extends Activity {
	
	public static int picturesToPlay = 10;
	  
	protected void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_settings);
        RadioGroup radiogroup = (RadioGroup) findViewById(R.id.radioGroup1);
        radiogroup.setOnCheckedChangeListener(new OnCheckedChangeListener() 
        {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) 
			{
				switch (checkedId)
				{
				case R.id.radio0:
					picturesToPlay = 10;
					break;
				case R.id.radio1:
					picturesToPlay = 30;
					break;
				case R.id.radio2:
					picturesToPlay = 50;
					break;
				default:
					picturesToPlay = 10;
					break;
				}
			}
        	
        });
        switch (picturesToPlay)
        {
        	case 10:
        		((RadioButton)radiogroup.getChildAt(0)).setChecked(true);
        		break;
        	case 30:
        		((RadioButton)radiogroup.getChildAt(1)).setChecked(true);
        		break;
        	case 50:
        		((RadioButton)radiogroup.getChildAt(2)).setChecked(true);
        		break;
        	default:
        		break;
        }
	}
}
