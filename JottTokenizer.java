

/**
 * This class is responsible for tokenizing Jott code.
 * 
 * @author Issac Kim
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

      //goes through whole file string
      for ( int i = 0; i < endOfFile; i++ ) {

        char ch = fileString.charAt(i);

        switch(ch){
          case ',':
            Token token = (charStream == "") ? new Token(",", filename, lineNum, TokenType.COMMA) : new Token(charStream, filename, lineNum, type);
            tokenStream.add(token);
            if(charStream != ""){
              token = new Token(",", filename, lineNum, TokenType.COMMA);
              tokenStream.add(token);
            }
          case '[':
            token = (charStream == "") ? new Token("[", filename, lineNum, TokenType.L_BRACKET) : new Token(charStream, filename, lineNum, type);
            tokenStream.add(token);
            if(charStream != ""){
              token = new Token("[", filename, lineNum, TokenType.L_BRACKET);
              tokenStream.add(token);
            }
          case ']':
            token = (charStream == "") ? new Token("]", filename, lineNum, TokenType.R_BRACKET) : new Token(charStream, filename, lineNum, type);
            tokenStream.add(token);
            if(charStream != ""){
              token = new Token("]", filename, lineNum, TokenType.R_BRACKET);
              tokenStream.add(token);
            }
          case '{':
            token = (charStream == "") ? new Token("{", filename, lineNum, TokenType.L_BRACE) : new Token(charStream, filename, lineNum, type);
            tokenStream.add(token);
            if(charStream != ""){
              token = new Token("{", filename, lineNum, TokenType.L_BRACE);
              tokenStream.add(token);
            }        
          case '}':
            token = (charStream == "") ? new Token("}", filename, lineNum, TokenType.R_BRACE) : new Token(charStream, filename, lineNum, type);
            tokenStream.add(token);
            if(charStream != ""){
              token = new Token("}", filename, lineNum, TokenType.R_BRACE);
              tokenStream.add(token);
            }
          case '=':

          case '<':

          case '>':

          case '/':

          case '+':

          case '-':

          case '*':

          case ';':

          case '.':

          case '0':

          case '1':

          case '2':

          case '3':

          case '4':

          case '5': 

          case '6':

          case '7':

          case '8':

          case '9':

          case '#':
            while(ch != '\n' && i < endOfFile){
              i++;
              ch = fileString.charAt(i);
            }
          case '\n':
            lineNum++;



        }

        if(ch == '\n'){

          lineNum++;
          continue;

        }

        System.out.println(ch);
        //branches from DFA
          
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