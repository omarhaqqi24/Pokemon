package Pukimon;

import java.util.*;
import java.io.*;

class Player {
    private ArrayList<Monster> monsters;
    private Dungeon dungeon;
    private Scanner scanner;
    private String nama;

    public Player(String nama) {
        this.nama = nama;
        this.monsters = new ArrayList<>();
        this.scanner = new Scanner(System.in);

        monsters.add(new MonsterApi("Charmander"));
        monsters.add(new MonsterAir("Squirtle"));
        monsters.add(new MonsterAngin("Pidgeot"));
        monsters.add(new MonsterEs("Pogo"));
        monsters.add(new MonsterTanah("Swayworm"));

        this.dungeon = new Dungeon(this);
    }

    public Player(String nama, ArrayList<Monster> monsters) {
        this.nama = nama;
        this.monsters = monsters;

        this.dungeon = new Dungeon(this);
    }

    public ArrayList<Monster> getMonsters () {
        return monsters;
    }

    public void exploreDungeon() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose monsters to bring to the dungeon:");
        ArrayList<Monster> selectedMonsters = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            System.out.println("Select monster " + (i + 1) + ":");
            for (int j = 0; j < monsters.size(); j++) {
                System.out.println((j + 1) + ". " + monsters.get(j).getName() + " (Level: " +
                        monsters.get(j).getLevel() + ", HP: " + monsters.get(j).getHp() + ")");
            }

            System.out.print("Select: ");
            int choice = scanner.nextInt();
            if (choice < 1 || choice > monsters.size()) {
                System.out.println("Invalid choice. Please choose again.");
                i--;
            } else {
                selectedMonsters.add(monsters.get(choice - 1));
            }
            System.out.println();
        }

        System.out.println("Exploring the dungeon...");

        boolean fight = true;

        while (fight) {
            Random ran = new Random();
            ArrayList<Monster> monsterList = dungeon.getMonster();
            Monster enemyMonster = monsterList.get(ran.nextInt(monsterList.size()));
            System.out.println("ENEMY FOUND!!!");
            System.out.println(enemyMonster.getName() + " (" + enemyMonster.getElement() + ") " + "lvl." + enemyMonster.getLevel());
            System.out.println();

            System.out.println("Your Monster:");
            int count = 1;
            for (Monster a : selectedMonsters) {
                System.out.println(count++ + ". " + a.getName() + " (lvl. " + a.getLevel() + ")");
            }
            int select = 0;

            while (select < 1) {
                System.out.print("Select your monster: ");
                select = scanner.nextInt();
                if (select < 1 || select > selectedMonsters.size()) {
                    System.out.println("Input tidak valid!");
                }
            }
            System.out.println();

            Monster monster = selectedMonsters.get(select-1);

            dungeon.startBattle(monster, enemyMonster);
            if (monster.getHp() > 0) {
                System.out.println(monster.getName() + " survived the dungeon!");
                // Gain XP is already handled in the dungeon battle
            } else {
                System.out.println(monster.getName() + " fainted and was taken back to the home base.");
                monster.setHp(monster.getLevel() * 10);
                selectedMonsters.remove(select-1);
            }

            if (selectedMonsters.size() == 0) {
                System.out.println("All you monsters are defeated");
                System.out.println("Go to base!");
                System.out.println();
                break;
            }

            System.out.print("Continue explore Dungeon? (y/n): ");
            String inp = scanner.next();

            if (inp.equals("n") || inp.equals("N")) {
                fight = false;
            }
        }
    }

    public void upgradeMonster() {
        System.out.println("Select a monster to upgrade:");
        for (int i = 0; i < monsters.size(); i++) {
            Monster m = monsters.get(i);
            System.out.println((i + 1) + ". " + m.getName() + " (Level: " + m.getLevel() + ", XP: " + m.getEp() + ")");
        }

        int choice = scanner.nextInt();
        if (choice < 1 || choice > monsters.size()) {
            System.out.println("Invalid choice. Please choose again.");
            return;
        }

        Monster selectedMonster = monsters.get(choice - 1);
        if (selectedMonster.canLevelUp()) {
            selectedMonster.levelUp();
        } else {
            System.out.println(selectedMonster.getName() + " does not have enough XP to level up.");
        }
    }

    public void saveProgress() {
        try {
          FileWriter myWriter = new FileWriter(nama + ".txt");
          for (Monster m : monsters) {
            String mons = m.getName() + " " + m.getLevel() + " " + m.getHp() + " " + m.getEp() + " " + m.getElement() + " " + m.getStrengthAgainst();
            myWriter.write(mons + "\n");
          }
          myWriter.close();
          System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
        
        boolean found = false;

        try {
            File myObj = new File("user.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              if (data.equals(nama)) found = true;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        if (!found) {
            ArrayList<String> users = new ArrayList<>();
            try {
                File myObj = new File("user.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    users.add(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
            
            try {
                FileWriter myWriter = new FileWriter("user.txt");
                for (String n : users) myWriter.write(n + "\n");
                myWriter.write(nama + "\n");
                myWriter.close();
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
    }

    public void closeScanner() {
        scanner.close();
    }
}
