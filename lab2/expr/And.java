package expr;

public class And extends BinaryExpr {


	public And(Expr e1, Expr e2) {
		super(e1, e2);
	}

	@Override
	protected String code() {
		return "&&";
	}

	@Override
	protected boolean op(boolean v1, boolean v2) {
		return v1 && v2;
	}

}
