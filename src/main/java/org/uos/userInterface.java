package org.uos;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
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
       ║4. DISPLAY INVENTORY OR TRANSACTIONS                   ║
       ╠═══════════════════════════════════════════════════════╣
       ║5. Exit                                                ║
       ╚═══════════════════════════════════════════════════════╝
        """);
    }

    public void viewInventory() {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════╗");
        for (int i = 0; i < Processes.records.size(); i++) {
            for (String field: Processes.records.get(i)) {
                System.out.printf("%-15s", field);
            }
            System.out.println("");
        }
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════╝");
    }


}
