package it.xp.scuola.locanda.web.home;

import it.xp.scuola.toolkit.web.TemplateView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LocandaServlet extends HttpServlet
{
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
  {
    String html = new TemplateView("home.ftl").toHtml();
    resp.getWriter().write(html);
    resp.setStatus(200);
  }
}
