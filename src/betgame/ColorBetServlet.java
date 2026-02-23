package betgame;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet("/bet")
public class ColorBetServlet extends HttpServlet {
    private final Random random = new Random();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String betParam = req.getParameter("bet"); // "0" or "1"
        if (!"0".equals(betParam) && !"1".equals(betParam)) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("Invalid bet");
            return;
        }

        int playerBet = Integer.parseInt(betParam);
        int outcome = random.nextInt(2); // 0 or 1

        boolean won = (playerBet == outcome);
        String color = outcome == 0 ? "white" : "red";

        resp.setContentType("application/json");
        resp.getWriter().write(String.format(
                "{\"outcome\":%d,\"color\":\"%s\",\"win\":%b}",
                outcome, color, won));
    }
}
