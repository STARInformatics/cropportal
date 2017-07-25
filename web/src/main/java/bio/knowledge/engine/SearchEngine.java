package bio.knowledge.engine;

import java.util.List;

import bio.knowledge.models.Crop;
import bio.knowledge.models.Trait;

public class SearchEngine {
	public static int TIMEOUT_SECONDS = 10;
	
	private static String CROPONTOLOTY_SEARCH = "http://www.cropontology.org/search?q=";
	
	public static Trait[] findTraits(Crop crop, String keywords) {
		RequestEngine<Trait[]> engine = new RequestEngine<Trait[]>(Trait[].class);
		return engine.request(CROPONTOLOTY_SEARCH + crop.toString() + " " + keywords, TIMEOUT_SECONDS);
	}

}
