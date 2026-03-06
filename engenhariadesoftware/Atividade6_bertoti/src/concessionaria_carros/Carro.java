package concessionaria_carros;

public class Carro {
	private String modelo;
	private String fabricante;
	private int ano;
	
	public Carro (String modelo, String fabricante, int ano) {
		this.modelo = modelo;
		this.fabricante = fabricante;
		this.ano = ano;
	}
	
	public String getModelo () {
		return modelo;
	}
	
	public String getFabricante () {
		return fabricante;
	}
	
	public int getAno () {
		return ano;
	}

}
