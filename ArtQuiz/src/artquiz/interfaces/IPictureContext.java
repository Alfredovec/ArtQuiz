package artquiz.interfaces;

import java.util.List;

import artquiz.models.Picture;


public interface IPictureContext {
	
	public int countPictures();
	
	public List<String> getWrongAnswers(int rightAnswerId);
	
	public Picture getPictureById(int id);
}
