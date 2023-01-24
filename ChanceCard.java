/*Author: An Ha
 *Date: January 23, 2022
 *Course: ICS4U
 *Description: This class contains every possible instance the user can run into 
 *upon pulling a "chance" card (from landing on a chance square)
 */

import java.util.*;
public class ChanceCard {
    //variables
	public String name;
	
    //constructor
    public ChanceCard (String newName) {
    	name = newName;
    }

    /* Pre: Player player, Scanner input
	 * Post: void
	 * Action: Depending on what the player draws, it displays a random chance event*/
    public void displayCardInfo (Player player, Scanner input) {
        //every single possible chance card they can draw...
        if (name.equals("1.1")) {
            System.out.println("In the midst of an all nighter, you have this crazy idea: \n" + 
                                "uploading a reddit post pitching an idea of robot that dumps oil in the ocean.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("In doing this, you broke ACM's Code of Ethics 1.1:");
            System.out.println("A computing professional should contribute to society and to human well-being,\n" +
                                "acknowledging that all people are stakeholders in computing.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("Redditors scorn you and downvote you to oblivion. \n" +
                                "So, you spend $20 to buy Reddit bots and restore your karma.");
            player.getCash(-20);
        } else if (name.equals("1.2")) {
            System.out.println("While showing your new toaster robot to your friend, the exposed wiring gets short circuited.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("Your friend is electrocuted.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("They are admitted to the hospital.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("In doing this, you broke ACM's Code of Ethics 1.2:");
            System.out.println("A computing professional should avoid harm.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("You owe them $100 to pay off their hospital bill");
            player.getCash(-100);
        } else if (name.equals("1.3")) {
            System.out.println("Today is the day you pitch your project to your first interested investor.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("You tell them about your idea of a program that simulates a broken computer.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("\"So... it does nothing?\" they ask.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("\"Yes\" you answer.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("\"So your idea is like... useless?\" they ask. ");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("\"Yes\" you answer.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("\"...\"");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("\"...\"");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("\"I love your honesty and how it complies with ACM's Code of Ethics 1.3: \nBeing honest and trustworthy\" they say.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("They hand you $300 as an investment in your project.");
            player.getCash(300);
        } else if (name.equals("1.4")) {
            System.out.println("You create a gun that kills all men.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("ACM's Code of Ethics 1.4 (be fair and take action not to discriminate) doesn't like that.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("Men then band together to beat you up and take your money out of anger.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("You lost $50.");
            player.getCash(-50);
        } else if (name.equals("1.5")) {
            System.out.println("Your friend shows you their coding project and you laugh, calling it awful and useless.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("Then, you remember that you broke AMC's Code of Ethics 1.5: \n" +
                                "Respect the work required to produce new ideas,\ninventions, creative works, and computing artifacts.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("You pay $5 to buy them a coffee and ask for forgiveness.");
            player.getCash(-5);
        } else if (name.equals("1.6")) {
            System.out.println("You think it'll be funny to make a data collection program.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("You broke AMC's Code of Ethics 1.6: Respect privacy.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("You are arrested and pay bail fee of $30.");
            player.getCash(-30);
        } else if (name.equals("1.7")) {
            System.out.println("You tell your friend undisclosed information from your freelancer contract...");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("You broke AMC's Code of Ethics 1.7: Honor Confidentiality.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("You are sued for $50. Drats.");
            player.getCash(-50);
        } else if (name.equals("2.1")) {
            System.out.println("You worked on your project for days on end...");
            System.out.println("You can't even open your eyes...");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("But.. you must... get grocery...");
            System.out.println("You have to get outside.....");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("You try so hard to stay awake while scoruing through the dairy aisle....");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("Alas, you collapse, only to be woken up by a sweet looking person....");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();  
            System.out.println("\"Wow man. You must be a coder who's exhuausted from overworking. \n" +
                                "I appreciate you for following AMC's Code of Ethics 2.1:\n" +
                                "Strive to achieve high quality in both the processes and products of professional work.\"");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("They hand you a $20 bill with a sympathetic smile.");
            player.getCash(20);
        } else if (name.equals("2.2")) {
            System.out.println("Your friend is doing their own freelancing work!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("They are thinking of scamming their client...");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("But! You warn them of ACM's Code of Ethics 2.2:");
            System.out.println("Maintain high standards of professional competence, conduct, and ethical practice.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("They say \"You're right. I should be more ethical. Thank you for keeping me in line.\"");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("You didn't really earn anything from that but your friend learned a\nvery important lesson about being an ethical programmer.");
            player.getCash(0);
        } else if (name.equals("2.3")) {
            System.out.println("You walk along the street until suddenly...");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("A robber approaches you! Oh no!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("\"If you can't name all of ACM's Codes of Ethics, I'll steal your money!\" the robber says.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("\"Oh, that's easy,\" you say as you begin reciting all codes 1.1 to 4.2 with ease.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("\"Wow you must be a master coder as you follow code 2.3 of \n" + 
                                "knowing and respect existing rules pertaining to professional work.\"");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();  
            System.out.println("\"I guess you're let off the hook today. Have a great evening!\"");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("You do not gain nor lose money.");
            player.getCash(0);
        } else if (name.equals("2.4")) {
            System.out.println("You wake up to a new comment on your github repository!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("This commenter... in great detail... told you about how bad your code is!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("Instead of being sad, you take all their points into consideration and add them!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("The commenter comes back, saying \"I'm so glad you can follow ACM's Code of Ethics 2.4 of \n" + 
                                    "accepting/providing appropriate professional review.\"");
            System.out.println("[Press Enter To Continue]");
            input.nextLine(); 
            System.out.println("The next day, you see the commenter has found your email and sent you $10 on paypal. Nice!");
            player.getCash(10);
        } else if (name.equals("2.5")) {
            System.out.println("Your friend asks you to download ubuntu on their computer.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("You think that this is the perfect chance to halphhazardly \nsend them your newly made OS to download!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("It accidentally gives them a massive virus, violating ACM's Code of Ethics 2.5: \n" +
                                "Give comprehensive and thorough evaluations of computer systems and \ntheir impacts, including analysis of possible risks.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("You don't lose any money but you do lose a friend.");
            player.getCash(0);
        } else if (name.equals("2.6")) {
            System.out.println("A person on LinkedIn messages you about the possibility of commissioning a program for you!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("They want... A robot... that... performs surgery...");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("You don't know how to do that.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("You accept anyway, breaching code 1.6 of ACM's Codes of Ethics that states \nto perform work only in areas of competence.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("That was an awful idea. \nYou give them an unworking project and an investor withdraws from you, costing you $100.");
            player.getCash(-100);
        } else if (name.equals("2.7")) {
            System.out.println("On a whim, you host a coding class, which adheres to ACM's Code of Ethics 2.7: \n" + 
                                "Foster public awareness and understanding of computing, related technologies, \nand their consequences.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("Lots of people join your class and you end up making $400! Wow!");
            player.getCash(400);
        } else if (name.equals("2.8")) {
            System.out.println("Oh no! A very obvious internet phisher is sending scam emails to people!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("You could just leave them be... Or you can hack them and get rid of their schemes!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("It may seem really bad, but, it follows to ACM's Code of Ethics 2.8: \n" + 
                                "Access computing and communication resources only when authorized or when \ncompelled by the public good.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("You don't really earn anything from it... as in... 0 dollars... \nBut you feel like a proud samaritan for sure!");
            player.getCash(0);
        } else if (name.equals("2.9")) {
            System.out.println("Whew! Finally finished making a website! Can't wait to send over the files!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("There an obvious hole in the website's security but who's gonna notice anyway? Sent!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("You did not listen to ACM's Code of Ethics 2.9: \n" + 
                                    "Design and implement systems that are robustly and usably secure.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("You pay up $100 as compensation for your client getting hacked.");
            player.getCash(-100);
        } else if (name.equals("3.1")) {
            System.out.println("You send a project to a new freelance client.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("\"Thank you so much, you really did follow ACM's Code of Ethics 3.1: \n" + 
                                    "Ensure that the public good is the central concern during all professional computing work.\"");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("For that, you are tipped an extra $50. Wowzers!");
            player.getCash(50);
        } else if (name.equals("3.2")) {
            System.out.println("You are hired in a team to to make a mobile app!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("Even though they are useless, you persevere and follow ACM's Code of Ethics 3.2: \n" + 
                                "Articulate, encourage acceptance of, and evaluate fulfillment of social responsibilities by \nmembers of the organization or group.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("The person is very glad you are taking on a leadership role and \npays you a little bit extra for your work.");
            System.out.println("$30 extra. Yippee!!");
            player.getCash(30);
        } else if (name.equals("3.3")) {
            System.out.println("You decide your work would be so much better if you hire some interns for yourself!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("Except you don't follow ACM's Code of Ethics 3.3: \n" + 
                                    "Manage personnel and resources to enhance the quality of working life.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("You abuse your interns.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("They sue you for $80");
            player.getCash(-80);
        } else if (name.equals("3.4")) {
            System.out.println("Your friend is a hacker and is HACKING (illegally)!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("You turn a blind eye and do not say anything.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("Oops that doesn't adhere to ACM's Code of Ethics 3.4: \n" + 
                                "Articulate, apply, and support policies and processes that reflect the principles of the Code.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("Your friend is in jail and you are arrested as an accomplice for not stopping them.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("You pay $50 to bail them out.");
            player.getCash(-50);
        } else if (name.equals("3.5")) {
            System.out.println("Your friend is crying about not getting any CS jobs!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("Well... currently... you are freelancing right now... It wouldn't hurt to offer a \nrecommendation.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("You decide to do the nice thing and follow ACM's Code of Ethics 3.5: \n" + 
                                    "Create opportunities for members of the organization or group to grow as professionals.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("Your friend, using this job, built their way the top and now works at Google.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("\"Thank you for being there since the start. Here is a token of my appreciation :)\"\nthey say, giving you $20.");
            player.getCash(20);
        } else if (name.equals("3.6")) {
            System.out.println("An update for your softare is needed...");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("You decide to make all devices restart so it can update without any notice. \nWho's gonna care anyway?");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("Everyone cared. You broke ACM's Code of Ethics 3.6: \n" + 
                                    "Use care when modifying or retiring systems.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("A particularly angry user hacked you and stole $200 from your bank account. Oh no.");
            player.getCash(-200);
        } else if (name.equals("3.7")) {
            System.out.println("You finally did it! You made a phone! An actual real working phone!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("Except that with prolongue use, it causes brain damage.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("Nobody's even going to use this, so you don't care if it breaks ACM's Code of Ethics 3.7: \n" + 
                                "Recognize and take special care of systems that become integrated into the \ninfrastructure of society.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("Oops. A lawsuit for $300. Oops.");
            player.getCash(-300);
        } else if (name.equals("4.1")) {
            System.out.println("You decide to make an article preaching ACM online in hopes of getting recognition.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("It works!");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("ACM loves it since it follows their Code of Ethics 4.1 to \nuphold, promote, and respect the principles of the Code.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("They sponsor you with $100 to write another article about them.");
            player.getCash(100);
        } else {
            System.out.println("Even though you're not a member of ACM, you still try your best fo follow their rules.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("This is also due to code 4.2, which says to treat violations of the Code as \ninconsistent with membership in the ACM.");
            System.out.println("[Press Enter To Continue]");
            input.nextLine();
            System.out.println("Actual members of ACM recognize this and decide to give you $20 on your patreon for like.. \nfunsies!");
            player.getCash(20);
        }
    }

}
