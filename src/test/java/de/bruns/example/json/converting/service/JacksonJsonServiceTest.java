package de.bruns.example.json.converting.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.bruns.example.json.converting.data.PoiData;
import de.bruns.example.json.converting.data.PoiTypeEnum;

public class JacksonJsonServiceTest {

   private static final List<PoiData> POIS_LIST = Arrays.asList(new PoiData[] {
         new PoiData(1l, "", PoiTypeEnum.PLAYGROUND, 8.5864, 52.8988),
         new PoiData(2l, "Balkan-Restaurant", PoiTypeEnum.RESTAURANT, 8.5992, 52.9106),
         new PoiData(3l, "carpe diem", PoiTypeEnum.HAIRDRESSER, 8.5873, 52.9079) });

   private JsonService jsonService;

   @Before
   public void setup() {
      jsonService = new JacksonJsonService();
   }
   
   @Test
   public void testSerializeDeserialize() throws Exception {
      String asGeoJson = jsonService.asGeoJson(POIS_LIST);
      List<PoiData> pois = jsonService.asPois(asGeoJson);

      assertEquals(3, pois.size());

      assertEquals(1l, pois.get(0).getId());
      assertEquals("", pois.get(0).getName());
      assertEquals(PoiTypeEnum.PLAYGROUND, pois.get(0).getType());
      assertEquals(8.5864, pois.get(0).getLongitude(), 0.0001);
      assertEquals(52.8988, pois.get(0).getLatitude(), 0.0001);
      
      assertEquals(2l, pois.get(1).getId());
      assertEquals("Balkan-Restaurant", pois.get(1).getName());
      assertEquals(PoiTypeEnum.RESTAURANT, pois.get(1).getType());
      assertEquals(8.5992, pois.get(1).getLongitude(), 0.0001);
      assertEquals(52.9106, pois.get(1).getLatitude(), 0.0001);
      
      assertEquals(3l, pois.get(2).getId());
      assertEquals("carpe diem", pois.get(2).getName());
      assertEquals(PoiTypeEnum.HAIRDRESSER, pois.get(2).getType());
      assertEquals(8.5873, pois.get(2).getLongitude(), 0.0001);
      assertEquals(52.9079, pois.get(2).getLatitude(), 0.0001);
   }
}
