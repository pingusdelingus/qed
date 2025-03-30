package com.parser.qed;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Lexer
{

private static final Map<String, TokenType> keywords;

static{
    keywords = new HashMap<>();
        keywords.put("and",    TokenType.AND);
        keywords.put("class",  TokenType.CLASS);
        keywords.put("else",   TokenType.ELSE);
        keywords.put("false",  TokenType.FALSE);
        keywords.put("for",    TokenType.FOR);
        keywords.put("fun",    TokenType.FUN);
        keywords.put("if",     TokenType.IF);
        keywords.put("nil",    TokenType.NIL);
        keywords.put("or",     TokenType.OR);
        keywords.put("print",  TokenType.PRINT);
        keywords.put("return", TokenType.RETURN);
        keywords.put("super",  TokenType.SUPER);
        keywords.put("this",   TokenType.THIS);
        keywords.put("true",   TokenType.TRUE);
        keywords.put("var",    TokenType.VAR);
        keywords.put("while",  TokenType.WHILE);
  }


  private final String source;
  private final List<Token> tokens = new ArrayList<>();
  private int start = 0;
  private int current = 0;
  private int line = 1;

Lexer(String source)
{
  this.source = source;
  }

  private boolean isAtEnd()
{
    return current >= source.length();
  }


  List<Token> scanTokens()
{
  while(!isAtEnd())
  {
    start = current;
      scanToken();
    }// end of while
    
    tokens.add(new Token(TokenType.EOF, "", null, line));
    return tokens;


  }// end of scan tokens method
   
public void addToken(TokenType type)
{
    addToken(type, "null");

  }// end of addToken
public void addToken(TokenType token, Object literal)
{
  
  String currText = source.substring(start,current);
    tokens.add(new Token(token, currText, literal, line)); // curr text to text??

  }// end of addToken

public char advance()
{
return source.charAt(current++);  }// end of advance


public boolean match(char expected)
{
  if (isAtEnd()){ return false;}
  if (source.charAt(current) != expected) return false;
  current++;
    return true;

  }// end of match

private char peek()
{
  if (isAtEnd()) return '\0';
  return source.charAt(current);
  }// end of peek

private void scanToken()
{

  char c = advance();
    switch (c)
  {
    case '(': addToken(TokenType.LEFT_PAREN); break;
      case ')': addToken(TokenType.RIGHT_PAREN); break;
      case '{': addToken(TokenType.LEFT_BRACE); break;
      case '}': addToken(TokenType.RIGHT_BRACE); break;
      case ',': addToken(TokenType.COMMA); break;
      case '.': addToken(TokenType.DOT); break;
      case '-': addToken(TokenType.MINUS); break;
      case '+': addToken(TokenType.PLUS); break;
      case ';': addToken(TokenType.SEMICOLON); break;
      case '*': addToken(TokenType.STAR); break; 
      case '!':
        addToken(match('=') ? TokenType.BANG_EQUAL : TokenType.BANG);
        break;
      case '=':
        addToken(match('=') ? TokenType.EQUAL_EQUAL : TokenType.EQUAL);
        break;
      case '<':
        addToken(match('=') ? TokenType.LESS_EQUAL : TokenType.LESS);
        break;
      case '>':
        addToken(match('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER);
        break;
      case '/':
        if (match('/')){
        while (peek() != '\n' && isAtEnd()) advance();
      } else if (match('*')){
        while (peek() != '*' && peekNext() != '/') advance();
      }
      else {
        addToken(TokenType.SLASH);
      }
      break;
      case ' ':
      case '\r':
      case '\t':
        // ignore whitespace
      break;

      case '\n':
        line++;
      break;


      // parsing string litterals 
      case '"': string(); break;

      default:
      if (isDigit(c)){
        number();
      }else if (isAlpha(c)){
        identifier();
      } else{
      qed.error(line, "Unexpected character."); // will add in later
      }
      break;

    }// end of switch
    
    
  }
private boolean isAlpha(char c)
  {
   return (c>= 'a' && c <= 'z') || (c >= 'A' && c<= 'Z') || (c == '_'); 
  }// end of isAlpha
private boolean isAlphaNumberic(char c)
  {
    return isAlpha(c) || isDigit(c);

  }// end of isAlphanumerbic

private void identifier()
  {
    while (isAlphaNumberic(peek())) advance();
    String text = source.substring(start, current);
      TokenType type = keywords.get(text);
      if (type == null) type = TokenType.IDENTIFIER;
      
    addToken(type);
  }// end of idnetifier
private char peekNext()
  {
  if (current + 1 >= source.length()) return '\0';
  return source.charAt(current + 1);

  }// end of peeknext
    
 private void number()
  {
    while (isDigit(peek())) advance();
    if (peek() == '.' && isDigit(peekNext())){
        //consume the '.'
        advance();
    while (isDigit(peek())) advance();
      }

      addToken(TokenType.NUMBER, Double.parseDouble(source.substring(start,current)));

  }// end of number method
    
private boolean isDigit(char c)
  {
  return c >= '0' && c <= '9';


  }// end of isdigit


public void string()
  {
    while (peek() != '"' && !isAtEnd())
    {
      if (peek() == '\n') line++;
        advance();
      }// end of while

      if (isAtEnd()){
//        qed.error(line, "Unterminated String literal.")
          return ;
      }

      // the closing
      advance();
      String value = source.substring(start+1, current-1);
      addToken(TokenType.STRING, value);

    }// end of string method


}// end of scanner class
