package net.xaviersala.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.text.BadLocationException;

public class Tauler {

  private static final String ERROR_POSICIO_INEXISTENT = "Posicio inexistent";

  public static final String RESULTAT_DOBLE = "W";
  public static final String RESULTAT_MORT = "X";
  public static final String RESULTAT_NORMAL = "O";

  private static final int NUMERO_DE_MORTS_A_CREAR = 2;
  private static final int MIDA_TAULER = 16;

  List<Casella> caselles;
  Random aleatori = new Random();

  public Tauler() {
    caselles = new ArrayList<>(MIDA_TAULER);
  }

  public void generarPantalla() {
      caselles.clear();
      for(int i=0; i<MIDA_TAULER; i++) {
        caselles.add(new Casella(RESULTAT_NORMAL));
      }

      for(int i=0; i<NUMERO_DE_MORTS_A_CREAR; i++) {
        int valor = aleatori.nextInt(MIDA_TAULER);
        caselles.get(valor).setContingut(RESULTAT_MORT);
      }

      int  valor = aleatori.nextInt(MIDA_TAULER);
      caselles.get(valor).setContingut(RESULTAT_DOBLE);

  }

  public String get(int posicio) throws BadLocationException {
    if (posicio > MIDA_TAULER || posicio < 0) {
      throw new BadLocationException(ERROR_POSICIO_INEXISTENT, posicio);
    }
    return caselles.get(posicio-1).getContingut();
  }

  @Override
  public String toString() {
    return caselles.toString();
  }
}
