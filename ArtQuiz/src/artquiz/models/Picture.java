package artquiz.models;

public class Picture {
	
    private int Id;
    
    private int Year;
    
    private String Title;
    
    private String Author;
    
    private String File;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getYear() {
		return Year;
	}

	public void setYear(int year) {
		Year = year;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getAuthor() {
		return Author;
	}

	public void setAuthor(String author) {
		Author = author;
	}

	public String getFile() {
		return File;
	}

	public void setFile(String file) {
		File = file;
	}
    
}