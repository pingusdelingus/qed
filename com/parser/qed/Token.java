
package com.parser.qed;
public class Token
  {
final TokenType type;
  final String lexeme;
  final Object literal;
  final int line;


  // constructor
  Token(TokenType type, String lexeme, Object literal, int line)
  {
  this.type = type;
  this.lexeme = lexeme;
  this.literal = literal;
  this.line = line;
  }// constructor


  //toString method
  public String toString()
{

  if (literal != null)return type + " " + lexeme + " " + literal + " on line " + line;
    else{
      return type + " " + lexeme + " on line " + line;

    }
  }// end of toString


}// end of token class
