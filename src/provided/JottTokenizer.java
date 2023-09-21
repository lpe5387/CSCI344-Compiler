package provided;

/*
 * This class is responsible for tokenizing Jott code.
 * 
 * @author Issac Kim, lucie lim, Dara Prak, Andrew Dantone, Luka Eaton
 **/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import exceptions.SyntaxException;

public class JottTokenizer {

	/**
   * Takes in a filename and tokenizes that file into Tokens
   * based on the rules of the Jott Language
   * @param filename the name of the file to tokenize; can be relative or absolute path
   * @return an ArrayList of Jott Tokens
   */
  public static ArrayList<Token> tokenize(String filename){

    ArrayList<Token> tokenStream = new ArrayList<>();

    //turns String filename into Path file
    Path file = Paths.get(filename);
    file = file.toAbsolutePath(); //redundancy in file pathing where CSCI344 repeated

    try{
      file = file.toRealPath();
      //turns file into a string
      String fileString = Files.readString(file);
      String charStream = "";
      int lineNum = 1;

      int endOfFile = fileString.length();
      int decimal = 0;

      Token token;

      //goes through whole file string
      for ( int i = 0; i < endOfFile; i++ ) {

        char ch = fileString.charAt(i);

        switch(ch){
          /*
           * Initial case:
           *  charStream = ""
           *  decimal = 0
           */
          case ',':
            charStream = charStream + ",";
            token = new Token(charStream, filename, lineNum, TokenType.COMMA);
            tokenStream.add(token);
            break;
          case '[':
            charStream = charStream + "[";
            token = new Token(charStream, filename, lineNum, TokenType.L_BRACKET);
            tokenStream.add(token);
            break;
          case ']':
            charStream = charStream + "]";          
            token = new Token(charStream, filename, lineNum, TokenType.R_BRACKET);
            tokenStream.add(token);
            break;
          case '{':
            charStream = charStream + "{";
            token = new Token(charStream, filename, lineNum, TokenType.L_BRACE);
            tokenStream.add(token);
            break;
          case '}':
            charStream = charStream + "}";
            token = new Token(charStream, filename, lineNum, TokenType.R_BRACE);
            tokenStream.add(token);
            break;
          case '=':
            if(i+1 >= endOfFile){
              token = new Token("=", filename, lineNum, TokenType.ASSIGN);
              tokenStream.add(token);
            }
            else {
              // check to see if the next char is a another = sign
              char nextChar = fileString.charAt(i+1);
              if ( nextChar == '=' ) {
                token = new Token("==", filename, lineNum, TokenType.REL_OP);
                tokenStream.add(token);
                //update position in the file string
                i++;
              } else {
                token = new Token("=", filename, lineNum, TokenType.ASSIGN);
                tokenStream.add(token);
              }
            }
            break;
          case '<':
            if(i+1 >= endOfFile){
              token = new Token("<", filename, lineNum, TokenType.REL_OP);
              tokenStream.add(token);
            }
            else{
              char nextChar = fileString.charAt(i+1);
              if ( nextChar == '=' ) {
                //add the equal to the current char which could be < or > into a string
                charStream = ch + "=";
                //put that string into the tokenizer
                token = new Token(charStream, filename, lineNum, TokenType.REL_OP);
                tokenStream.add(token);
                i++;
              } else {
                token = new Token("<", filename, lineNum, TokenType.REL_OP);
                tokenStream.add(token);
              }
            }
            break;
          case '>':
            if(i+1 >= endOfFile){
              token = new Token(">", filename, lineNum, TokenType.REL_OP);
              tokenStream.add(token);
            }
            else{
              char nextChar = fileString.charAt(i+1);
              if ( nextChar == '=' ) {
                //add the equal to the current char which could be < or > into a string
                charStream = ch + "=";
                //put that string into the tokenizer
                token = new Token(charStream, filename, lineNum, TokenType.REL_OP);
                tokenStream.add(token);
                i++;
              } else {
                token = new Token(">", filename, lineNum, TokenType.REL_OP);
                tokenStream.add(token);
              }
            }
            break;
          case '/':
            token = new Token("/", filename, lineNum, TokenType.MATH_OP);
            tokenStream.add(token);
            break;
          case '+':
            token = new Token("+", filename, lineNum, TokenType.MATH_OP);
            tokenStream.add(token);
            break;
          case '-':
            token = new Token("-", filename, lineNum, TokenType.MATH_OP);
            tokenStream.add(token);
            break;
          case '*':
            token = new Token("*", filename, lineNum, TokenType.MATH_OP);
            tokenStream.add(token);
            break;
          case '!':
            if(i+1 >= endOfFile){
              throw new SyntaxException("Invalid token '!'" + ", expected '=' afterwards.", filename, lineNum);
            }
            else{
              //check next char for an =
              char nextChar = fileString.charAt(i + 1);
              if (nextChar == '='){ //if so make it a token
                token = new Token("!=", filename, lineNum, TokenType.REL_OP);
                tokenStream.add(token);
                i++;
              }
              else{//otherwise its an error as an ! can only be followed by an =
                throw new SyntaxException("Invalid token '" + nextChar + "'" + ", expected '=' after a '!'.", filename, lineNum);//error out here and break
              }
            }
            break;
          case '"':
            if(i+1 >= endOfFile){
              throw new SyntaxException("Invalid token ' \" '" + ", needs a closing ' \" ' to complete the string.", filename, lineNum);
            }
            else{
              String tok = "\""; //initial " for start of string
              char nextChar = fileString.charAt(i + 1); //get the next character
              tok += nextChar; //add it to the token string, have to do this here in case empty string
              int x = 1;
              //loops through all follwing nums, chars, and spaces
              while(Character.isDigit(nextChar) || Character.isAlphabetic(nextChar) || nextChar == ' '){
                if (x != 1){
                  tok += nextChar; //add character to string
                }
                x++; //increase count
                if(i+x >= endOfFile) break;
                nextChar = fileString.charAt(i + x); //get next character
              }
              //catching the ending "
              if (x != 1){
                tok += "\"";
              }
              x++;
              //if we end with a " end normally
              if (nextChar == '"'){
                token = new Token(tok, filename, lineNum, TokenType.STRING);
                tokenStream.add(token);
                i += x;
              }
              else{ //if we end with anything else error, if we reach here with a num, char, or space we are at EOF
                throw new SyntaxException("Invalid token '\"'" + ", expected a closing '\"'.", filename, lineNum);//error out here and break
              }
            }
            break;
          case ';':
            token = new Token(";", filename, lineNum, TokenType.SEMICOLON);
            tokenStream.add(token);
            break;
          case ':':
            if(i+1 >= endOfFile){
              token = new Token(":", filename, lineNum, TokenType.COLON);
              tokenStream.add(token);
            }
            else{
              char nextChar = fileString.charAt(i + 1);
              if(nextChar == ':'){
                token = new Token("::", filename, lineNum, TokenType.FC_HEADER);
                tokenStream.add(token);
                i++;
              } else {
                token = new Token(":", filename, lineNum, TokenType.COLON);
                tokenStream.add(token);
              }
            }
            break;
          case '.':
            if(i+1 >= endOfFile){
              throw new SyntaxException("Invalid token '.'" + ", a digit must follow.", filename, lineNum);
            }
            else{
              i++; // looks ahead one
              ch = fileString.charAt(i);
              if(!Character.isDigit(ch)){
                throw new SyntaxException("Invalid token '"+ch+"' , expected a digit.", filename, lineNum);
              }
              charStream += '.';
              while((Character.isDigit(ch)) && i < endOfFile){
                charStream += ch;
                i++;
                if(i >= endOfFile) break;
                ch = fileString.charAt(i);
              }
              token = new Token(charStream, filename, lineNum, TokenType.NUMBER);
              tokenStream.add(token);
            }
            break;
          case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
            while( (Character.isDigit(ch) || ch == '.') && i < endOfFile){
              if(ch == '.'){
                decimal++;
              }
              if(decimal > 1){
                break;
              }
              charStream = charStream + ch;
              i++;
              if(i >= endOfFile) break;
              ch = fileString.charAt(i);
            }
            if(i < endOfFile) i--;
            token = new Token(charStream, filename, lineNum, TokenType.NUMBER);
            tokenStream.add(token);
            break;
          case '#':
            while(ch != '\n' && i < endOfFile){
              i++;
              if(i >= endOfFile) break;
              ch = fileString.charAt(i);
            }
          case '\n':
            lineNum++;

          case ' ':

          default:
            if(Character.isAlphabetic(ch) && charStream.isEmpty()){
              charStream = charStream + ch;
              i++;
              if(i < endOfFile){
                ch = fileString.charAt(i);
                while( (Character.isAlphabetic(ch) || Character.isDigit(ch)) && i < endOfFile){
                  charStream += ch;
                  i++;
                  if(i >= endOfFile) break;
                  ch = fileString.charAt(i);
                }
                if(i < endOfFile) i--;
              }
              token = new Token(charStream, filename, lineNum, TokenType.ID_KEYWORD);
              tokenStream.add(token);
            }
        }

        if(!(charStream.isEmpty())){
          //resetting state goes here
          charStream = "";
          decimal = 0;
        }

      }

      return tokenStream;

    } catch(IOException ioe){

      //handles IOExce
      System.out.println(ioe.toString());

    } catch(OutOfMemoryError meme){

      //handles MemError
      System.out.println(meme.toString());

    } catch(SecurityException sece){

      //handles SecExe
      System.out.println(sece.toString());
      
    } catch(SyntaxException syne){
      syne.printErrorMessage();
    }

		return null;
	}

}