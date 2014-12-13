package artquiz.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.xmlpull.v1.XmlPullParser;

import com.artquiz.R;

import android.app.Activity;
import artquiz.interfaces.IPictureContext;
import artquiz.models.Picture;

public class PictureContext implements IPictureContext
{
	Activity act;
	
	public PictureContext(Activity act) 
	{
		this.act = act;
	}

	public Picture getPictureById(int id) 
	{
		Picture outPicture = new Picture();
		XmlPullParser xpp = act.getResources().getXml(R.xml.pictures);
		try 
		{
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) 
			{
		        switch (xpp.getEventType()) 
		        {
		        	case XmlPullParser.START_TAG:
		        		String text = xpp.getName();
		        		if (text.equals("picture"))
		        		{
		        			String a = xpp.getAttributeValue(0);
		        			if (Integer.parseInt(xpp.getAttributeValue(0)) == id)
		        			{
			        			while (!(xpp.getEventType() == XmlPullParser.END_TAG && xpp.getName().equals("picture")))
			        			{
			        				Boolean b = xpp.getEventType() == XmlPullParser.END_TAG;
			        				String s =  xpp.getName();
			        				String r;
			        				switch (xpp.getEventType())
			        				{
			        					case XmlPullParser.START_TAG:
			        						switch (xpp.getName())
			        						{
			        							case "name":
			        								xpp.next();
			        								if (xpp.getEventType() == XmlPullParser.TEXT)
			        								{
			        									outPicture.setTitle(xpp.getText());
			        								}
			        								break;
			        							case "year":
			        								xpp.next();
			        								if (xpp.getEventType() == XmlPullParser.TEXT)
			        								{
			        									outPicture.setYear(Integer.parseInt(xpp.getText()));
			        								}
			        								break;
			        							case "author":
			        								xpp.next();
			        								if (xpp.getEventType() == XmlPullParser.TEXT)
			        								{
			        									outPicture.setAuthor(xpp.getText());
			        								}
			        								break;
			        							case "file":
			        								xpp.next();
			        								if (xpp.getEventType() == XmlPullParser.TEXT)
			        								{
			        									outPicture.setFile(xpp.getText());
			        								}
			        								break;
			        						}
			        						
			        						break;
			        					}
				    		    xpp.next();
			        			}
			        			outPicture.setId(id);
			        			return outPicture;
		        			}
		        		}
		        		break;
		        	default:
		        		break;
		        }
		        xpp.next();
		      }
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public List<String> getWrongAnswers(int rightAnswerId) 
	{
		try 
		{
			String rightAuthor = this.getPictureById(rightAnswerId).getAuthor();
			List<Integer> Ids = new ArrayList<Integer>(3);
			int amount = this.countPictures();
			do
			{
				Ids.clear();
				for (int i = 0; i < 3; i++)
				{
					Random rand = new Random();
					int random = rand.nextInt(amount);
					Ids.add(random);
				}
			}
			while (Ids.contains(rightAnswerId));

			XmlPullParser xpp = act.getResources().getXml(R.xml.pictures);
			List<String> authors = new ArrayList<String>(3);
			
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) 
			{
		        switch (xpp.getEventType()) 
		        {
		        	case XmlPullParser.START_TAG:
		        		String text = xpp.getName();
		        		if (text.equals("picture"))
		        		{
		        			String a = xpp.getAttributeValue(0);
		        			if (Ids.contains(Integer.parseInt(xpp.getAttributeValue(0))))
		        			{
			        			while (!(xpp.getEventType() == XmlPullParser.END_TAG && xpp.getName().equals("picture")))
			        			{
			        				Boolean b = xpp.getEventType() == XmlPullParser.END_TAG;
			        				String s =  xpp.getName();
			        				String r;
			        				switch (xpp.getEventType())
			        				{
			        					case XmlPullParser.START_TAG:
			        						if (xpp.getName().equals("author"))
			        						{
		        								xpp.next();
		        								if (xpp.getEventType() == XmlPullParser.TEXT)
		        								{
		        									if (xpp.getText() != rightAuthor)
		        									{
			        									authors.add(xpp.getText());
		        									}
		        									else
		        									{
		        										String author;
		        										do
		        										{
			        										Random random = new Random();
			        										int rand = random.nextInt(this.countPictures());
			        										author = this.getPictureById(rand).getAuthor();
		        										}
		        										while (author == rightAuthor || authors.contains(author));
		        										authors.add(author);
		        									}
		        									if (authors.size() > 2) 
		        										return authors;
		        								}
			        						}
			        						break;
			        				}
				    		    xpp.next();
			        			}
		        			}
		        		}
		        		break;
		        	default:
		        		break;
		        }
		        xpp.next();
		      }
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}

	public int countPictures() 
	{
		int counter = 0;
		XmlPullParser xpp = act.getResources().getXml(R.xml.pictures);
		try 
		{
			while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) 
			{
				if (xpp.getEventType() == XmlPullParser.START_TAG && xpp.getName().equals("picture"))
				{
					counter++;
				}
				xpp.next();
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return counter;
	}
}
