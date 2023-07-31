package integration;

public class AnimaleDto {
	private Integer id;
	private Integer peso;
	private String specie;

	public String getSpecie() {
		return specie;
	}

	public Integer getPeso() {
		return peso;
	}

	public Integer getId() {
		return id;
	}

	public void setSpecie(String specie) {
		this.specie = specie;
	}

	public void setPeso(Integer i) {
		this.peso = i;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
