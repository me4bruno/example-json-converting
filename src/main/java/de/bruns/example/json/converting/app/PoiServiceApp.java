package de.bruns.example.json.converting.app;

import java.util.Arrays;
import java.util.List;

import de.bruns.example.json.converting.data.PoiData;
import de.bruns.example.json.converting.data.PoiTypeEnum;
import de.bruns.example.json.converting.service.JsonService;
import de.bruns.example.json.converting.service.StringJsonService;

public class PoiServiceApp {

   private static final List<PoiData> POIS_LIST = Arrays.asList(new PoiData[] {
         new PoiData(1l, "", PoiTypeEnum.PLAYGROUND, 8.5864, 52.8988),
         new PoiData(2l, "Balkan-Restaurant", PoiTypeEnum.RESTAURANT, 8.5992, 52.9106),
         new PoiData(3l, "carpe diem", PoiTypeEnum.HAIRDRESSER, 8.5873, 52.9079) });

   public static void main(String[] args) throws Exception {
      JsonService jsonService = new StringJsonService();
      String poisAsGeoJson = jsonService.asGeoJson(POIS_LIST);
      System.out.println(poisAsGeoJson);
   }
}
