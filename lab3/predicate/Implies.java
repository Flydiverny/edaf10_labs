package predicate;

import term.Term;
import term.Variable;

public class Implies implements Expr {

	private Expr px, py;
	
	public Implies(Expr px, Expr py) {
		this.px = px;
		this.py = py;
	}
	
	public String toString() {
		return px + "->" + py;
	}

	@Override
	public Expr substitute(Variable x, Term term) {
		return new Implies(px.substitute(x, term), py.substitute(x, term));
	}

}
