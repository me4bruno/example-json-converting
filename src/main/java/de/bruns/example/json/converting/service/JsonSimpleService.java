package de.bruns.example.json.converting.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import de.bruns.example.json.converting.data.PoiData;
import de.bruns.example.json.converting.data.PoiTypeEnum;

public class JsonSimpleService implements JsonService {

   @Override
   public String asGeoJson(List<PoiData> pois) throws IOException {
		List<Map<String, Object>> featuresList = new ArrayList<Map<String, Object>>();
		for (PoiData poi : pois) {
			List<Double> coordinatesList = new ArrayList<Double>();
			coordinatesList.add(poi.getLongitude());
			coordinatesList.add(poi.getLatitude());

			Map<String, Object> geometryMap = new LinkedHashMap<String, Object>();
			geometryMap.put("type", "Point");
			geometryMap.put("coordinates", coordinatesList);

			Map<String, Object> propertiesMap = new LinkedHashMap<String, Object>();
			propertiesMap.put("name", poi.getName());
			propertiesMap.put("image", poi.getType().getImageFile());

			Map<String, Object> featureMap = new LinkedHashMap<String, Object>();
			featureMap.put("type", "Feature");
			featureMap.put("id", poi.getId());
			featureMap.put("properties", propertiesMap);
			featureMap.put("geometry", geometryMap);
			featuresList.add(featureMap);
		}

		Map<String, Object> featureCollectionMap = new LinkedHashMap<String, Object>();
		featureCollectionMap.put("type", "FeatureCollection");
		featureCollectionMap.put("features", featuresList);
		return JSONValue.toJSONString(featureCollectionMap);
	}

   @Override
   public List<PoiData> asPois(String geoJson) throws IOException {
      List<PoiData> pois = new ArrayList<PoiData>();

      JSONObject jsonRoot=(JSONObject)JSONValue.parse(geoJson);
      JSONArray features = (JSONArray) jsonRoot.get("features");
      for (int i = 0; i < features.size(); i++) {
         JSONObject feature = (JSONObject) features.get(i);
         long id = (Long) feature.get("id");

         JSONObject properties = (JSONObject) feature.get("properties");
         String name = (String) properties.get("name");
         PoiTypeEnum type = PoiTypeEnum.ofImage((String) properties.get("image"));

         JSONObject geometry = (JSONObject) feature.get("geometry");
         JSONArray coordinates = (JSONArray) geometry.get("coordinates");
         double longitude = (Double) coordinates.get(0);
         double latitude = (Double) coordinates.get(1);

         PoiData poiData = new PoiData(id, name, type, longitude, latitude);
         pois.add(poiData);
      }
      return pois;
   }
}
