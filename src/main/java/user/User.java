package user;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class User
{
     static boolean ans=true;

    public static int  signin(Connection con) throws SQLException
    {

        Scanner sc=new Scanner(System.in);
        Random rm = new Random();
        int user_id = rm.nextInt(500);
        try
        {
            System.out.println("******************************");
            System.out.println("1 : Login");
            System.out.println("2 : Create new account");
            int sa = sc.nextInt();
            if (sa == 1)
            {

                //for existing customer

                System.out.println("Please enter your credentials to login into Application. ");
                System.out.println("Enter your user_id");
                String uid = sc.next();
                System.out.println("Enter your password");
                String password = sc.next();
                String finalpassword = null;

                boolean ismatch = Pattern.matches("[a-z]+@[1-9]+", password);

                if (ismatch == false)
                {
                    System.out.println("Wrong Password");
                    System.out.println("password must have alphabet number and special character");
                }

                if (ismatch)
                {
                    finalpassword = password;

                }

                Statement stmt = con.createStatement();
                String query1 = "select password from user where user_id= '" + uid + "' ;";
                ResultSet rs1 = stmt.executeQuery(query1);
                String pswrd = null;
                while (rs1.next())
                {
                    pswrd = rs1.getString(1);
                }

                if ( finalpassword!=null && finalpassword.equalsIgnoreCase(pswrd))
                {
                    System.out.println("Welcome to your Account.");

                    String query3 = "select * from user where user_id= '" + uid + "' ";
                    ResultSet rs3 = stmt.executeQuery(query3);

                    int uId = 0;
                    String nn=null;
                    while (rs3.next())
                    {
                        uId = rs3.getInt(1);
                        nn= rs3.getString(2);
                    }
                    System.out.println(nn);
                    System.out.println("----------------------------------------------------------------");
                    ans=true;
                    return uId;


                }
                else
                {
                    System.out.println("Try again");
                    ans = false;
                }
            }
            else if (sa == 2)
            {
                //for new customer
                System.out.println("Enter Some basic details  ");


                String q1 = "insert into user values(?,?,?)";
                PreparedStatement ps1 = con.prepareStatement(q1);
                ps1.setInt(1, user_id);

                System.out.println("Enter your name :");
                String name = sc.next();
                boolean us = Pattern.matches("[a-z]+", name);
                String finalname = null;

                if (us == false)
                {
                    System.out.println("INVALID NAME");
                    System.out.println("User Name must have alphabet only");
                    ans=false;
                }

                if (us)
                {
                    finalname = name;
                    ps1.setString(2, finalname);

                    System.out.println("Enter the password");
                    String password = sc.next();
                    ps1.setString(3, password);


                    int res = ps1.executeUpdate();
                    System.out.println("Your auto generated user_id is  :  " + user_id);
                    System.out.println("Please remember for further login");

                }


                System.out.println("**************************************************");
                return user_id;
            }
            else
            {
                System.out.println("Invalid Input");
                ans=false;
            }

        }
        catch (NullPointerException a)
        {
            a.printStackTrace();
        }

        return user_id;
    }

    public static boolean condition()
    {
        return ans;
    }
}
