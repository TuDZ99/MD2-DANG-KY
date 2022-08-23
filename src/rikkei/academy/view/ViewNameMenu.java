package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.dto.request.SingUpDTO;
import rikkei.academy.dto.response.ResponseMessenger;
import rikkei.academy.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class ViewNameMenu {
    UserController userController = new UserController();

    List<User> userList = userController.getUserList();
    public void menu () {
        System.out.println("===========Menu==========");
        System.out.println("1.Show User List");
        System.out.println("2.Register");

        int choice = Integer.parseInt(Config.scanner().nextLine());
        switch (choice) {
            case 1:
                formShowListUser();
                break;
            case 2:
                formRegister();
                break;
            default:
                System.out.println("Invalid choice");
        }
      menu();
    }
    private void formRegister() {
        System.out.println("======Register=======");
        //id
        int id;
        if (userList.isEmpty()){
            id = 1;
        } else {
            id = userList.get(userList.size()-1).getId() + 1;
        }
        //name
        String name;
        while (true){
            System.out.println("Enter name: ");
            name = Config.scanner().nextLine();
            if (name.matches("[A-Z][a-zA-Z]+")){
                break;
            } else {
                System.out.println("Invalid name, try again!");
            }
        }
        //username
        String username;
        boolean validateUsername;
        while (true) {
            System.out.println("Enter the username: ");
            username = Config.scanner().nextLine();
            validateUsername = Pattern.matches("[a-zA-Z0-9]{1,40}", username);
            if (validateUsername) {
                break;
            } else {
                System.out.println("The username failed! Please try again!");
            }
        }
        //email
        String email;
        while (true){
            System.out.println("Enter email: ");
            email = Config.scanner().nextLine();
            if (email.matches(".+@.+")){
                break;
            } else {
                System.out.println("Invalid email, try again!");
            }
        }
        //password
        String password;
        boolean validatePassword;
        while (true) {
            System.out.println("Enter the password: ");
            password = Config.scanner().nextLine();
            validatePassword = Pattern.matches("[a-zA-Z0-9]{1,40}", password);
            if (validatePassword) {
                break;
            } else {
                System.out.println("The password failed! Please try again!");
            }
        }
        //role
        System.out.println("Enter role: ");
        String role = Config.scanner().nextLine();
        Set<String> strRole = new HashSet<>();
        strRole.add(role);

        SingUpDTO singUpDTO = new SingUpDTO(id,name,username,email,password,strRole);

        ResponseMessenger responseMessenger = userController.register(singUpDTO);

        switch (responseMessenger.getMessage()){
            case "user_existed":
                System.out.println("Username already existed");
                break;
            case "email_existed":
                System.out.println("Email already existed");
                break;
            case "invalid_role":
                System.out.println("invalid already existed");
                break;
            case "success":
                System.out.println("success already existed");

        }

    }

    private void formShowListUser() {
        System.out.printf("%-15s%s%n", "Username", "Role");
        for (User user : userList){
            System.out.printf("%-15s%s%n", user.getUsername(),new ArrayList<>(user.getRoles()).get(0).getRoleName() );
        }

}

}










