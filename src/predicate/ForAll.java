package predicate;

import java.util.HashSet;
import java.util.Set;

import term.Term;
import term.Variable;

public class ForAll implements Expr {

	private Variable x;
	private Expr e;
	
	public ForAll(Variable x, Expr e) {
		this.x = x;
		this.e = e;
	}

	public String toString() {
		return "(\u2200" + x + "." + e + ")";
	}
	
	@Override
	public Expr substitute(Variable y, Term t) {
		if(x.equals(y)) {
			return this;
		}
		
		Set<Variable> set = new HashSet<Variable>();
		t.collectVariables(set);
		
		if(set.contains(x)) {
			Variable x2 = new Variable();
			return new ForAll(x2, e.substitute(x, x2).substitute(y, t));
		}
		
		return new ForAll(x, e.substitute(y, t));
	}

}
