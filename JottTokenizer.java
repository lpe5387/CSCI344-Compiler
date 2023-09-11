

/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author Issac Kim, lucie lim, Dara Prak, Andrew Dantone
 **/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JottTokenizer {

  /**
   * java JottTokenizer.java Token.java [Insert file path to testcase]
   * 
   * @param args
   */
  public static void main(String[] args){
    tokenize(args[1]);
  }

	/**
   * Takes in a filename and tokenizes that file into Tokens
   * based on the rules of the Jott Language
   * @param filename the name of the file to tokenize; can be relative or absolute path
   * @return an ArrayList of Jott Tokens
   */
  public static ArrayList<Token> tokenize(String filename){

    ArrayList<Token> tokenStream = new ArrayList<Token>();

    //turns String filename into Path file
    Path file = Paths.get(filename);
    file = file.toAbsolutePath(); //redundancy in file pathing where CSCI344 repeated

    try{
      file = file.toRealPath();
      //turns file into a string
      String fileString = Files.readString(file);
      String charStream = "";
      TokenType type = TokenType.ASSIGN; //placeholder to initalize variable
      int lineNum = 1;

      int endOfFile = fileString.length();
      int decimal = 0;

      //goes through whole file string
      for ( int i = 0; i < endOfFile; i++ ) {

        char ch = fileString.charAt(i);

        switch(ch){
          /**
           * Initial case:
           *  charStream = ""
           *  decimal = 0
           */
          case ',':
            charStream = charStream + ",";
            Token token = new Token(charStream, filename, lineNum, TokenType.COMMA);
            tokenStream.add(token);
          case '[':
            charStream = charStream + "[";
            token = new Token(charStream, filename, lineNum, TokenType.L_BRACKET);
            tokenStream.add(token);
          case ']':
            charStream = charStream + "]";          
            token = new Token(charStream, filename, lineNum, TokenType.R_BRACKET);
            tokenStream.add(token);
          case '{':
            charStream = charStream + "{";
            token = new Token(charStream, filename, lineNum, TokenType.L_BRACE);
            tokenStream.add(token);       
          case '}':
            charStream = charStream + "}";
            token = new Token(charStream, filename, lineNum, TokenType.R_BRACE);
            tokenStream.add(token);
          case '=':
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
            //clean up char stream
            charStream = "";
          case '<':
            nextChar = fileString.charAt(i+1);
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
            //clean up char stream 
            charStream = "";

          case '>':
            nextChar = fileString.charAt(i+1);
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
            //clean up char stream 
            charStream = "";
          case '/':
            token = new Token("/", filename, lineNum, TokenType.MATH_OP);
            tokenStream.add(token);
          case '+':
            token = new Token("+", filename, lineNum, TokenType.MATH_OP);
            tokenStream.add(token);
          case '-':
            token = new Token("-", filename, lineNum, TokenType.MATH_OP);
            tokenStream.add(token);
          case '*':
            token = new Token("*", filename, lineNum, TokenType.MATH_OP);
            tokenStream.add(token);
          case '!':
            //check next char for an =
            char nextChar = fileString.charAt(i + 1); 
            if (nextChar == '='){ //if so make it a token
              token = new Token("!=", filename, lineNum, TokenType.REL_OP);
              tokenStream.add(token);
              i++;
            }
            else{//otherwise its an error as an ! can only be followed by an =
              //TODO error out of here and break
            };

          case: '"':
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
              i += x;
            }
            else{ //if we end with anything else error, if we reach here with a num, char, or space something has gone very wrong
              //error out and break
            };

          case ';':
            charStream += ";";
            token = new Token(charStream, filename, lineNum, TokenType.SEMICOLON);
            tokenStream.add(token);
          case ':':
            charStream += ":";
            if(type == TokenType.COLON){
              
            }
            char oneAhead = fileString.charAt(i + 1);
            if(oneAhead == ':'){
              type = TokenType.FC_HEADER;
            } else {
              type = TokenType.COLON;
            }
          case '.':
            oneAhead = fileString.charAt(i + 1);
            if(Character.isDigit(oneAhead)){
              charStream += ".";
            }
          case '0':
            while( (Character.isDigit(ch) || ch == '.') && i < endOfFile){
              if(ch == '.'){
                decimal++;
              }
              if(decimal > 1){
                break;
              }
              charStream = charStream + ch;
              i++;
              ch = fileString.charAt(i);
            }
            token = new Token(charStream, filename, lineNum, TokenType.NUMBER);
            tokenStream.add(token);
          case '1':
            while( (Character.isDigit(ch) || ch == '.') && i < endOfFile){
              if(ch == '.'){
                decimal++;
              }
              if(decimal > 1){
                break;
              }
              charStream = charStream + ch;
              i++;
              ch = fileString.charAt(i);
            }
            token = new Token(charStream, filename, lineNum, TokenType.NUMBER);
            tokenStream.add(token);
          case '2':
            while( (Character.isDigit(ch) || ch == '.') && i < endOfFile){
              if(ch == '.'){
                decimal++;
              }
              if(decimal > 1){
                break;
              }
              charStream = charStream + ch;
              i++;
              ch = fileString.charAt(i);
            }
            token = new Token(charStream, filename, lineNum, TokenType.NUMBER);
            tokenStream.add(token);
          case '3':
            while( (Character.isDigit(ch) || ch == '.') && i < endOfFile){
              if(ch == '.'){
                decimal++;
              }
              if(decimal > 1){
                break;
              }
              charStream = charStream + ch;
              i++;
              ch = fileString.charAt(i);
            }
            token = new Token(charStream, filename, lineNum, TokenType.NUMBER);
            tokenStream.add(token);
          case '4':
            while( (Character.isDigit(ch) || ch == '.') && i < endOfFile){
              if(ch == '.'){
                decimal++;
              }
              if(decimal > 1){
                break;
              }
              charStream = charStream + ch;
              i++;
              ch = fileString.charAt(i);
            }
            token = new Token(charStream, filename, lineNum, TokenType.NUMBER);
            tokenStream.add(token);
          case '5':
            while( (Character.isDigit(ch) || ch == '.') && i < endOfFile){
              if(ch == '.'){
                decimal++;
              }
              if(decimal > 1){
                break;
              }
              charStream = charStream + ch;
              i++;
              ch = fileString.charAt(i);
            }
            token = new Token(charStream, filename, lineNum, TokenType.NUMBER);
            tokenStream.add(token);
          case '6':
            while( (Character.isDigit(ch) || ch == '.') && i < endOfFile){
              if(ch == '.'){
                decimal++;
              }
              if(decimal > 1){
                break;
              }
              charStream = charStream + ch;
              i++;
              ch = fileString.charAt(i);
            }
            token = new Token(charStream, filename, lineNum, TokenType.NUMBER);
            tokenStream.add(token);
          case '7':
            while( (Character.isDigit(ch) || ch == '.') && i < endOfFile){
              if(ch == '.'){
                decimal++;
              }
              if(decimal > 1){
                break;
              }
              charStream = charStream + ch;
              i++;
              ch = fileString.charAt(i);
            }
            token = new Token(charStream, filename, lineNum, TokenType.NUMBER);
            tokenStream.add(token);
          case '8':
            while( (Character.isDigit(ch) || ch == '.') && i < endOfFile){
              if(ch == '.'){
                decimal++;
              }
              if(decimal > 1){
                break;
              }
              charStream = charStream + ch;
              i++;
              ch = fileString.charAt(i);
            }
            token = new Token(charStream, filename, lineNum, TokenType.NUMBER);
            tokenStream.add(token);
          case '9':
            while( (Character.isDigit(ch) || ch == '.') && i < endOfFile){
              if(ch == '.'){
                decimal++;
              }
              if(decimal > 1){
                break;
              }
              charStream = charStream + ch;
              i++;
              ch = fileString.charAt(i);
            }
            token = new Token(charStream, filename, lineNum, TokenType.NUMBER);
          case '#':
            while(ch != '\n' && i < endOfFile){
              i++;
              ch = fileString.charAt(i);
            }
          case '\n':
            lineNum++;

          case ' ':

          default:
            if(Character.isAlphabetic(ch) && charStream == ""){
              charStream = charStream + ch;
              i++;
              ch = fileString.charAt(i);
              while( (Character.isAlphabetic(ch) || Character.isDigit(ch)) && i < endOfFile){
                charStream = charStream + ch;
                i++;
                ch = fileString.charAt(i);
              }
              token = new Token(charStream, filename, lineNum, TokenType.ID_KEYWORD);
              tokenStream.add(token);
            }
            if(charStream != ""){
              //resetting state goes here
              charStream = "";
              decimal = 0;
            }
        }
          
      }

    } catch(IOException ioe){

      //handles IOExce
      System.out.println(ioe.toString());

    } catch(OutOfMemoryError meme){

      //handles MemError
      System.out.println(meme.toString());

    } catch(SecurityException sece){

      //handles SecExe
      System.out.println(sece.toString());

    }

		return null;
	}

}