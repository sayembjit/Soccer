package org.example;

import java.sql.*;

public class DBOperations {
    Connection conn;
    int matchId = 1;
    int clubId = 1;

    public void doConnectDB() {
        String connectionStr = "jdbc:mysql://localhost:3306/" + Utilities.DB_NAME;
        String userName = "root";
        String password = "";
        try {
            conn = DriverManager.getConnection(connectionStr, userName, password);
            System.out.println("DB Connection is seccussful!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fetchData() {
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT p.first_name, p.last_name, p.position " +
                    "FROM Players p " +
                    "INNER JOIN Matches m ON p.club_id = m.home_team_id OR p.club_id = m.away_team_id " +
                    "WHERE m.match_id = " + matchId;

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String position = rs.getString("position");
                System.out.println(firstName + " " + lastName + " (" + position + ")");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void fetchDataForMatch() {
        try {
            Statement stmt = conn.createStatement();
            String query = "SELECT m.date, m.stadium, m.home_team_score, m.away_team_score " +
                    "FROM Matches m " +
                    "WHERE m.home_team_id = " + clubId + " OR m.away_team_id = " + clubId;

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Date date = rs.getDate("date");
                String stadium = rs.getString("stadium");
                int homeScore = rs.getInt("home_team_score");
                int awayScore = rs.getInt("away_team_score");
                System.out.println("Date: " + date + ", Stadium: " + stadium + ", Home Score: " + homeScore + ", Away Score: " + awayScore);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
