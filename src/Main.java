import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Main m1 = new Main();
        m1.Init();
    }
    public void Init() {
        try {
            Info1 m1 = new Info1();
            m1.Serier();
            m1.printSeries();
            Info m2 = new Info();
            m2.Films();
            m2.printMovies();
        } catch (Exception e) {
            System.out.println("Filmen eller Serien findes ikke");
        }
    }
}