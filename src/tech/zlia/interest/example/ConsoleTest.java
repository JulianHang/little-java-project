package tech.zlia.interest.example;

import java.io.Console;

/**
 * 读取控制台输入，只能在原始控制台中使用，如终端，
 * ide或eclipse中的控制台使用会报错
 * @version - 1.0.0 2018-12-13
 * @author - zlia
 */
public class ConsoleTest {

    public static void main(String[] args) {
        Console cons = System.console();
        String username = cons.readLine();
        char[] password =cons.readPassword();
        System.out.println("username : " + username);
        System.out.println("password : " + password.toString());
    }
}
