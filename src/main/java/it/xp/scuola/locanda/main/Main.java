package it.xp.scuola.locanda.main;

import it.xp.scuola.locanda.web.home.LocandaServlet;
import it.xp.scuola.toolkit.web.ReusableJettyApp;

public class Main {
  public static void main(String[] args) {
    ReusableJettyApp app = new ReusableJettyApp(new LocandaServlet());
    app.start(8080, "src/main/webapp");
  }
}
