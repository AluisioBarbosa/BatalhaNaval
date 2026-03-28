package aluisio.batalhanaval.logic;

public class Tabuleiro {
	private Campo campo[][];
	
	public Tabuleiro() {
		campo = new Campo[7][7];
	}
	
	public boolean updateCampo(Campo campo) {
		if(campo.isCampoAtingido() == true) {
			System.out.println("Campo já foi destruido");
			return false;
		}
		else if(campo.isNavio() == true) {
			// logica de destruicao de navio
		}
		return true;
	}
	
	
}
