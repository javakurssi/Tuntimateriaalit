package tunti6;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import tunti6.apuluokat.Henkilo;

// Muista lisätä SQLLite-driver build.gradle:en ja "Refresh gradle project" (eclipse)

public class HenkiloDAO {
	
    private Connection yhteys;    
	
	public static void main(String[] args) {
        // Luo HenkiloDAO-olio
        HenkiloDAO henkiloDAO = new HenkiloDAO();

        // Lisää henkilöitä tietokantaan
        henkiloDAO.lisaaHenkilo(new Henkilo("Maija", 25));
        henkiloDAO.lisaaHenkilo(new Henkilo("Pekka", 30));

        // Hae kaikki henkilöt ja tulosta ne
        System.out.println("Kaikki henkilöt:");
        henkiloDAO.haeKaikkiHenkilot().forEach(System.out::println);

        // Päivitä henkilön tietoja
        Henkilo paivitettavaHenkilo = henkiloDAO.haeHenkiloNimella("Maija").orElse(null);
        if (paivitettavaHenkilo != null) {
            paivitettavaHenkilo.setIka(26);
            henkiloDAO.paivitaHenkilo(paivitettavaHenkilo);
            System.out.println("Henkilön tiedot päivitetty.");
        }

        // Hae kaikki henkilöt uudelleen ja tulosta ne päivityksen jälkeen
        System.out.println("Kaikki henkilöt päivityksen jälkeen:");
        henkiloDAO.haeKaikkiHenkilot().forEach(System.out::println);

        // Poista henkilö tietokannasta
        Henkilo poistettavaHenkilo = henkiloDAO.haeHenkiloNimella("Pekka").orElse(null);
        if (poistettavaHenkilo != null) {
            henkiloDAO.poistaHenkilo(poistettavaHenkilo.getId());
            System.out.println("Henkilö poistettu tietokannasta.");
        }

        // Hae kaikki henkilöt viimeisen toiminnon jälkeen ja tulosta ne
        System.out.println("Kaikki henkilöt poiston jälkeen:");
        henkiloDAO.haeKaikkiHenkilot().forEach(System.out::println);

        // Poistetaan kaikki henkilöt lopuksi
        henkiloDAO.poistaKaikkiHenkilot();
        
        // Sulje yhteys tietokantaan
        henkiloDAO.sulje();
    }
	
    public HenkiloDAO() {
        // Alusta yhteys. Tämä tulisi tehdä ihanteellisesti erillisessä metodissa tai yhteysaltaassa.
        String url = "jdbc:sqlite:src/main/java/tunti6/data/henkilokanta.sqlite"; //SQLLite luo tiedostokannan automaattisesti, tiedostokansio pitää olla olemassa ja kirjoitusoikeudet sinne annettu.
        
        //String mySqlURL = "jdbc:mysql://localhost:3306/my_database?user=myUser&password=myPassword";
        
        try {
            yhteys = DriverManager.getConnection(url);
            luoTaulu(); // Luo taulu, jos sitä ei ole
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void luoTaulu() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS henkilot (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nimi TEXT NOT NULL UNIQUE," +
                "ika INTEGER NOT NULL)";
        
        /*String mySsql = "CREATE TABLE IF NOT EXISTS henkilot ("
                + "id INT AUTO_INCREMENT PRIMARY KEY, "
                + "nimi VARCHAR(255) NOT NULL, "
                + "ika INT NOT NULL)";*/
        try (Statement lause = yhteys.createStatement()) {
            lause.execute(sql);
        }
    }

    public int lisaaHenkilo(Henkilo henkilo) {
        String sql = "INSERT INTO henkilot (nimi, ika) VALUES (?, ?)";
        // Käytetään prepared statementtia, joka estää myös SQL-injektiot.
        try (PreparedStatement valmisteltuLause = yhteys.prepareStatement(sql)) {
            valmisteltuLause.setString(1, henkilo.getNimi());
            valmisteltuLause.setInt(2, henkilo.getIka());
            int rowCount = valmisteltuLause.executeUpdate();
            return rowCount;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Henkilo> haeKaikkiHenkilot() {
        List<Henkilo> henkilot = new LinkedList<>();
        String sql = "SELECT * FROM henkilot";
        try (Statement lause = yhteys.createStatement();
             ResultSet tulokset = lause.executeQuery(sql)) {
            while (tulokset.next()) {
                int id = tulokset.getInt("id");
                String nimi = tulokset.getString("nimi");
                int ika = tulokset.getInt("ika");
                henkilot.add(new Henkilo(id, nimi, ika));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return henkilot;
    }
    
    // Hae henkilö nimen perusteella
    public Optional<Henkilo> haeHenkiloNimella(String nimi) {
        String query = "SELECT * FROM henkilot WHERE nimi = ?";
        try (PreparedStatement statement = yhteys.prepareStatement(query)) {
            statement.setString(1, nimi);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int ika = resultSet.getInt("ika");
                    return Optional.of(new Henkilo(id, nimi, ika));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Tässä olisi asianmukainen virheidenkäsittely
        }
        return Optional.empty();
    }

    public void paivitaHenkilo(Henkilo henkilo) {
        String sql = "UPDATE henkilot SET nimi = ?, ika = ? WHERE id = ?";
        try (PreparedStatement valmisteltuLause = yhteys.prepareStatement(sql)) {
            valmisteltuLause.setString(1, henkilo.getNimi());
            valmisteltuLause.setInt(2, henkilo.getIka());
            valmisteltuLause.setInt(3, henkilo.getId());
            valmisteltuLause.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void poistaHenkilo(int id) {
        String sql = "DELETE FROM henkilot WHERE id = ?";
        try (PreparedStatement valmisteltuLause = yhteys.prepareStatement(sql)) {
            valmisteltuLause.setInt(1, id);
            valmisteltuLause.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void poistaKaikkiHenkilot() {
        String sql = "DELETE FROM henkilot";
        
        try (PreparedStatement valmisteltuLause = yhteys.prepareStatement(sql)) {
            valmisteltuLause.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Sulje yhteys kun olet valmis
    public void sulje() {
        try {
            if (yhteys != null && !yhteys.isClosed()) {
                yhteys.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

