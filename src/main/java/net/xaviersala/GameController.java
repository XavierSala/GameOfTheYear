package net.xaviersala;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.text.BadLocationException;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import net.xaviersala.model.Tauler;

public class GameController {
  private static final String ERROR_NO_HE_POGUT_GRAVAR_EL_RECORD = "No he pogut gravar el rècord";
  private static final String ERROR_CASELLA_INEXISTENT = "Casella inexistent";

  private static final String TEXT_HAS_FET = "Has fet ";
  private static final String TEXT_PUNTS = " punts";
  private static final String TEXT_REFER = "Refer";
  private static final String TEXT_COMENCAR = "Començar";


  private static final String INTERROGANT = "?";
  private static final String FITXER_DE_RECORDS = "record.txt";

  @FXML
  private Button a1;
  @FXML
  private Button a2;
  @FXML
  private Button a3;
  @FXML
  private Button a4;
  @FXML
  private Button a5;
  @FXML
  private Button a6;
  @FXML
  private Button a7;
  @FXML
  private Button a8;
  @FXML
  private Button a9;
  @FXML
  private Button a10;
  @FXML
  private Button a11;
  @FXML
  private Button a12;
  @FXML
  private Button a13;
  @FXML
  private Button a14;
  @FXML
  private Button a15;
  @FXML
  private Button a16;
  @FXML
  private Label record;
  @FXML
  private Label punts;
  @FXML
  private Button sortir;
  @FXML
  private Button comensa;

  private Tauler tauler;
  private int numPunts = 0;
  private int numRecord;
  File fileRecord;

  public void initialize() {

    fileRecord = new File(FITXER_DE_RECORDS);

    try (FileInputStream in = new FileInputStream(fileRecord)) {
      numRecord = in.read();
    } catch (IOException e) {
      // No hauria de passar mai ...
      numRecord = 0;
    }

    tauler = new Tauler();
    preparaPartida();
  }

  /**
   * Prepara la pantalla per poder començar una partida.
   */
  private void preparaPartida() {
    record.setText(numRecord + TEXT_PUNTS);
    numPunts = 0;
    punts.setText(String.valueOf(numPunts));
    comensa.setText(TEXT_COMENCAR);
    generarPantalla();
  }

  @FXML
  public void clic(ActionEvent event) {

    if (comensa.getText().equals(TEXT_COMENCAR)) {
      comensa.setText(TEXT_REFER);
    }

    Button boto = (Button) event.getSource();
    int clicat = Integer.parseInt(boto.getId().replaceAll("a", ""));
    try {

      String resultat = tauler.get(clicat);
      boto.setText(resultat);
      boto.setDisable(true);

      switch (resultat) {
      case Tauler.RESULTAT_DOBLE:
        numPunts = numPunts * 2;
        break;
      case Tauler.RESULTAT_MORT:
        Alert acabat = new Alert(AlertType.INFORMATION, TEXT_HAS_FET + numPunts + TEXT_PUNTS);
        acabat.showAndWait();
        if (numPunts > numRecord) {
          numRecord = numPunts;
          desaElRecord();
        }
        preparaPartida();
        break;
      case Tauler.RESULTAT_NORMAL:
        numPunts = numPunts + 1;
        break;
      }
      punts.setText(String.valueOf(numPunts));

    } catch (BadLocationException e) {
      Alert alerta = new Alert(AlertType.ERROR, ERROR_CASELLA_INEXISTENT);
      alerta.showAndWait();
    }

  }

  private void desaElRecord() {
    try (FileOutputStream out = new FileOutputStream(fileRecord)) {
      out.write(numRecord);
    } catch (IOException e ) {
      Alert problemes = new Alert(AlertType.ERROR, ERROR_NO_HE_POGUT_GRAVAR_EL_RECORD);
      problemes.showAndWait();
    }
  }

  @FXML
  public void aComensarOReset(ActionEvent event) {
    if (comensa.getText().equals(TEXT_COMENCAR)) {
      punts.setText(Tauler.RESULTAT_NORMAL);
      numPunts = 0;
      comensa.setText(TEXT_REFER);
    }
    generarPantalla();
  }

  @FXML
  public void aSortir(ActionEvent event) {
    Platform.exit();
  }

  /**
   * Reinicia la pantalla.
   */
  public void generarPantalla() {
    tauler.generarPantalla();
    resetBoto(a1);
    resetBoto(a2);
    resetBoto(a3);
    resetBoto(a4);
    resetBoto(a5);
    resetBoto(a6);
    resetBoto(a7);
    resetBoto(a8);
    resetBoto(a9);
    resetBoto(a10);
    resetBoto(a11);
    resetBoto(a12);
    resetBoto(a13);
    resetBoto(a14);
    resetBoto(a15);
    resetBoto(a16);
  }

  /**
   * Reinicia un botó.
   *  - Li posa l'interrogant
   *  - L'activa
   *
   * @param x Botó a reiniciar
   */
  private void  resetBoto(Button x) {
    x.setText(INTERROGANT);
    x.setDisable(false);
  }
}
