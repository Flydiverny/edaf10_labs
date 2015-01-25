package expr;

import java.util.Map;
import java.util.Set;

public abstract class BinaryExpr extends Expr {
	private Expr e1, e2;
	
	public BinaryExpr(Expr e1, Expr e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	@Override
	protected void collectVariables(Set<Variable> set) {
		e1.collectVariables(set);
		e2.collectVariables(set);
	}

	@Override
	public boolean value(Map<Variable, Boolean> map) {
		return op(e1.value(map), e2.value(map));
	}

	protected abstract boolean op(boolean v1, boolean v2);
	
	protected abstract String code();
	
	public String toString() {
		return "(" + e1 + " " + code() + " " + e2 + ")"; 
	}
}
