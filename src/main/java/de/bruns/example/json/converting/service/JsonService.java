package de.bruns.example.json.converting.service;

import java.util.List;

import de.bruns.example.json.converting.data.PoiData;

public interface JsonService {

   public String asGeoJson(List<PoiData> pois) throws Exception;

   public List<PoiData> asPois(String geoJson) throws Exception;

}