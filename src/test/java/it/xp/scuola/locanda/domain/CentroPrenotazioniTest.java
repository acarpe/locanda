package it.xp.scuola.locanda.domain;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CentroPrenotazioniTest
{

  private CentroPrenotazioni centroPrenotazioni;
  private ArrayList<Prenotazione> prenotazioni;
  private DataPrenotazione unaData;
  private DataPrenotazione unAltraData;

  @Before
  public void setUp() throws Exception
  {
    unaData = new DataPrenotazione("01/01/2018");
    unAltraData = new DataPrenotazione("01/01/2017");
    prenotazioni = new ArrayList<Prenotazione>();
    centroPrenotazioni = new CentroPrenotazioni(prenotazioni);
  }

  @Test
  public void nessunaPrenotazioneTrovataTest() throws Exception {
    expectPrenotazioniTrovateSizeAre(0);
  }

  @Test
  public void unaPrenotazioneTrovataTest() throws Exception {
    prenotazioni.add(new Prenotazione(unaData, new NumeroPersone(1)));
    prenotazioni.add(new Prenotazione(unAltraData, new NumeroPersone(1)));
    int expectedSize = 1;
    expectPrenotazioniTrovateSizeAre(expectedSize);
  }

  @Test
  public void inseriscoConSuccessoUnaPrenotazioneTest() throws Exception {
    expectPrenotazioniTrovateSizeAre(0);
    centroPrenotazioni.inserisciPrenotazione(new Prenotazione(unaData, new NumeroPersone(1)));
    expectPrenotazioniTrovateSizeAre(1);
  }

  @Test
  public void nonPossoInserirePrenotazioniQuandoLaDisponibilitaEEsauritaTest() throws Exception {
    prenotazioni.add(new Prenotazione(unaData, new NumeroPersone(60)));
    centroPrenotazioni.inserisciPrenotazione(new Prenotazione(unaData, new NumeroPersone(1)));
    expectPrenotazioniTrovateSizeAre(1);
  }

  private void expectPrenotazioniTrovateSizeAre(int expectedSize)
  {
    List<Prenotazione> prenotazioniTrovate = centroPrenotazioni.cercaPrenotazioniPer(unaData);
    assertThat(prenotazioniTrovate.size(),is(expectedSize));
  }

}
