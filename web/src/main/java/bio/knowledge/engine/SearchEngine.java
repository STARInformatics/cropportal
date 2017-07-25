package bio.knowledge.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bio.knowledge.models.Crop;
import bio.knowledge.models.Trait;

import java.lang.reflect.Type;

public class SearchEngine {
	public static int TIMEOUT_SECONDS = 10;
	
	private static String TRAIT_SEARCH = "http://www.cropontology.org/search?q=";
	private static String GET_ATTRIBUTES = "http://www.cropontology.org/get-attributes/";
	
	public static Trait[] findTraits(Crop crop, String keywords) {
		RequestEngine<Trait[]> engine = new RequestEngine<Trait[]>(Trait[].class);
		return engine.request(TRAIT_SEARCH + crop.toString() + " " + keywords, TIMEOUT_SECONDS);
	}
	
	public static Map<String, String> findTraitDetails(String traitId) {
		Type type = new ArrayList<HashMap<String, String>>().getClass();
		RequestEngine<List<Map<String, String>>> engine =
				new RequestEngine<List<Map<String, String>>>(type);
		
		List<Map<String, String>> results = engine.request(GET_ATTRIBUTES + traitId, TIMEOUT_SECONDS);
		
		Map<String, String> map = new HashMap<String, String>();
		
		for (Map<String, String> m : results) {
			map.put(m.get("key"), m.get("value"));
		}
		
		return map;
	}
}
