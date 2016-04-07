package it.xp.scuola.locanda.domain;

public class NumeroPersone
{
  private final int numero;

  public NumeroPersone(int numero)
  {
    this.numero = numero;
  }

  public int toInt()
  {
    return numero;
  }
}
