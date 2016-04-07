package it.xp.scuola.locanda.domain;

public class DataPrenotazione
{
  private final String data;

  public DataPrenotazione(String data)
  {
    this.data = data;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DataPrenotazione that = (DataPrenotazione) o;

    return data != null ? data.equals(that.data) : that.data == null;

  }

  @Override
  public int hashCode()
  {
    return data != null ? data.hashCode() : 0;
  }
}
