package parser;

import expr.And;
import expr.Implies;
import expr.Not;
import expr.Or;
import expr.Variable;

public class SimpleParserTest {
    private Parser parser = new Parser();
    private Variable p = new Variable("p");
    private Variable q = new Variable("q");
    private And andpq = new And(p, q);
    private Or orpq = new Or(p, q);
    private Implies impliespq = new Implies(p, q);

    protected void printBoth(Object expected, Object actual) {
	String expectedString = expected.toString();
	String actualString = actual.toString();
        String message = expectedString.equals(actualString) ? "ok" : "fail";
        System.out.print(message + "\t");
        System.out.print(expected);
        System.out.print("\t\t");
        System.out.println(actual);
    }

    private void shouldFail(String string, String message) {
        try {
            parser.build(string);
            System.out.println("Should throw ParserExption: ");
        } catch (ParserException e) {
            if (e.getMessage().equals(message)) {
                System.out.println("Successful test");
            } else {
                System.out.println("Should throw ParserExption: " + message);
            }
        }
    }

    public void testAll() {
        printBoth(p, parser.build("p"));
        printBoth(p, parser.build("(p)"));
        printBoth(new Not(p), parser.build("!p"));
        printBoth(andpq, parser.build("p&q"));
        printBoth(new And(andpq, p), parser.build("p&q&p"));
        printBoth(new And(p, andpq), parser.build("p&(p&q)"));
        printBoth(orpq, parser.build("p|q"));
        printBoth(new Or(orpq, p), parser.build("p|q|p"));
        printBoth(new Or(p, orpq), parser.build("p|(p|q)"));
        printBoth(new Or(andpq, p), parser.build("p&q|p"));
        printBoth(new Or(p, andpq), parser.build("p|p&q"));
        printBoth(impliespq, parser.build("p->q"));
        shouldFail("p->q->p", "Trailing garbage after ->");
        shouldFail("(p->q", "Expecting \")\", found: EOF");
        shouldFail("#p", "Unexpected #");
    }

    public static void main(String[] args) {
        new SimpleParserTest().testAll();
    }
}
