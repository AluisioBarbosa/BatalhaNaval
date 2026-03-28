package aluisio.batalhanaval.logic;

public class Campo {
	private boolean navio;
	private boolean campoAtingido;
	
	public Campo(){
		this.navio = false;
		this.campoAtingido = false;
	}

	public boolean isNavio() {
		return navio;
	}

	public void setNavio() {
		this.navio = true;
	}

	public boolean isCampoAtingido() {
		return campoAtingido;
	}

	public void setCampoAtingido() {
		this.campoAtingido = true;
	}
	
}
