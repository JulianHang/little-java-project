package tech.zlia.interest.example;

import java.util.Scanner;
/**
 * 读取控制台输入,不适合用于从控制台读取密码（明文）
 * @version - 1.0.0 2018-12-13
 * @author - zlia
 */
public class InputTest {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); //关联输入流

        System.out.println("What is you name ?");
        String name = in.nextLine();
        System.out.println("How old are you ?");
        int age = in.nextInt();
        System.out.println("Hello, "+ name + ".Next year,you'll be " + (age + 1));
    }
}
