package expr;

import java.util.Map;
import java.util.Set;

public class Variable extends Expr {
	
	private String name;
	
	public Variable(String name) {
		this.name = name;
	}
	
	public boolean equals(Object o) {
		if(o instanceof Variable) {
			return ((Variable)o).name.equals(this.name);
		}
		
		return false;
	}
	
	public int hashCode() {
		return name.hashCode() + 4;
	}

	@Override
	protected void collectVariables(Set<Variable> set) {
		set.add(this);
	}

	@Override
	public boolean value(Map<Variable, Boolean> map) {
		return map.get(this);
	}

	public String toString() {
		return this.name;
	}
}
