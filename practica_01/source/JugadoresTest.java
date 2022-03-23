package practica_01;

import java.io.File;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class JugadoresTest {
	
	String directorioEntrada = System.getProperty("user.dir") + File.separator +
			   "src" + File.separator + 
			   "practica_01" + File.separator +
			   "NbaStats.txt";
	
	@Test
	public void testJugadores() {
		
		ArrayList<Player> nbaStats = new ArrayList<Player>();
		ArrayList<Player> solucion = new ArrayList<Player>();
		ArrayList<Player> solucionMejora = new ArrayList<Player>();
		ArrayList<Player> mejores10 = new ArrayList<Player>();
		Player jugador1 = new Player("LeBron James", "CLE", "SG", 1031);
		jugador1.addTeam("MIA");
		jugador1.addPosition("SF");
		jugador1.addPosition("PF");
		Player jugador2 = new Player("James Harden", "OKC", "SG", 994);
		jugador2.addTeam("HOU");
		jugador2.addPosition("PG");
		Player jugador3 = new Player("Stephen Curry", "GSW", "PG", 975);
		Player jugador4 = new Player("Karl-Anthony Towns", "MIN", "C", 965);
		Player jugador5 = new Player("Russell Westbrook", "OKC", "PG", 931);
		Player jugador6 = new Player("Anthony Davis", "NOH", "PF", 894);
		jugador6.addTeam("NOP");
		jugador6.addPosition("C");
		Player jugador7 = new Player("Kevin Durant", "SEA", "SG", 864);
		jugador7.addTeam("OKC");
		jugador7.addTeam("GSW");
		jugador7.addPosition("SF");
		Player jugador8 = new Player("Damian Lillard", "POR", "PG", 825);
		Player jugador9 = new Player("DeMar DeRozan", "TOR", "SG", 820);
		Player jugador10 = new Player("Wilt Chamberlain*", "PHW", "C", 785);
		jugador10.addTeam("SFW");
		jugador10.addTeam("TOT");
		jugador10.addTeam("PHI");
		jugador10.addTeam("LAL");
		
		mejores10.add(jugador1);
		mejores10.add(jugador2);
		mejores10.add(jugador3);
		mejores10.add(jugador4);
		mejores10.add(jugador5);
		mejores10.add(jugador6);
		mejores10.add(jugador7);
		mejores10.add(jugador8);
		mejores10.add(jugador9);
		mejores10.add(jugador10);
		
		Jugadores.cargarArchivo(directorioEntrada, nbaStats);
		solucion = Jugadores.mergeSort(nbaStats);
		solucionMejora = Jugadores.mergeSort2(nbaStats);
		
		// Comparar la primera solucion con los resultados esperados
		for(int i = 0; i < 10; i++) {
			Assert.assertEquals(mejores10.get(i), solucion.get(i));
		}
		
		// Comparar la solucion mejorada con los resultados esperados
		for(int i = 0; i < 10; i++) {
			Assert.assertEquals(mejores10.get(i), solucionMejora.get(i));
		}
		
	}

}
