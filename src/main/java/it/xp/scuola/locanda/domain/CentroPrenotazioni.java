package it.xp.scuola.locanda.domain;

import java.util.ArrayList;
import java.util.List;

public class CentroPrenotazioni
{

  private final List<Prenotazione> prenotazioni;

  public CentroPrenotazioni(List<Prenotazione> prenotazioni)
  {
    this.prenotazioni = prenotazioni;
  }

  public List<Prenotazione> cercaPrenotazioniPer(DataPrenotazione data)
  {
    ArrayList<Prenotazione> result = new ArrayList<>();
    for (Prenotazione prenotazione : prenotazioni)
    {
      if(prenotazione.isFor(data)) {
        result.add(prenotazione);
      }
    }
    return result;
  }

  public void inserisciPrenotazione(Prenotazione prenotazione)
  {
      prenotazioni.add(prenotazione);
  }
}
