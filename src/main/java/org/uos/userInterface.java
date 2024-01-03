package org.uos;

import java.util.ArrayList;
import java.util.List;

public class userInterface {
    public void renderInterface() {
        System.out.println("""
       ╔═══════════════════════════════════════════════════════╗
       ║I N V E N T O R Y    M A N A G E M E N T    S Y S T E M║
       ╠═══════════════════════════════════════════════════════╣
       ║1. ADD NEW ITEM                                        ║
       ║2. UPDATE QUANTITY OF EXISTING ITEM                    ║
       ║3. REMOVE ITEM                                         ║
       ║4. DISPLAY INVENTORY                                   ║
       ║5. DISPLAY TRANSACTIONS                                ║
       ╠═══════════════════════════════════════════════════════╣
       ║6. Exit                                                ║
       ╚═══════════════════════════════════════════════════════╝
        """);
    }

    public void viewInventory(ArrayList<List<String>> recordType) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════╗");
        for (int i = 0; i < recordType.size(); i++) {
            for (String field: recordType.get(i)) {
                System.out.printf("%-15s", field);
            }
            System.out.println("");
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════╝");
    }


}
