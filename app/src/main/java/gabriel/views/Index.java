package gabriel.views;

import java.util.Scanner;

public class Index {
    public static void index() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - signin");
        System.out.println("2 - signup");

        String option = scanner.nextLine();

        switch (option) {
            case "1":
                System.out.println("entrei aqui");
                break;

            default:
                System.out.println("no option");
                break;
        }

    }
}
