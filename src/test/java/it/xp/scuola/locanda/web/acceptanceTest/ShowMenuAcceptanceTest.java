package it.xp.scuola.locanda.web.acceptanceTest;

import it.xp.scuola.locanda.util.TestWithALiveServer;
import org.junit.Test;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

public class ShowMenuAcceptanceTest extends TestWithALiveServer
{
  @Test
  public void helloLocanda() throws Exception {
    get("/");

    assertStatus(200);
    assertThat(responseBody(), containsString("La Locanda della Chiesa"));
  }

  @Test
  public void showsMenu() throws Exception {
    get("/");
    assertThat(responseBody(), containsString("Il nostro menu"));
    assertThat(responseBody(), containsString("Gli antipasti"));
    assertThat(responseBody(), containsString("I primi piatti"));
    assertThat(responseBody(), containsString("Dolci e dessert"));
  }

  @Test
  public void showsContacts() throws Exception {
    get("/");
    assertThat(responseBody(), containsString("Locanda della chiesa, Piazza della Vittoria 5"));
  }

}
