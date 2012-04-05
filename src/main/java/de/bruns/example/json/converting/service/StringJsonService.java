package de.bruns.example.json.converting.service;

import java.io.IOException;
import java.util.List;

import de.bruns.example.json.converting.data.PoiData;

public class StringJsonService implements JsonService {

	private static final String LINE_SEPERATOR = "\n";

	public String asGeoJson(List<PoiData> pois) throws IOException {
	   StringBuilder geoJsonString = new StringBuilder();
	   geoJsonString.append("{ \"type\": \"FeatureCollection\", \"features\": [" + LINE_SEPERATOR);
		for (int i = 0; i < pois.size(); i++) {
		   PoiData poi = pois.get(i);
			if (i != 0) {
			   geoJsonString.append("," + LINE_SEPERATOR);
			}
			String poiGeoJson = 
			      "{ \"type\": \"Feature\"," +
			            " \"id\": " + poi.getId() + "," + //
			            " \"properties\": {" + //
			               " \"name\": \"" + poi.getName() + "\"," + //
			               " \"image\": \"" + poi.getType().getImageFile() + "\"}," + //
			            " \"geometry\": {" + //
			               " \"type\": \"Point\"," + //
			               " \"coordinates\": [" + poi.getLongitude() + ", " + poi.getLatitude() + //
			       "] } }";
         geoJsonString.append(poiGeoJson);
		}
		geoJsonString.append(LINE_SEPERATOR + "] }");
		return geoJsonString.toString(); 
	}

   @Override
   public List<PoiData> asPois(String geoJson) throws IOException {
      throw new RuntimeException("not implemented");
   }
	
}
