import exceptions.SemanticException;
import exceptions.SyntaxException;
import provided.*;
import treeNodes.ProgramNode;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *  This is the main file to run the program
 * 
 *  @author: Lucie Lim, Dara Prak, Andrew Dantone
 */

public class JottMain {
    public static void main (String[] args) {
        try {
            // STEP 0: validate args.
            if(args.length != 2){ //must have 2 args
                System.err.println("Usage: JottMain FILENAME Language.\n" +
                        "Where:\n" +
                        "FILENAME is the name of the Jott file to be parsed,\n" +
                        "and Language is \"Jott\", \"Java\", \"C\", or \"Python\" depending on which language you want the result in.");
            }
            //must have the 2nd arg be a language we translate to
            else if(!args[1].equals("Jott") && !args[1].equals("Java") && !args[1].equals("C") && !args[1].equals("Python")){
                System.err.println("Usage: JottMain FILENAME Language.\n" +
                        "Where:\n" +
                        "FILENAME is the name of the Jott file to be parsed,\n" +
                        "and Language is \"Jott\", \"Java\", \"C\", or \"Python\" depending on which language you want the result in.");
            }
            else {
                boolean valid = false;
                try{
                    //TODO: make sure this actually checks for the file
                    Path file = Paths.get(args[0]);
                    file = file.toAbsolutePath(); //redundancy in file pathing where CSCI344 repeated
                    valid = true; //if we get here without breaking we are good to run the program
                }
                catch (Exception E){
                    System.err.println("Failed to open " + args[0] + ", See error below.");
                    System.err.println(E.toString());
                    System.err.println("Usage: JottMain FILENAME Language.\n" +
                            "Where:\n" +
                            "FILENAME is the name of the Jott file to be parsed,\n" +
                            "and Language is \"Jott\", \"Java\", \"C\", or \"Python\" depending on which language you want the result in.");
                }
                if(valid) {
                    // STEP 1: Tokenize
                    ArrayList<Token> tokenList = JottTokenizer.tokenize(args[0]);
                    // STEP 2: Parse into jott tree
                    ProgramNode program = ProgramNode.parseProgram(tokenList);
                    // STEP 3: Make the Parse table and validate tree
                    program.validateTree();
                    // STEP 4: Do Phase 4 things.
                }
            }
        } catch (SyntaxException e){
            e.printErrorMessage();
        } catch (SemanticException s){
            s.printErrorMessage();
        } catch (Exception exception) {
            System.err.println(exception.toString());
            exception.printStackTrace();
        }
    }    
}
