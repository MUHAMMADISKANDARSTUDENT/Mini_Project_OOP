/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vasun
 */
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExceptionHandlingTest {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        try {

            System.out.print("Enter first number: ");
            int number1 = input.nextInt();

            System.out.print("Enter second number: ");
            int number2 = input.nextInt();

            int result = number1 / number2;

            System.out.println("Result: " + result);

        } catch (InputMismatchException e) {

            System.out.println("Please enter whole numbers only.");

        } catch (ArithmeticException e) {

            System.out.println("The second number cannot be zero.");

        } finally {

            System.out.println("End of calculation.");

        }
    }
}