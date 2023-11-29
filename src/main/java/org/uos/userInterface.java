package org.uos;

import java.util.Scanner;

public class userInterface {

    public static void userControl() {
        renderInterface();
        byte userInput = 0;
        while (userInput > 5 || userInput < 1) {
            Scanner input = new Scanner(System.in);
            System.out.print("\n Enter a choice and Press ENTER to continue[1-5]:");
            userInput = input.nextByte();
        }
        InventoryManagement.userChoice(userInput);
    }

    public static void renderInterface() {
        System.out.println("""
       ╔═══════════════════════════════════════════════════════╗
       ║I N V E N T O R Y    M A N A G E M E N T    S Y S T E M║
       ╠═══════════════════════════════════════════════════════╣
       ║1. ADD NEW ITEM                                        ║
       ║2. UPDATE QUANTITY OF EXISTING ITEM                    ║
       ║3. REMOVE ITEM                                         ║
       ║4. DISPLAY INVENTORY OR TRANSACTIONS                   ║
       ╠═══════════════════════════════════════════════════════╣
       ║5. Exit                                                ║
       ╚═══════════════════════════════════════════════════════╝
        """);
    }
}
