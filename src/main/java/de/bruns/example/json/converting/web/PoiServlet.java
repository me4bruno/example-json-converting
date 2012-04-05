package de.bruns.example.json.converting.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.bruns.example.json.converting.data.PoiData;
import de.bruns.example.json.converting.data.PoiTypeEnum;
import de.bruns.example.json.converting.service.JacksonJsonService;
import de.bruns.example.json.converting.service.JsonService;
import de.bruns.example.json.converting.service.JsonSimpleService;
import de.bruns.example.json.converting.service.OrgJsonService;
import de.bruns.example.json.converting.service.StringJsonService;

@WebServlet("/pois")
public class PoiServlet extends HttpServlet {

   private static final long serialVersionUID = 4581952804038449353L;

   private static final List<PoiData> POIS_LIST = Arrays.asList(new PoiData[] {
         new PoiData(1l, "", PoiTypeEnum.PLAYGROUND, 8.5864, 52.8988),
         new PoiData(2l, "Balkan-Restaurant", PoiTypeEnum.RESTAURANT, 8.5992, 52.9106),
         new PoiData(3l, "carpe diem", PoiTypeEnum.HAIRDRESSER, 8.5873, 52.9079) });

   private JsonService jsonService;

   @Override
   public void init() throws ServletException {
      jsonService = new StringJsonService();
      jsonService = new OrgJsonService();
      jsonService = new JsonSimpleService();
      jsonService = new JacksonJsonService();
   }

   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response) {
      response.setCharacterEncoding("UTF-8");
      response.setContentType("application/json");
      response.setStatus(HttpServletResponse.SC_OK);

      try {
         String geoJsonString = jsonService.asGeoJson(POIS_LIST);
         response.getWriter().write(geoJsonString);
      } catch (Exception e) {
         throw new RuntimeException(e); 
      }
   }
}