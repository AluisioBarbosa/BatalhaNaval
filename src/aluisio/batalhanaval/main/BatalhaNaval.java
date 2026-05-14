package aluisio.batalhanaval.main;

import aluisio.batalhanaval.logic.DIFICULTY;
import aluisio.batalhanaval.logic.Game;

public class BatalhaNaval {

	public static void main(String[] args) {
		Game batalha = new Game(7, DIFICULTY.HARD);
		batalha.start();
	}

}
