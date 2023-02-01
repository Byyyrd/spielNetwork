import java.io.IOException;

/**
 * --Connection--
 * <br> Gewünschte IP4 eingeben, bei einem Spieler ::1/127.0.0.1 möglich
 * <br> Namen eingeben
 * <br> Auf Connect drücken
 * <br> <p>
 * <br> --Spiel--
 * <br> WASD zum Bewegen
 * <br> Schwert und Bogen aufsammeln, um diese freizuschalten
 * <br> Wechseln zwischen Schwert und Bogen mit F, zur Faust mit Q, weg von der Faust mit F
 * <br> Während man ein Schwert hält, kann man mit der rechten Maustaste Dashen
 * <br>
 * <br>
 * <br> Jedes Mal, wenn man seinen Gegner besiegt, erhält man 5 Exp
 * <br> Bei 20 Exp gibt es ein LevelUp(zum Testen LevelUP mit G möglich)
 * <br> Entweder ein zufälliges Item oder ein StatUpgrade wählen
 * <br> Items im Inventar(esc drücken)zu sehen, mit der linken Maustaste Equipen/Deequipen
 * <br> Stats in einem ToolTip zu sehen
 * <br>  Blau: Perfekt
 * <br>  Grün: Gut
 * <br>  Hellgrün: Ok
 * <br>  Gelb: Akzeptabel
 * <br>  Orange: Schlecht
 * <br>  Rot: Schlechtester
 * <br>
 * <br>  Unten rechts kleiner Chat
 * <br>  Mit ^ (über Tab) großen Chat öffnen und unten Nachrichten schreiben (Abschicken mit Enter)
 * <br>
 * <br>  Inventar & Chat:
 * <br>  Stats in Weiß: Stats durch upgrades
 * <br>  Stats in klein und Grün: Stats durch Equipped Items
 */
public class Main {
    public static void main(String[] args) throws IOException {
        var frameThread = new Thread(ConnectionFrame::new);
        frameThread.start();
        System.out.println("created server");
        new Server();
    }
}