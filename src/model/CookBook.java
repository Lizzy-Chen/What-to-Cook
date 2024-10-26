package model;

public class CookBook {
	
	private int id;
	
	private String name;
	
	private String material;
	
	private String steps;
        
        public CookBook() {}

        /**
	 * @param id
	 * @param name
	 * @param material
	 * @param steps
	 */
	public CookBook(String name, String material, String steps) {
		this.name = name;
		this.material = material;
		this.steps = steps;
	}
	
        /**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the material
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * @param material the material to set
	 */
	public void setMaterial(String material) {
		this.material = material;
	}

	/**
	 * @return the steps
	 */
	public String getSteps() {
		return steps;
	}

	/**
	 * @param steps the steps to set
	 */
	public void setSteps(String steps) {
		this.steps = steps;
	}
	
	

}
