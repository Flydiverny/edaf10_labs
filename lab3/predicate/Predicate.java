package predicate;

import term.Term;
import term.TermList;
import term.Variable;

public class Predicate implements Expr {

	private String name;
	private TermList list;
	
	public Predicate(String name, TermList list) {
		this.name = name;
		this.list = list;
	}
	
	public String toString() {
        return name + list;
    }

	@Override
	public Expr substitute(Variable variable, Term term) {
		
		// P(x)[x\y] = P(y)
		
		return new Predicate(name, list.substitute(variable, term));
	}

}
