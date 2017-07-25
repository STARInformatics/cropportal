package bio.knowledge.models;

public enum Crop {
	LENTIL("Lentil"),
	SUNFLOWER("Sunflower");
	
	private String name;
	
	Crop(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name();
	}
}
