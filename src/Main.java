import java.io.IOException;

/**
 * --Connection--
 * Gewünschte IP4 eingeben, bei einem Spieler ::1/127.0.0.1 möglich
 * Namen eingeben
 * Auf Connect drücken
 * <p>
 * --Spiel--
 * WASD zum Bewegen
 * Schwert und Bogen aufsammeln, um diese freizuschalten
 * Wechseln zwischen Schwert und Bogen mit F, zur Faust mit Q, weg von der Faust mit F
 * Während man ein Schwert hält, kann man mit der rechten Maustaste Dashen
 * Jedes mal, wenn man seinen Gegner besiegt, erhält man 5 Exp
 * Bei 20 Exp gibt es ein LevelUp(zum Testen LevelUP mit G möglich)
 * Entweder ein zufälliges Item oder ein StatUpgrade wählen
 * Items im Inventar(esc drücken)zu sehen, mit der linken Maustaste Equipen/Deequipen
 * Stats in einem ToolTip zu sehen
 *  Blau: Perfekt
 *  Grün: Gut
 *  Hellgrün: Ok
 *  Gelb: Akzeptabel
 *  Orange: Schlecht
 *  Rot: Schlechtester
 *
 *  Unten rechts kleiner Chat
 *  Mit ^ (über Tab) großen Chat öffnen und unten Nachrichten schreiben (Abschicken mit Enter)
 *
 *  Inventar & Chat:
 *  Stats in Weiß: Stats durch upgrades
 *  Stats in klein und Grün: Stats durch Equipped Items
 */
public class Main {
    public static void main(String[] args) throws IOException {
        var frameThread = new Thread(ConnectionFrame::new);
        frameThread.start();
        System.out.println("created server");
        new Server();
    }
}