package concessionaria_carros;
import java.util.ArrayList;

public class Concessionaria {
	private ArrayList<Carro> carros = new ArrayList<>();
	
	public void adicionarCarro (Carro carro) { 
		carros.add(carro);
	}
	
	public int quantidadedeCarros() {
		return carros.size();
	}
	
	

}
