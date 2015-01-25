package expr;

import java.util.Map;
import java.util.Set;

public class Not extends Expr {
	private Expr expr;

	public Not(Expr expr) {
		this.expr = expr;
	}

	@Override
	protected void collectVariables(Set<Variable> set) {
		expr.collectVariables(set);
	}

	@Override
	public boolean value(Map<Variable, Boolean> map) {
		return !expr.value(map);
	}
	
	public String toString() {
		return "!" + expr;
	}
}