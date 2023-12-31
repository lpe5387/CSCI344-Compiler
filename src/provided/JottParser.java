package provided;

/**
 * This class is responsible for paring Jott Tokens
 * into a Jott parse tree.
 *
 * @author Luka Eaton
 */

import exceptions.SemanticException;
import exceptions.SyntaxException;
import treeNodes.ProgramNode;

import java.util.ArrayList;

public class JottParser {

    /**
     * Parses an ArrayList of Jotton tokens into a Jott Parse Tree.
     * @param tokens the ArrayList of Jott tokens to parse
     * @return the root of the Jott Parse Tree represented by the tokens.
     *         or null upon an error in parsing.
     */
    public static JottTree parse(ArrayList<Token> tokens){
        try{
            return ProgramNode.parseProgram(tokens);
        }
        catch(SyntaxException syne){
            syne.printErrorMessage();
        } catch (SemanticException e) {
            e.printErrorMessage();
        }
        return null;
    }
}
