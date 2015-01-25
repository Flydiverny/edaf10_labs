package test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import expr.And;
import expr.Equal;
import expr.Expr;
import expr.Implication;
import expr.Not;
import expr.Or;
import expr.Variable;

public class TestExpr {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void andWorks() {
		Variable q = new Variable("q");
		Variable p = new Variable("p");
		
		Expr expr = new And(p, q);
		
		Map<Variable,Boolean> map = new HashMap<Variable, Boolean>();
		map.put(q, true);
		map.put(p, true);
		assertTrue(expr.value(map));
		
		map.put(q, false);
		map.put(p, false);
		assertFalse(expr.value(map));
		
		map.put(q, true);
		map.put(p, false);
		assertFalse(expr.value(map));
		
		map.put(q, false);
		map.put(p, true);
		assertFalse(expr.value(map));
	}
	
	@Test
	public void equalWorks() {
		Variable q = new Variable("q");
		Variable p = new Variable("p");
		
		Expr expr = new Equal(p, q);
		
		Map<Variable,Boolean> map = new HashMap<Variable, Boolean>();
		map.put(q, true);
		map.put(p, true);
		assertTrue(expr.value(map));
		
		map.put(q, false);
		map.put(p, false);
		assertTrue(expr.value(map));
		
		map.put(q, true);
		map.put(p, false);
		assertFalse(expr.value(map));
		
		map.put(q, false);
		map.put(p, true);
		assertFalse(expr.value(map));
	}
	
	@Test
	public void orWorks() {
		Variable q = new Variable("q");
		Variable p = new Variable("p");
		
		Expr expr = new Or(p, q);
		
		Map<Variable,Boolean> map = new HashMap<Variable, Boolean>();
		map.put(q, true);
		map.put(p, true);
		assertTrue(expr.value(map));
		
		map.put(q, false);
		map.put(p, false);
		assertFalse(expr.value(map));
		
		map.put(q, true);
		map.put(p, false);
		assertTrue(expr.value(map));
		
		map.put(q, false);
		map.put(p, true);
		assertTrue(expr.value(map));
	}
	
	@Test
	public void notWorks() {
		Variable p = new Variable("p");
		
		Expr expr = new Not(p);
		
		Map<Variable,Boolean> map = new HashMap<Variable, Boolean>();
		map.put(p, true);
		assertFalse(expr.value(map));
		map.put(p, false);
		assertTrue(expr.value(map));
	}
	
	@Test
	public void implicationWorks() {
		Variable q = new Variable("q");
		Variable p = new Variable("p");
		
		Expr expr = new Implication(q, p);
		
		Map<Variable,Boolean> map = new HashMap<Variable, Boolean>();
		map.put(q, true);
		map.put(p, true);
		assertTrue(expr.value(map));
		
		map.put(q, false);
		map.put(p, false);
		assertTrue(expr.value(map));
		
		map.put(q, true);
		map.put(p, false);
		assertFalse(expr.value(map));
		
		map.put(q, false);
		map.put(p, true);
		assertTrue(expr.value(map));
	}
	
	@Test
	public void simpleTest() {
		Variable q = new Variable("q");
		Variable p = new Variable("p");
		
		Expr expr = new Not(new And(q, p));
		System.out.println("simpleTest: " + expr);
		
		assertFalse(expr.isTautology());
	}
	
	@Test
	public void kebabTest() {
		Variable p = new Variable("p");
		Variable q = new Variable("q");
		Variable a = new Variable("a");
		
		Expr expr = new Implication(new Not(p), new Or(new And(q, p), a));
		
		System.out.println("kebabTest: " + expr);
		assertFalse(expr.isTautology());
	}
	
	@Test
	public void tautologyTest() {
		Variable p = new Variable("p");
		Variable q = new Variable("q");
		
		Expr expr = new Implication(new Implication(new Not(p), new Not(q)), new Implication(new Implication(new Not(p), q), p));
		System.out.println("tautologyTest: " + expr);
		assertTrue(expr.isTautology());
	}


}
