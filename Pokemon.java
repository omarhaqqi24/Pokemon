package Pukimon;

import java.util.*;
import java.io.*;

public class Pokemon {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. New Game");
        System.out.println("2. Load Game");
        System.out.print("Select (1/2): ");
        String plh = scanner.nextLine();
        Player player;

        while (true) {
            if (plh.equals("1")) {
                // Example usage of scanner
                System.out.print("Enter your name: ");
                String playerName = scanner.nextLine();
                System.out.println("Hello, " + playerName + "! Welcome to the game.");
                player = new Player(playerName);
                break;
            } else {
                boolean repeat = false;
                ArrayList<String> users = new ArrayList<>();
                int count = 1;
                try {
                    if (repeat) {
                        continue;
                    } else {
                        File myObj = new File("user.txt");
                        Scanner myReader = new Scanner(myObj);
        
                        System.out.println("Select an user:");
                        if (!myReader.hasNext()) {
                            System.out.println("Save data not found");
                            repeat = true;
                            continue;
                        } else {
                            while (myReader.hasNextLine()) {
                            String data = myReader.nextLine();
                            users.add(data);
                            System.out.println(count++ + ". "  + data);
                            }
                        }
    
                        myReader.close();
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Save data not found");
                }
    
                System.out.print("Select: ");
                int plh2 = scanner.nextInt();
                ArrayList<Monster> mons = new ArrayList<>();
    
                try {
                    File myObj = new File(users.get(plh2-1) + ".txt");
                    Scanner myReader = new Scanner(myObj);
                    while (myReader.hasNextLine()) {
                      String data = myReader.nextLine();
                      String[] monsData = data.split(" ");
                      String nm = monsData[0];
                      int lv = Integer.parseInt(monsData[1]);
                      int hp = Integer.parseInt(monsData[2]);
                      int ep = Integer.parseInt(monsData[3]);
                      String el = monsData[4];
                      String sa = monsData[5];
                      
                      switch (el) {
                        case "Air":
                            mons.add(new MonsterAir(nm, lv, hp, ep));
                            break;
                      
                        case "Angin":
                        mons.add(new MonsterAngin(nm, lv, hp, ep));
                        break;
                    
                        case "Api":
                        mons.add(new MonsterApi(nm, lv, hp, ep));
                        break;
                    
                        case "Es":
                        mons.add(new MonsterEs(nm, lv, hp, ep));
                        break;
                    
                        case "Tanah":
                        mons.add(new MonsterTanah(nm, lv, hp, ep));
                        break;
                      }
                    }
                    myReader.close();
                  } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                  }
    
                player = new Player(users.get(plh2-1), mons);
                break;
            }
    
        }
        // Example game loop
        boolean running = true;
        while (running) {
            System.out.println("What do you want to do?");
            System.out.println("1. Explore dungeon");
            System.out.println("2. Save progress");
            System.out.println("3. Upgrade Monster");
            System.out.println("4. Monster Evolution");
            System.out.println("5. Quit");

            int choice = scanner.nextInt();
            System.out.println();
            
            switch (choice) {
                case 1:
                    player.exploreDungeon();
                    break;
                case 2:
                    player.saveProgress();
                    break;
                case 3:
                    player.upgradeMonster();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter again.");
                    break;
            }
        }
    }
}
