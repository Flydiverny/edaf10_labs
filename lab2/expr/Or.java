package expr;

public class Or extends BinaryExpr {

	public Or(Expr e1, Expr e2) {
		super(e1, e2);
	}

	@Override
	protected boolean op(boolean v1, boolean v2) {
		return v1 || v2;
	}

	@Override
	protected String code() {
		return "||";
	}

}
