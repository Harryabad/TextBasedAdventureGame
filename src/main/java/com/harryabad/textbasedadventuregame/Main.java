package com.harryabad.textbasedadventuregame;

import java.util.Random;
import java.util.Scanner;
public class Main {

    public static void main (String[] args){

        Scanner in = new Scanner(System.in);
        Random rand = new Random();

        // Game Variables
        String[] enemies = {"Skeleton Fighter"," Skeleton Warrior", "Skeleton Knight", "Skeleton Soldier"};
        int maxEnemyHealth = 75;
        int maxEnemyAttackDamage = 25;

        // Player Variables
        int playerHealth = 100;
        int playerAttackDamage = 50;
        int numHealthPotions = 3;
        int healthPotionHealAmount = 30;
        int healthPotionDropChange = 50; // percentage

        boolean running = true;

        System.out.println("Welcome to the Dungeon");

        GAME: // << label
        while(running){
            System.out.println("-----------------------------------------------");

            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)];
            System.out.println("\t# " + enemy + " appeared! #\n");

            while (enemyHealth > 0){
                System.out.println("\tYour HP: " + playerHealth);
                System.out.println("\t" + enemy + "'s HP: " + enemyHealth);
                System.out.println("\n\tWhat would you like to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health potion");
                System.out.println("\t3. Run!");

                String input = in.nextLine();
                if(input.equals("1")){
                    int damageDealt = rand.nextInt(playerAttackDamage);
                    int damageTaken = rand.nextInt(maxEnemyAttackDamage);

                    enemyHealth -= damageDealt;
                    playerHealth -= damageTaken;

                    System.out.println("\t> You strike the " + enemy + " for " + damageDealt + ".");
                    System.out.println("\t> The " + enemy + " strikes back for "+ damageTaken + ".");

                    if(playerHealth < 1){
                        System.out.println("\t> You have taken too much damage" + ".");
                        break;
                    }
                } else if(input.equals("2")){
                    if(numHealthPotions > 0){
                        playerHealth += healthPotionHealAmount;
                        numHealthPotions--;
                        System.out.println("\t> You drink a health potion healing you for " + healthPotionHealAmount + "."
                                            + "\n\t> You now have " + playerHealth + " HP."
                                            + "\n\t> You have " + numHealthPotions + " health potions left.\n");
                    }else{
                        System.out.println("\t> You have no health potions left. Defeat enemies for a chance to get one.");
                    }
                } else if(input.equals("3")){
                    System.out.println("\tYou run away from the " + enemy + ".");
                    continue GAME; //reruns while loop from label
                }else{
                    System.out.println("\tInvalid Command");
                    //as last option in a while loop and no change made, will just recall most recent while loop option
                }
            }
            if (playerHealth < 1){
                System.out.println("Badly wounded you manage to escape from the dungeon.");
                break;
            }
            //case where enemy was defeated
            System.out.println("-----------------------------------------------");
            System.out.println(" # " + enemy + " was defeated! # ");
            System.out.println(" # You have " + playerHealth + " HP left. #");
            if(rand.nextInt(100)< healthPotionDropChange){
                numHealthPotions++;
                System.out.println(" # The " + enemy + " dropped a health potion. #");
                System.out.println(" # YOu now have " + numHealthPotions + " health potion(s). # ");
            }
            System.out.println("-----------------------------------------------");
            System.out.println("What would you like to do now?");
            System.out.println("1. Continue fighting");
            System.out.println("2. Exit Dungeon");

            String input = in.nextLine();

            while (!input.equals("1") && !input.equals("2")){
                System.out.println("Invalid command.");
                input = in.nextLine();
            }
            if(input.equals("1")){
                System.out.println("You continue on your adventure.");
            } else if(input.equals("2")){
                System.out.println("You exit the dungeon, successful from your adventures");
                break;
            }

        }
        System.out.println("######################");
        System.out.println("# THANKS FOR PLAYING #");
        System.out.println("######################");
    }
}
