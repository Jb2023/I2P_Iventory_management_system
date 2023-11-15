package org.uos;

public class userInterface {
    public static void renderGUI() {
        System.out.println("""
       ╔═══════════════════════════════════════════════════════╗
       ║I N V E N T O R Y    M A N A G E M E N T    S Y S T E M║
       ╠═══════════════════════════════════════════════════════╣
       ║1. ADD NEW ITEM                                        ║
       ║2. UPDATE QUANTITY OF EXISTING ITEM                    ║
       ║3. REMOVE ITEM                                         ║
       ║4. VIEW DAILY TRANSACTION REPORT                       ║
       ╠═══════════════════════════════════════════════════════╣
       ║5. Exit                                                ║
       ╚═══════════════════════════════════════════════════════╝
        """);
    }
}
