import exceptions.SemanticException;
import exceptions.SyntaxException;
import provided.*;
import treeNodes.ProgramNode;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
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
            if(args.length != 3){ //must have 3 args
                System.err.println("Usage: JottMain INPUTFILENAME OUTPUTFILENAME Language.\n" +
                        "Where:\n" +
                        "INPUTFILENAME is the name of the Jott file to be parsed,\n" +
                        "OUTPUTFILENAME is the name of the output file to be written into\n" +
                        "and Language is \"Jott\", \"Java\", \"C\", or \"Python\" depending on which language you want the result in.");
            }
            //must have the 3nd arg be a language we translate to
            else if(!args[2].equals("Jott") && !args[2].equals("Java") && !args[2].equals("C") && !args[2].equals("Python")){
                System.err.println("Usage: JottMain INPUTFILENAME OUTPUTFILENAME Language.\n" +
                        "Where:\n" +
                        "INPUTFILENAME is the name of the Jott file to be parsed,\n" +
                        "OUTPUTFILENAME is the name of the output file to be written into\n" +
                        "and Language is \"Jott\", \"Java\", \"C\", or \"Python\" depending on which language you want the result in.");
            }
            else if(args[0].equals(args[1])){
                System.err.println("INPUTFILENAME and OUTPUTFILENAME cannot be the same\n" +
                        "Usage: JottMain INPUTFILENAME OUTPUTFILENAME Language.\n" +
                        "Where:\n" +
                        "INPUTFILENAME is the name of the Jott file to be parsed,\n" +
                        "OUTPUTFILENAME is the name of the output file to be written into\n" +
                        "and Language is \"Jott\", \"Java\", \"C\", or \"Python\" depending on which language you want the result in.");
            }
            else {
                Boolean exception = false;
                boolean existsInput = false;
                boolean existsOutput = false;
                try{
                    File fileInput = new File(args[0]);
                    File fileOutput = new File(args[1]);
                    existsInput = fileInput.isFile();
                    existsOutput = fileOutput.isFile();
                }
                catch (Exception E){
                    exception = true;
                    System.err.println("Failed to open " + args[0] + "or " + args[1] + ", See error below.");
                    System.err.println(E.toString());
                    System.err.println("Usage: JottMain INPUTFILENAME OUTPUTFILENAME Language.\n" +
                            "Where:\n" +
                            "INPUTFILENAME is the name of the Jott file to be parsed,\n" +
                            "OUTPUTFILENAME is the name of the output file to be written into\n" +
                            "and Language is \"Jott\", \"Java\", \"C\", or \"Python\" depending on which language you want the result in.");
                }
                if(existsInput && existsOutput && !exception) {
                    // STEP 1: Tokenize
                    ArrayList<Token> tokenList = JottTokenizer.tokenize(args[0]);
                    // STEP 2: Parse into jott tree
                    ProgramNode program = ProgramNode.parseProgram(tokenList);
                    // STEP 3: Make the Parse table and validate tree
                    program.validateTree();
                    // STEP 4: Do Phase 4 things.
                    try {
                        FileWriter writer = new FileWriter(args[1]);
                        if (args[2].equals("Jott")) {
                            writer.write(program.convertToJott());
                        } else if (args[2].equals("Java")) {
                            writer.write(program.convertToJava(args[0])); //this might be wrong idk, that's not this phase
                        } else if (args[2].equals("C")) {
                            writer.write(program.convertToC());
                        } else if (args[2].equals("Python")) {
                            writer.write(program.convertToPython());
                        }
                        writer.close();
                    }
                    catch (IOException e){
                        System.err.println("There was a problem with writing to the file: " + args[1]);
                        System.err.println("Usage: JottMain INPUTFILENAME OUTPUTFILENAME Language.\n" +
                                "Where:\n" +
                                "INPUTFILENAME is the name of the Jott file to be parsed,\n" +
                                "OUTPUTFILENAME is the name of the output file to be written into\n" +
                                "and Language is \"Jott\", \"Java\", \"C\", or \"Python\" depending on which language you want the result in.");
                    }
                }
                else if(existsInput && !existsOutput && !exception) {
                    // STEP 1: Tokenize
                    ArrayList<Token> tokenList = JottTokenizer.tokenize(args[0]);
                    // STEP 2: Parse into jott tree
                    ProgramNode program = ProgramNode.parseProgram(tokenList);
                    // STEP 3: Make the Parse table and validate tree
                    program.validateTree();
                    // STEP 4: Do Phase 4 things.
                    // TODO: create the file in the new directory
                    try {
                        FileWriter writer = new FileWriter(args[1]);
                        if (args[2].equals("Jott")) {
                            writer.write(program.convertToJott());
                        } else if (args[2].equals("Java")) {
                            writer.write(program.convertToJava(args[0])); //this might be wrong idk, that's not this phase
                        } else if (args[2].equals("C")) {
                            writer.write(program.convertToC());
                        } else if (args[2].equals("Python")) {
                            writer.write(program.convertToPython());
                        }
                        writer.close();
                    }
                    catch (IOException e){
                        System.err.println("There was a problem with writing to the file: " + args[1]);
                        System.err.println("Usage: JottMain INPUTFILENAME OUTPUTFILENAME Language.\n" +
                                "Where:\n" +
                                "INPUTFILENAME is the name of the Jott file to be parsed,\n" +
                                "OUTPUTFILENAME is the name of the output file to be written into\n" +
                                "and Language is \"Jott\", \"Java\", \"C\", or \"Python\" depending on which language you want the result in.");
                    }
                }
                else{
                    System.err.println("File " + args[0] + " does not exist.");
                    System.err.println("Usage: JottMain INPUTFILENAME OUTPUTFILENAME Language.\n" +
                            "Where:\n" +
                            "INPUTFILENAME is the name of the Jott file to be parsed,\n" +
                            "OUTPUTFILENAME is the name of the output file to be written into\n" +
                            "and Language is \"Jott\", \"Java\", \"C\", or \"Python\" depending on which language you want the result in.");
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
