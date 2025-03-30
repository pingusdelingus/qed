package com.parser.qed;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.io.IOException;
import java.util.Scanner;

public class qed
{

  public static void run(String sourcecode)
  {
    Lexer input = new Lexer(sourcecode);

    List<Token> tokens = input.scanTokens();

    for (Token token : tokens)
  {
    System.out.println(token);
    }// end of for loop


  }// end of run method


  public static int runFile(String filepath) throws IOException
  {
    byte[] bytes = Files.readAllBytes(Paths.get(filepath));
    run(new String(bytes, Charset.defaultCharset()));


  return 0;
  }// end of runFile method

public static void runREPL()
{
  Scanner keyboard = new Scanner(System.in);
  
  System.out.print("qed version 1.0.0");
  System.out.print("> ");
  String input = keyboard.nextLine();

  while (!input.equals("\r") || input.equals("exit()"))
  {
    System.out.print("> ");
    run(input);

    input = keyboard.nextLine();

  }// end of while

}// end of run REPL method

public static void error(int line, String errmsg)
{
  System.out.println(errmsg + " on line :" + line);
    System.exit(1);

  }// end of error function

  public static void main(String[] args) throws IOException
  {
    String filePath = "";
    if (args.length > 1)
    {
      System.out.println("specify the usage as qed: 'foo.qed' ");
      System.exit(64);

    } else if ( args.length == 0)
    {
      runREPL();
    }

    filePath = args[0];
    int statusCode = runFile(filePath);

  }// end of main method
  
}// end of ASTNode clas
