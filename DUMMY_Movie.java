import java.util.Iterator;
import java.util.List;

/**
 * THIS IS A DUMMY IMPLEMENTATION of the movie class - it contains only bare functionality to allow
 * construction with title, genre, and rating, 0 or empty string for others
 * DOES NOT CONTAIN VALID COMPARETO METHOD
 * @author Kali
 *
 */
public class DUMMY_Movie implements MovieInterface, Iterable<DUMMY_Movie> {
	String title;
	Integer year;
	List<String> Genres;
	String Director;
	String Description;
	Float avgVote;
	
	
	public DUMMY_Movie(String title, List<String> Genres, Float avgVote) {
		this.title = title;
		this.Genres = Genres;
		this.avgVote = avgVote;
		this.year = 0;
		this.Director = "";
		this.Description = "";
	}
	
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return this.title;
	}

	@Override
	public Integer getYear() {
		// TODO Auto-generated method stub
		return this.year;
	}

	@Override
	public List<String> getGenres() {
		// TODO Auto-generated method stub
		return this.Genres;
	}

	@Override
	public String getDirector() {
		// TODO Auto-generated method stub
		return this.Director;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return this.Description;
	}

	@Override
	public Float getAvgVote() {
		// TODO Auto-generated method stub
		return this.avgVote;
	}

	@Override
	public int compareTo(MovieInterface otherMovie) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<DUMMY_Movie> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
