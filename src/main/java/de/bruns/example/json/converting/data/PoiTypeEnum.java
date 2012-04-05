package de.bruns.example.json.converting.data;

public enum PoiTypeEnum {

	PLAYGROUND("Spielplatz"), //
	RESTAURANT("Restaurant"), //
	HAIRDRESSER("Frisoer");
	
	public String getLabel() {
		return label;
	}

	public String getImageFile() {
	   return name().toLowerCase() + ".png";
	}

	private final String label;

	private PoiTypeEnum(String label) {
		this.label = label;
	}

   public static PoiTypeEnum ofImage(String image) {
      return valueOf(image.replace(".png", "").toUpperCase());
   }
}
