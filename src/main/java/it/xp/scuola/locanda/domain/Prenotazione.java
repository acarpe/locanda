package it.xp.scuola.locanda.domain;

public class Prenotazione
{
  private final DataPrenotazione data;
  private final NumeroPersone numeroPersone;

  public Prenotazione(DataPrenotazione data, NumeroPersone numeroPersone)
  {
    this.data = data;
    this.numeroPersone = numeroPersone;
  }

  public boolean isFor(DataPrenotazione data)
  {
    return data.equals(this.data);
  }


}
