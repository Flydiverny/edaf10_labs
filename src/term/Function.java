package term;

import java.util.Set;

public class Function implements Term {
    private String name;
    private TermList list;

    public Function(String name, TermList list) {
        this.name = name;
        this.list = list;
    }

    public Set<Variable> collectVariables(Set<Variable> set) {
        return list.collectVariables(set);
    }

    public String toString() {
        return name + list;
    }

	@Override
	public Term substitute(Variable x, Term term) {
		// f(x)[x\f(x)] = f(f(x))

			        
		return new Function(name, this.list.substitute(x, term));
	}
}
