package gabriel.views;

import java.util.Scanner;

public class Signin {
    public static void signin() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("1 - signin");
        System.out.println("2 - signup");

        String option = scanner.nextLine();

        switch (option) {
            case "1":
                System.out.println("entrei aqui");
                break;

            default:
                break;
        }

    }
}
