package de.bruns.example.json.converting.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import de.bruns.example.json.converting.data.PoiData;
import de.bruns.example.json.converting.data.PoiTypeEnum;

public class OrgJsonService implements JsonService {

   @Override
   public String asGeoJson(List<PoiData> pois) throws Exception {
      JSONArray featuresJson = new JSONArray();
      for (PoiData poi : pois) {
         JSONArray coordinatesJson = new JSONArray();
         coordinatesJson.put(poi.getLongitude());
         coordinatesJson.put(poi.getLatitude());

         JSONObject geometryJson = new JSONObject();
         geometryJson.put("type", "Point");
         geometryJson.put("coordinates", coordinatesJson);

         JSONObject propertiesJson = new JSONObject();
         propertiesJson.put("name", poi.getName());
         propertiesJson.put("image", poi.getType().getImageFile());

         JSONObject featureJson = new JSONObject();
         featureJson.put("type", "Feature");
         featureJson.put("id", poi.getId());
         featureJson.put("properties", propertiesJson);
         featureJson.put("geometry", geometryJson);
         featuresJson.put(featureJson);
      }
      JSONObject featureCollectionJson = new JSONObject();
      featureCollectionJson.put("type", "FeatureCollection");
      featureCollectionJson.put("features", featuresJson);
      return featureCollectionJson.toString();
   }

   @Override
   public List<PoiData> asPois(String geoJson) throws Exception {
      List<PoiData> pois = new ArrayList<PoiData>();
      JSONObject jsonRoot = new JSONObject(geoJson);
      JSONArray features = jsonRoot.getJSONArray("features");
      for (int i = 0; i < features.length(); i++) {
         JSONObject feature = (JSONObject) features.get(i);
         long id = feature.getLong("id");

         JSONObject properties = (JSONObject) feature.get("properties");
         String name = properties.getString("name");
         PoiTypeEnum type = PoiTypeEnum.ofImage(properties.getString("image"));

         JSONObject geometry = (JSONObject) feature.get("geometry");
         JSONArray coordinates = (JSONArray) geometry.get("coordinates");
         double longitude = coordinates.getDouble(0);
         double latitude = coordinates.getDouble(1);

         PoiData poiData = new PoiData(id, name, type, longitude, latitude);
         pois.add(poiData);
      }
      return pois;
   }
}
