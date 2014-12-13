package artquiz.activities;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import artquiz.context.PictureContext;
import artquiz.models.Picture;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.artquiz.R;

public class GameScreenActivity extends Activity implements OnClickListener {

	
	
	protected Picture rightAnswer;
	
	protected int rightAnswerPosition;
	
	protected int rightChoosesCounter;
	
	protected int wrongChoosesCounter;
	
	protected int picturesToPlay;
	
	protected int amountOfPictures;
	
	protected int checkedAnswerPosition;
	
	protected int currentRandom;
	
	protected List<Integer> playedPictures;
	
	protected List<Button> buttons;
	
	protected boolean frozen = false;
	
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_gamescreen);
	    
	    playedPictures = new ArrayList<Integer>();
	    buttons = new ArrayList<Button>();
	    
	    buttons.add((Button) findViewById(R.id.button5));
	    buttons.add((Button) findViewById(R.id.button6));
	    buttons.add((Button) findViewById(R.id.button7));
	    buttons.add((Button) findViewById(R.id.button8));
	    for (Button b : buttons)
	    {
	        b.setOnClickListener(this);
	    }
	    ImageButton hint = (ImageButton) findViewById(R.id.hint50);
	    hint.setOnClickListener(this);
	    
	    ImageView imageView = (ImageView) findViewById(R.id.imageView1);
	    imageView.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if (!frozen)
				{
					frozen = true;
					Toast.makeText(getApplicationContext(), rightAnswer.getTitle(), 1000).show();
					Handler handler = new Handler();
		            handler.postDelayed(new Runnable() {
			            public void run()
			            {
			                frozen = false;
			            }
		            }, 2600);
				}
			}	
	    });
	    
	    picturesToPlay = SettingsActivity.picturesToPlay;
	    
	    PictureContext context = new PictureContext(this);
		amountOfPictures = context.countPictures();
	    
	    getRound();
	  }
	
	protected void getRound()
	{
		if (rightChoosesCounter + wrongChoosesCounter == picturesToPlay)
        {
			frozen = true;
            int k = rightChoosesCounter + wrongChoosesCounter;
            Toast.makeText(this, "Ваш результат: " + rightChoosesCounter + " из " + k, 1000).show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
	            public void run()
	            {
	                CloseActivity();
	            }
            }, 1500);
            return;
        }
		for (Button b : buttons)
	    {
	        b.setVisibility(View.VISIBLE);
	    }
		
		PictureContext context = new PictureContext(this);
		do
		{
			Random rand = new Random();
			currentRandom = rand.nextInt(amountOfPictures);
		}
		while (playedPictures.contains(currentRandom));
		
		rightAnswer = context.getPictureById(currentRandom);
		
        List<String> wrongAnswers = context.getWrongAnswers(rightAnswer.getId());
		
		Random random = new Random();
		rightAnswerPosition = random.nextInt(4);
		
		buttons.get(rightAnswerPosition).setText(rightAnswer.getAuthor());
		
        for (int i = 1; i < 4; i++)
        {
            buttons.get((i + rightAnswerPosition) % 4).setText(wrongAnswers.get(i-1));
        }
		
        playedPictures.add(rightAnswer.getId());
        
        ImageView imageView = (ImageView) findViewById(R.id.imageView1);
       
        Drawable picture = getResources().getDrawable(R.drawable.novojamoskva);
        int resourceID = getResources().getIdentifier(rightAnswer.getFile(), "drawable", getPackageName());
        picture = getResources().getDrawable(resourceID);
        
        imageView.setImageDrawable(picture);
	}
	
	protected void checkAnswer()
	{
		int i;
		frozen = true;
		TextView calc = (TextView) findViewById(R.id.calc);
		if (checkedAnswerPosition == rightAnswerPosition)
		{
			rightChoosesCounter++;
			i = 500;
			((Button)buttons.get(rightAnswerPosition)).getBackground().setColorFilter(new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP));
		}
		else
		{
			wrongChoosesCounter++;
			
			i = 1000;
			((Button)buttons.get(rightAnswerPosition)).getBackground().setColorFilter(new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.SRC_ATOP));
			((Button)buttons.get(checkedAnswerPosition)).getBackground().setColorFilter(new PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP));
		}
		calc.setText(rightChoosesCounter + "/" + (rightChoosesCounter + wrongChoosesCounter));
		Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
        public void run()
        {
            Drawable drawable2 = ((Button)buttons.get(rightAnswerPosition)).getBackground();
            Drawable drawable3 = ((Button)buttons.get(checkedAnswerPosition)).getBackground();
            drawable2.setColorFilter(new PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.DST));
            drawable3.setColorFilter(new PorterDuffColorFilter(Color.GRAY, PorterDuff.Mode.DST));
            ((Button)buttons.get(rightAnswerPosition)).invalidate();
            ((Button)buttons.get(checkedAnswerPosition)).invalidate();
            frozen = false;
            try
            {
                getRound();
            }
            catch (Exception ex)
            {
            	Toast.makeText(getApplicationContext(), "Oops!", 500).show();
            	getRound();
            }
        }
        }, i);
	}
	
	private void getHint50and50()
	{
		boolean done = false;
		int firstHidden = -1;
		while(!done)
		{
			Random rand = new Random();
			int random = rand.nextInt(3);
			if (random != rightAnswerPosition && random != firstHidden)
			{
				buttons.get(random).setVisibility(View.INVISIBLE);
				if (firstHidden == -1)
					firstHidden = random;
				else
					done = true;
			}
		}
	}
	
	private void CloseActivity()
	{
		this.finish();
	}
	
	public void onClick(View v) {
		if (!frozen)
        switch (v.getId()) {
        case R.id.button5:
            checkedAnswerPosition = 0;
        	checkAnswer();
          break;
        case R.id.button6:
            checkedAnswerPosition = 1;
        	checkAnswer();
          break;
        case R.id.button7:
            checkedAnswerPosition = 2;
        	checkAnswer();
          break;
        case R.id.button8:
            checkedAnswerPosition = 3;
        	checkAnswer();
          break;
        case R.id.hint50:
        	this.getHint50and50();
        	((ImageButton) findViewById(R.id.hint50)).setVisibility(View.INVISIBLE);
        	break;
        default:
          break;
        }
      }
}
