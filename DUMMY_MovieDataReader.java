import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
//ArrayList shouldn't be imported for final project
import java.util.ArrayList;
import java.util.zip.DataFormatException;

public class DUMMY_MovieDataReader implements MovieDataReaderInterface {

	@Override
	public List<MovieInterface> readDataSet(Reader inputFileReader)
			throws FileNotFoundException, IOException, DataFormatException {
		
		//make list to put movies in
		List<MovieInterface> list = new ArrayList<MovieInterface>();
		
		//movie one
		List<String> genresOne = new ArrayList<String>(); genresOne.add("Action"); genresOne.add("Romance");
		float ratingOne = 7.9f;
		DUMMY_Movie movieOne = new DUMMY_Movie("Movie Film Number One", genresOne, ratingOne);
		list.add(movieOne);
		
		//movie two
		List<String> genresTwo = new ArrayList<String>(); genresTwo.add("Comedy"); genresTwo.add("Horror"); genresTwo.add("Romance");
		float ratingTwo = 7.9f;
		DUMMY_Movie movieTwo = new DUMMY_Movie("Movie Film Number Two", genresTwo, ratingTwo);
		list.add(movieTwo);
		
		//movie three
		List<String> genresThree = new ArrayList<String>(); genresThree.add("Science Fiction");
		float ratingThree = 7.9f;
		DUMMY_Movie movieThree = new DUMMY_Movie("Movie Film Number One", genresThree, ratingThree);
		list.add(movieThree);
		
		return list;
	}

}
