/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ooc_homework3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Stefani Nunes
 */
//Teams names using enum
enum TeamName {
    TEAM1, TEAM2, TEAM3, TEAM4, TEAM5, TEAM6, TEAM7, TEAM8, TEAM9, TEAM10,
    TEAM11, TEAM12, TEAM13, TEAM14, TEAM15, TEAM16, TEAM17, TEAM18, TEAM19, TEAM20
}

public class OOC_Homework3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
   Set<String> allMembers = new HashSet<>();
        List<Team> teams = new ArrayList<>();

        // Read data from the CSV file 
        try (BufferedReader reader = new BufferedReader(new FileReader("MOCK_DATA.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String id = parts[0].trim();       //string to read each part of the rows from the document
                    String firstName = parts[1].trim();
                    String lastName = parts[2].trim();
                    String email = parts[3].trim();
                    allMembers.add(id + "," + firstName + "-" + lastName + "/" + email);// visual separe    tion
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the CSV file.");
            e.printStackTrace();
            System.exit(1);
        }

        // Create 20 teams with 5 members each
        for (TeamName teamName : TeamName.values()) {
            List<String> teamMembers = new ArrayList<>();
            while (teamMembers.size() < 5) {
                String randomMember = getRandomMember(allMembers); 
                teamMembers.add(randomMember);
                allMembers.remove(randomMember);
            }
            teams.add(new Team(teamName, teamMembers));
        }

        // Display teams
        for (Team team : teams) {
            System.out.println("Team Name: " + team.getTeamName()); //message to be output
            System.out.println("Members:");
            for (String member : team.getMembers()) {
                System.out.println(member);
            }
            System.out.println();
        }
    }

    private static String getRandomMember(Set<String> members) {
        int randomIndex = new Random().nextInt(members.size());//output random names from the list
        Iterator<String> iterator = members.iterator();
        for (int i = 0; i < randomIndex; i++) {
            iterator.next();
        }
        String randomMember = iterator.next();
        return randomMember;
    }
}

class Team {
    private TeamName teamName;
    private List<String> members;

    public Team(TeamName teamName, List<String> members) {
        this.teamName = teamName;
        this.members = members;
    }

    public TeamName getTeamName() {
        return teamName;
    }

    public List<String> getMembers() {
        return members;
    }
}  