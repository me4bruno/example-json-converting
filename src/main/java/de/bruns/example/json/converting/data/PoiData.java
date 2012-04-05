package de.bruns.example.json.converting.data;


public class PoiData {

	private long id;
	private String name;
	private PoiTypeEnum type;
	private double latitude;
	private double longitude;
	
   public PoiData(long id, String name, PoiTypeEnum type, double longitude, double latitude) {
      this.id = id;
      this.name = name;
      this.type = type;
      this.latitude = latitude;
      this.longitude = longitude;
   }

   public long getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public PoiTypeEnum getType() {
      return type;
   }

   public double getLatitude() {
      return latitude;
   }

   public double getLongitude() {
      return longitude;
   }
}
