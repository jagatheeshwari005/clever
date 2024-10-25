import java.util.Scanner;

public class HomePage {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to the To-Do List Application!");
            System.out.println("1. About");
            System.out.println("2. Signup");
            System.out.println("3. Login");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    showAbout();
                    break;
                case 2:
                    Signup signup = new Signup();
                    System.out.print("Enter username: ");
                    String signupUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String signupPassword = scanner.nextLine();
                    signup.signupUser(signupUsername, signupPassword);
                    break;
                case 3:
                    Login login = new Login();
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    if (login.loginUser(loginUsername, loginPassword)) {
                        System.out.println("Login successful!");
                        // Proceed to the To-Do List functionality here
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting the application. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void showAbout() {
        System.out.println("About This Application:");
        System.out.println("This is a simple To-Do List application that allows users to manage their tasks.");
        System.out.println("Users can sign up, log in, and add tasks to their list.");
        // Add more information as needed
    }
}
