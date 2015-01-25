package parser;

import java.io.Reader;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import expr.And;
import expr.Expr;
import expr.Implies;
import expr.Not;
import expr.Or;
import expr.Variable;

public class Parser {
    private Scanner scanner;
    private int token;

    public Expr build(Reader reader) {
        scanner = new Scanner(reader);
        token = scanner.nextToken();
        Expr expr = expr();
        if (token == Scanner.EOF) {
            return expr;
        } else {
            throw new ParserException("Trailing garbage after "
                    + scanner.token());
        }
    }

    public Expr build(String input) {
        return build(new StringReader(input));
    }

    private Expr expr() {
        Expr result, primary;
        result = primary();
        
        if(token == Scanner.IMPLIES) {
        	token = scanner.nextToken();
        	primary = primary();
        	result = new Implies(result, primary);
        }
        
        return result;
    }

    private Expr primary() {
        Expr result, term;
        result = term();
        
        while(token == '|') {
        	token = scanner.nextToken();
        	term = term();
        	result = new Or(result, term);
        }
        
        return result;
     }

    private Expr term() {
        Expr result, factor;
        result = factor();
        
        while(token == '&') {
        	token = scanner.nextToken();
        	factor = factor();
        	result = new And(result, factor);
        }
        
        return result;
   }

    private Expr factor() {
    	Expr expr;
    	
        switch (token) {
        case Scanner.VARIABLE:
        	expr = new Variable(scanner.token());
        	token = scanner.nextToken();
        	return expr;
        
        case '!':
        	token = scanner.nextToken();
        	return new Not(factor());

        case '(':
        	token = scanner.nextToken();
        	expr = expr();
        	
	        if (token == ')')
	            token = scanner.nextToken();
            else
            	throw new ParserException("Expecting ')', found: " + scanner.token());
 
        	
        	return expr;
        
        default:
            throw new ParserException("Unexpected " + scanner.token());
        }
    }
}