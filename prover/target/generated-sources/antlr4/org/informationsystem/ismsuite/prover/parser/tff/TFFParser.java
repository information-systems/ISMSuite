// Generated from org\informationsystem\ismsuite\prover\parser\tff\TFF.g4 by ANTLR 4.7.2
package org.informationsystem.ismsuite.prover.parser.tff;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TFFParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, Or=9, 
		And=10, Iff=11, Impl=12, If=13, Niff=14, Nor=15, Nand=16, Not=17, ForallComb=18, 
		TyForall=19, Infix_inequality=20, Infix_equality=21, Forall=22, ExistsComb=23, 
		TyExists=24, Exists=25, Lambda=26, ChoiceComb=27, Choice=28, DescriptionComb=29, 
		Description=30, EqComb=31, App=32, Assignment=33, Arrow=34, Star=35, Plus=36, 
		Subtype_sign=37, Gentzen_arrow=38, Real=39, Signed_real=40, Unsigned_real=41, 
		Rational=42, Signed_rational=43, Unsigned_rational=44, Integer=45, Signed_integer=46, 
		Unsigned_integer=47, Decimal=48, Positive_decimal=49, Decimal_exponent=50, 
		Decimal_fraction=51, Dot_decimal=52, Exp_integer=53, Signed_exp_integer=54, 
		Unsigned_exp_integer=55, Dollar_word=56, Dollar_dollar_word=57, Upper_word=58, 
		Lower_word=59, Single_quoted=60, Distinct_object=61, WS=62, Line_comment=63, 
		Block_comment=64;
	public static final int
		RULE_fof_quantifier = 0, RULE_binary_connective = 1, RULE_assoc_connective = 2, 
		RULE_unary_connective = 3, RULE_tff_file = 4, RULE_tff_line = 5, RULE_formula_role = 6, 
		RULE_tff_formula = 7, RULE_tff_logic_formula = 8, RULE_tff_binary_formula = 9, 
		RULE_tff_binary_nonassoc = 10, RULE_tff_binary_assoc = 11, RULE_tff_or_formula = 12, 
		RULE_tff_and_formula = 13, RULE_tff_unitary_formula = 14, RULE_tff_quantified_formula = 15, 
		RULE_tff_unary_formula = 16, RULE_tff_atomic_formula = 17, RULE_fof_infix_unary = 18, 
		RULE_fof_term = 19, RULE_argument_list = 20, RULE_argument = 21, RULE_tff_typed_atom = 22, 
		RULE_variable = 23, RULE_variable_list = 24, RULE_name = 25, RULE_atomic_word_list = 26, 
		RULE_atomic_word = 27, RULE_atomic_defined_word = 28, RULE_atomic_system_word = 29, 
		RULE_number = 30, RULE_file_name = 31;
	private static String[] makeRuleNames() {
		return new String[] {
			"fof_quantifier", "binary_connective", "assoc_connective", "unary_connective", 
			"tff_file", "tff_line", "formula_role", "tff_formula", "tff_logic_formula", 
			"tff_binary_formula", "tff_binary_nonassoc", "tff_binary_assoc", "tff_or_formula", 
			"tff_and_formula", "tff_unitary_formula", "tff_quantified_formula", "tff_unary_formula", 
			"tff_atomic_formula", "fof_infix_unary", "fof_term", "argument_list", 
			"argument", "tff_typed_atom", "variable", "variable_list", "name", "atomic_word_list", 
			"atomic_word", "atomic_defined_word", "atomic_system_word", "number", 
			"file_name"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'tff'", "'('", "','", "').'", "'['", "']'", "':'", "')'", "'|'", 
			"'&'", "'<=>'", "'=>'", "'<='", "'<~>'", "'~|'", "'~&'", "'~'", "'!!'", 
			"'!>'", "'!='", "'='", "'!'", "'??'", "'?*'", "'?'", "'^'", "'@@+'", 
			"'@+'", "'@@-'", "'@-'", "'@='", "'@'", "':='", "'>'", "'*'", "'+'", 
			"'<<'", "'-->'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "Or", "And", "Iff", 
			"Impl", "If", "Niff", "Nor", "Nand", "Not", "ForallComb", "TyForall", 
			"Infix_inequality", "Infix_equality", "Forall", "ExistsComb", "TyExists", 
			"Exists", "Lambda", "ChoiceComb", "Choice", "DescriptionComb", "Description", 
			"EqComb", "App", "Assignment", "Arrow", "Star", "Plus", "Subtype_sign", 
			"Gentzen_arrow", "Real", "Signed_real", "Unsigned_real", "Rational", 
			"Signed_rational", "Unsigned_rational", "Integer", "Signed_integer", 
			"Unsigned_integer", "Decimal", "Positive_decimal", "Decimal_exponent", 
			"Decimal_fraction", "Dot_decimal", "Exp_integer", "Signed_exp_integer", 
			"Unsigned_exp_integer", "Dollar_word", "Dollar_dollar_word", "Upper_word", 
			"Lower_word", "Single_quoted", "Distinct_object", "WS", "Line_comment", 
			"Block_comment"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "TFF.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TFFParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class Fof_quantifierContext extends ParserRuleContext {
		public TerminalNode Forall() { return getToken(TFFParser.Forall, 0); }
		public TerminalNode Exists() { return getToken(TFFParser.Exists, 0); }
		public Fof_quantifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fof_quantifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitFof_quantifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Fof_quantifierContext fof_quantifier() throws RecognitionException {
		Fof_quantifierContext _localctx = new Fof_quantifierContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_fof_quantifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			_la = _input.LA(1);
			if ( !(_la==Forall || _la==Exists) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Binary_connectiveContext extends ParserRuleContext {
		public TerminalNode Iff() { return getToken(TFFParser.Iff, 0); }
		public TerminalNode Impl() { return getToken(TFFParser.Impl, 0); }
		public TerminalNode If() { return getToken(TFFParser.If, 0); }
		public Binary_connectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_binary_connective; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitBinary_connective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Binary_connectiveContext binary_connective() throws RecognitionException {
		Binary_connectiveContext _localctx = new Binary_connectiveContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_binary_connective);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Iff) | (1L << Impl) | (1L << If))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Assoc_connectiveContext extends ParserRuleContext {
		public TerminalNode Or() { return getToken(TFFParser.Or, 0); }
		public TerminalNode And() { return getToken(TFFParser.And, 0); }
		public Assoc_connectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assoc_connective; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitAssoc_connective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Assoc_connectiveContext assoc_connective() throws RecognitionException {
		Assoc_connectiveContext _localctx = new Assoc_connectiveContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_assoc_connective);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			_la = _input.LA(1);
			if ( !(_la==Or || _la==And) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Unary_connectiveContext extends ParserRuleContext {
		public TerminalNode Not() { return getToken(TFFParser.Not, 0); }
		public Unary_connectiveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unary_connective; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitUnary_connective(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Unary_connectiveContext unary_connective() throws RecognitionException {
		Unary_connectiveContext _localctx = new Unary_connectiveContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_unary_connective);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(Not);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tff_fileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(TFFParser.EOF, 0); }
		public List<Tff_lineContext> tff_line() {
			return getRuleContexts(Tff_lineContext.class);
		}
		public Tff_lineContext tff_line(int i) {
			return getRuleContext(Tff_lineContext.class,i);
		}
		public Tff_fileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tff_file; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitTff_file(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tff_fileContext tff_file() throws RecognitionException {
		Tff_fileContext _localctx = new Tff_fileContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_tff_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(72);
				tff_line();
				}
				}
				setState(77);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(78);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tff_lineContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Formula_roleContext formula_role() {
			return getRuleContext(Formula_roleContext.class,0);
		}
		public Tff_formulaContext tff_formula() {
			return getRuleContext(Tff_formulaContext.class,0);
		}
		public Tff_lineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tff_line; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitTff_line(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tff_lineContext tff_line() throws RecognitionException {
		Tff_lineContext _localctx = new Tff_lineContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_tff_line);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__0);
			setState(81);
			match(T__1);
			setState(82);
			name();
			setState(83);
			match(T__2);
			setState(84);
			formula_role();
			setState(85);
			match(T__2);
			setState(86);
			tff_formula();
			setState(87);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Formula_roleContext extends ParserRuleContext {
		public TerminalNode Lower_word() { return getToken(TFFParser.Lower_word, 0); }
		public Formula_roleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formula_role; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitFormula_role(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Formula_roleContext formula_role() throws RecognitionException {
		Formula_roleContext _localctx = new Formula_roleContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_formula_role);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			match(Lower_word);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tff_formulaContext extends ParserRuleContext {
		public Tff_logic_formulaContext tff_logic_formula() {
			return getRuleContext(Tff_logic_formulaContext.class,0);
		}
		public Tff_typed_atomContext tff_typed_atom() {
			return getRuleContext(Tff_typed_atomContext.class,0);
		}
		public Tff_formulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tff_formula; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitTff_formula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tff_formulaContext tff_formula() throws RecognitionException {
		Tff_formulaContext _localctx = new Tff_formulaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_tff_formula);
		try {
			setState(93);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				tff_logic_formula();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				tff_typed_atom();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tff_logic_formulaContext extends ParserRuleContext {
		public Tff_binary_formulaContext tff_binary_formula() {
			return getRuleContext(Tff_binary_formulaContext.class,0);
		}
		public Tff_unitary_formulaContext tff_unitary_formula() {
			return getRuleContext(Tff_unitary_formulaContext.class,0);
		}
		public Tff_logic_formulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tff_logic_formula; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitTff_logic_formula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tff_logic_formulaContext tff_logic_formula() throws RecognitionException {
		Tff_logic_formulaContext _localctx = new Tff_logic_formulaContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_tff_logic_formula);
		try {
			setState(97);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				tff_binary_formula();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(96);
				tff_unitary_formula();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tff_binary_formulaContext extends ParserRuleContext {
		public Tff_binary_nonassocContext tff_binary_nonassoc() {
			return getRuleContext(Tff_binary_nonassocContext.class,0);
		}
		public Tff_binary_assocContext tff_binary_assoc() {
			return getRuleContext(Tff_binary_assocContext.class,0);
		}
		public Tff_binary_formulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tff_binary_formula; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitTff_binary_formula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tff_binary_formulaContext tff_binary_formula() throws RecognitionException {
		Tff_binary_formulaContext _localctx = new Tff_binary_formulaContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_tff_binary_formula);
		try {
			setState(101);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(99);
				tff_binary_nonassoc();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(100);
				tff_binary_assoc();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tff_binary_nonassocContext extends ParserRuleContext {
		public List<Tff_unitary_formulaContext> tff_unitary_formula() {
			return getRuleContexts(Tff_unitary_formulaContext.class);
		}
		public Tff_unitary_formulaContext tff_unitary_formula(int i) {
			return getRuleContext(Tff_unitary_formulaContext.class,i);
		}
		public Binary_connectiveContext binary_connective() {
			return getRuleContext(Binary_connectiveContext.class,0);
		}
		public Tff_binary_nonassocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tff_binary_nonassoc; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitTff_binary_nonassoc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tff_binary_nonassocContext tff_binary_nonassoc() throws RecognitionException {
		Tff_binary_nonassocContext _localctx = new Tff_binary_nonassocContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_tff_binary_nonassoc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			tff_unitary_formula();
			setState(104);
			binary_connective();
			setState(105);
			tff_unitary_formula();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tff_binary_assocContext extends ParserRuleContext {
		public Tff_or_formulaContext tff_or_formula() {
			return getRuleContext(Tff_or_formulaContext.class,0);
		}
		public Tff_and_formulaContext tff_and_formula() {
			return getRuleContext(Tff_and_formulaContext.class,0);
		}
		public Tff_binary_assocContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tff_binary_assoc; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitTff_binary_assoc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tff_binary_assocContext tff_binary_assoc() throws RecognitionException {
		Tff_binary_assocContext _localctx = new Tff_binary_assocContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_tff_binary_assoc);
		try {
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				tff_or_formula(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				tff_and_formula(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tff_or_formulaContext extends ParserRuleContext {
		public List<Tff_unitary_formulaContext> tff_unitary_formula() {
			return getRuleContexts(Tff_unitary_formulaContext.class);
		}
		public Tff_unitary_formulaContext tff_unitary_formula(int i) {
			return getRuleContext(Tff_unitary_formulaContext.class,i);
		}
		public TerminalNode Or() { return getToken(TFFParser.Or, 0); }
		public Tff_or_formulaContext tff_or_formula() {
			return getRuleContext(Tff_or_formulaContext.class,0);
		}
		public Tff_or_formulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tff_or_formula; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitTff_or_formula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tff_or_formulaContext tff_or_formula() throws RecognitionException {
		return tff_or_formula(0);
	}

	private Tff_or_formulaContext tff_or_formula(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Tff_or_formulaContext _localctx = new Tff_or_formulaContext(_ctx, _parentState);
		Tff_or_formulaContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_tff_or_formula, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(112);
			tff_unitary_formula();
			setState(113);
			match(Or);
			setState(114);
			tff_unitary_formula();
			}
			_ctx.stop = _input.LT(-1);
			setState(121);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Tff_or_formulaContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_tff_or_formula);
					setState(116);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(117);
					match(Or);
					setState(118);
					tff_unitary_formula();
					}
					} 
				}
				setState(123);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Tff_and_formulaContext extends ParserRuleContext {
		public List<Tff_unitary_formulaContext> tff_unitary_formula() {
			return getRuleContexts(Tff_unitary_formulaContext.class);
		}
		public Tff_unitary_formulaContext tff_unitary_formula(int i) {
			return getRuleContext(Tff_unitary_formulaContext.class,i);
		}
		public TerminalNode And() { return getToken(TFFParser.And, 0); }
		public Tff_and_formulaContext tff_and_formula() {
			return getRuleContext(Tff_and_formulaContext.class,0);
		}
		public Tff_and_formulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tff_and_formula; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitTff_and_formula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tff_and_formulaContext tff_and_formula() throws RecognitionException {
		return tff_and_formula(0);
	}

	private Tff_and_formulaContext tff_and_formula(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Tff_and_formulaContext _localctx = new Tff_and_formulaContext(_ctx, _parentState);
		Tff_and_formulaContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_tff_and_formula, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(125);
			tff_unitary_formula();
			setState(126);
			match(And);
			setState(127);
			tff_unitary_formula();
			}
			_ctx.stop = _input.LT(-1);
			setState(134);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Tff_and_formulaContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_tff_and_formula);
					setState(129);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(130);
					match(And);
					setState(131);
					tff_unitary_formula();
					}
					} 
				}
				setState(136);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Tff_unitary_formulaContext extends ParserRuleContext {
		public Tff_quantified_formulaContext tff_quantified_formula() {
			return getRuleContext(Tff_quantified_formulaContext.class,0);
		}
		public Tff_unary_formulaContext tff_unary_formula() {
			return getRuleContext(Tff_unary_formulaContext.class,0);
		}
		public Tff_atomic_formulaContext tff_atomic_formula() {
			return getRuleContext(Tff_atomic_formulaContext.class,0);
		}
		public Tff_unitary_formulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tff_unitary_formula; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitTff_unitary_formula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tff_unitary_formulaContext tff_unitary_formula() throws RecognitionException {
		Tff_unitary_formulaContext _localctx = new Tff_unitary_formulaContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_tff_unitary_formula);
		try {
			setState(140);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(137);
				tff_quantified_formula();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(138);
				tff_unary_formula();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(139);
				tff_atomic_formula();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tff_quantified_formulaContext extends ParserRuleContext {
		public Fof_quantifierContext fof_quantifier() {
			return getRuleContext(Fof_quantifierContext.class,0);
		}
		public Variable_listContext variable_list() {
			return getRuleContext(Variable_listContext.class,0);
		}
		public Tff_unitary_formulaContext tff_unitary_formula() {
			return getRuleContext(Tff_unitary_formulaContext.class,0);
		}
		public Tff_quantified_formulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tff_quantified_formula; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitTff_quantified_formula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tff_quantified_formulaContext tff_quantified_formula() throws RecognitionException {
		Tff_quantified_formulaContext _localctx = new Tff_quantified_formulaContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_tff_quantified_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			fof_quantifier();
			setState(143);
			match(T__4);
			setState(144);
			variable_list();
			setState(145);
			match(T__5);
			setState(146);
			match(T__6);
			setState(147);
			tff_unitary_formula();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tff_unary_formulaContext extends ParserRuleContext {
		public Unary_connectiveContext unary_connective() {
			return getRuleContext(Unary_connectiveContext.class,0);
		}
		public Tff_unitary_formulaContext tff_unitary_formula() {
			return getRuleContext(Tff_unitary_formulaContext.class,0);
		}
		public Fof_infix_unaryContext fof_infix_unary() {
			return getRuleContext(Fof_infix_unaryContext.class,0);
		}
		public Tff_logic_formulaContext tff_logic_formula() {
			return getRuleContext(Tff_logic_formulaContext.class,0);
		}
		public Tff_unary_formulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tff_unary_formula; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitTff_unary_formula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tff_unary_formulaContext tff_unary_formula() throws RecognitionException {
		Tff_unary_formulaContext _localctx = new Tff_unary_formulaContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_tff_unary_formula);
		try {
			setState(157);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Not:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				unary_connective();
				setState(150);
				tff_unitary_formula();
				}
				break;
			case Upper_word:
			case Lower_word:
			case Single_quoted:
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				fof_infix_unary();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 3);
				{
				setState(153);
				match(T__1);
				setState(154);
				tff_logic_formula();
				setState(155);
				match(T__7);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tff_atomic_formulaContext extends ParserRuleContext {
		public Atomic_wordContext atomic_word() {
			return getRuleContext(Atomic_wordContext.class,0);
		}
		public Argument_listContext argument_list() {
			return getRuleContext(Argument_listContext.class,0);
		}
		public Tff_atomic_formulaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tff_atomic_formula; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitTff_atomic_formula(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tff_atomic_formulaContext tff_atomic_formula() throws RecognitionException {
		Tff_atomic_formulaContext _localctx = new Tff_atomic_formulaContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_tff_atomic_formula);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			atomic_word();
			setState(164);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(160);
				match(T__1);
				setState(161);
				argument_list();
				setState(162);
				match(T__7);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Fof_infix_unaryContext extends ParserRuleContext {
		public List<Fof_termContext> fof_term() {
			return getRuleContexts(Fof_termContext.class);
		}
		public Fof_termContext fof_term(int i) {
			return getRuleContext(Fof_termContext.class,i);
		}
		public TerminalNode Infix_inequality() { return getToken(TFFParser.Infix_inequality, 0); }
		public TerminalNode Infix_equality() { return getToken(TFFParser.Infix_equality, 0); }
		public Fof_infix_unaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fof_infix_unary; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitFof_infix_unary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Fof_infix_unaryContext fof_infix_unary() throws RecognitionException {
		Fof_infix_unaryContext _localctx = new Fof_infix_unaryContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_fof_infix_unary);
		try {
			setState(174);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(166);
				fof_term();
				setState(167);
				match(Infix_inequality);
				setState(168);
				fof_term();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(170);
				fof_term();
				setState(171);
				match(Infix_equality);
				setState(172);
				fof_term();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Fof_termContext extends ParserRuleContext {
		public Tff_atomic_formulaContext tff_atomic_formula() {
			return getRuleContext(Tff_atomic_formulaContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public Fof_termContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fof_term; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitFof_term(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Fof_termContext fof_term() throws RecognitionException {
		Fof_termContext _localctx = new Fof_termContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_fof_term);
		try {
			setState(178);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Lower_word:
			case Single_quoted:
				enterOuterAlt(_localctx, 1);
				{
				setState(176);
				tff_atomic_formula();
				}
				break;
			case Upper_word:
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
				variable();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Argument_listContext extends ParserRuleContext {
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public Argument_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitArgument_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Argument_listContext argument_list() throws RecognitionException {
		Argument_listContext _localctx = new Argument_listContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_argument_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			argument();
			setState(185);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(181);
				match(T__2);
				setState(182);
				argument();
				}
				}
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public Atomic_wordContext atomic_word() {
			return getRuleContext(Atomic_wordContext.class,0);
		}
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_argument);
		try {
			setState(190);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Upper_word:
				enterOuterAlt(_localctx, 1);
				{
				setState(188);
				variable();
				}
				break;
			case Lower_word:
			case Single_quoted:
				enterOuterAlt(_localctx, 2);
				{
				setState(189);
				atomic_word();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Tff_typed_atomContext extends ParserRuleContext {
		public List<Atomic_wordContext> atomic_word() {
			return getRuleContexts(Atomic_wordContext.class);
		}
		public Atomic_wordContext atomic_word(int i) {
			return getRuleContext(Atomic_wordContext.class,i);
		}
		public Tff_typed_atomContext tff_typed_atom() {
			return getRuleContext(Tff_typed_atomContext.class,0);
		}
		public Tff_typed_atomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tff_typed_atom; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitTff_typed_atom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Tff_typed_atomContext tff_typed_atom() throws RecognitionException {
		Tff_typed_atomContext _localctx = new Tff_typed_atomContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_tff_typed_atom);
		try {
			setState(200);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Lower_word:
			case Single_quoted:
				enterOuterAlt(_localctx, 1);
				{
				setState(192);
				atomic_word();
				setState(193);
				match(T__6);
				setState(194);
				atomic_word();
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(196);
				match(T__1);
				setState(197);
				tff_typed_atom();
				setState(198);
				match(T__7);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode Upper_word() { return getToken(TFFParser.Upper_word, 0); }
		public Atomic_wordContext atomic_word() {
			return getRuleContext(Atomic_wordContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			match(Upper_word);
			setState(205);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(203);
				match(T__6);
				setState(204);
				atomic_word();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Variable_listContext extends ParserRuleContext {
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
		}
		public Variable_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitVariable_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_listContext variable_list() throws RecognitionException {
		Variable_listContext _localctx = new Variable_listContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_variable_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			variable();
			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(208);
				match(T__2);
				setState(209);
				variable();
				}
				}
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NameContext extends ParserRuleContext {
		public Atomic_wordContext atomic_word() {
			return getRuleContext(Atomic_wordContext.class,0);
		}
		public TerminalNode Integer() { return getToken(TFFParser.Integer, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_name);
		try {
			setState(217);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Lower_word:
			case Single_quoted:
				enterOuterAlt(_localctx, 1);
				{
				setState(215);
				atomic_word();
				}
				break;
			case Integer:
				enterOuterAlt(_localctx, 2);
				{
				setState(216);
				match(Integer);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Atomic_word_listContext extends ParserRuleContext {
		public Atomic_wordContext atomic_word() {
			return getRuleContext(Atomic_wordContext.class,0);
		}
		public List<Atomic_word_listContext> atomic_word_list() {
			return getRuleContexts(Atomic_word_listContext.class);
		}
		public Atomic_word_listContext atomic_word_list(int i) {
			return getRuleContext(Atomic_word_listContext.class,i);
		}
		public Atomic_word_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomic_word_list; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitAtomic_word_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atomic_word_listContext atomic_word_list() throws RecognitionException {
		Atomic_word_listContext _localctx = new Atomic_word_listContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_atomic_word_list);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			atomic_word();
			setState(224);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(220);
					match(T__2);
					setState(221);
					atomic_word_list();
					}
					} 
				}
				setState(226);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Atomic_wordContext extends ParserRuleContext {
		public TerminalNode Lower_word() { return getToken(TFFParser.Lower_word, 0); }
		public TerminalNode Single_quoted() { return getToken(TFFParser.Single_quoted, 0); }
		public Atomic_wordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomic_word; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitAtomic_word(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atomic_wordContext atomic_word() throws RecognitionException {
		Atomic_wordContext _localctx = new Atomic_wordContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_atomic_word);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(227);
			_la = _input.LA(1);
			if ( !(_la==Lower_word || _la==Single_quoted) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Atomic_defined_wordContext extends ParserRuleContext {
		public TerminalNode Dollar_word() { return getToken(TFFParser.Dollar_word, 0); }
		public Atomic_defined_wordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomic_defined_word; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitAtomic_defined_word(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atomic_defined_wordContext atomic_defined_word() throws RecognitionException {
		Atomic_defined_wordContext _localctx = new Atomic_defined_wordContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_atomic_defined_word);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(229);
			match(Dollar_word);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Atomic_system_wordContext extends ParserRuleContext {
		public TerminalNode Dollar_dollar_word() { return getToken(TFFParser.Dollar_dollar_word, 0); }
		public Atomic_system_wordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atomic_system_word; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitAtomic_system_word(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Atomic_system_wordContext atomic_system_word() throws RecognitionException {
		Atomic_system_wordContext _localctx = new Atomic_system_wordContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_atomic_system_word);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			match(Dollar_dollar_word);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode Integer() { return getToken(TFFParser.Integer, 0); }
		public TerminalNode Rational() { return getToken(TFFParser.Rational, 0); }
		public TerminalNode Real() { return getToken(TFFParser.Real, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(233);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Real) | (1L << Rational) | (1L << Integer))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class File_nameContext extends ParserRuleContext {
		public TerminalNode Single_quoted() { return getToken(TFFParser.Single_quoted, 0); }
		public File_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file_name; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof TFFVisitor ) return ((TFFVisitor<? extends T>)visitor).visitFile_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final File_nameContext file_name() throws RecognitionException {
		File_nameContext _localctx = new File_nameContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_file_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(Single_quoted);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 12:
			return tff_or_formula_sempred((Tff_or_formulaContext)_localctx, predIndex);
		case 13:
			return tff_and_formula_sempred((Tff_and_formulaContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean tff_or_formula_sempred(Tff_or_formulaContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean tff_and_formula_sempred(Tff_and_formulaContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3B\u00f0\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\7\6L\n\6\f\6\16\6O\13\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\5\t`\n\t\3\n\3"+
		"\n\5\nd\n\n\3\13\3\13\5\13h\n\13\3\f\3\f\3\f\3\f\3\r\3\r\5\rp\n\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16z\n\16\f\16\16\16}\13\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u0087\n\17\f\17\16\17\u008a\13"+
		"\17\3\20\3\20\3\20\5\20\u008f\n\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00a0\n\22\3\23\3\23\3\23"+
		"\3\23\3\23\5\23\u00a7\n\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24"+
		"\u00b1\n\24\3\25\3\25\5\25\u00b5\n\25\3\26\3\26\3\26\7\26\u00ba\n\26\f"+
		"\26\16\26\u00bd\13\26\3\27\3\27\5\27\u00c1\n\27\3\30\3\30\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\5\30\u00cb\n\30\3\31\3\31\3\31\5\31\u00d0\n\31\3\32"+
		"\3\32\3\32\7\32\u00d5\n\32\f\32\16\32\u00d8\13\32\3\33\3\33\5\33\u00dc"+
		"\n\33\3\34\3\34\3\34\7\34\u00e1\n\34\f\34\16\34\u00e4\13\34\3\35\3\35"+
		"\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3!\2\4\32\34\"\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@\2\7\4\2\30\30\33\33\3\2\r\17"+
		"\3\2\13\f\3\2=>\5\2)),,//\2\u00e4\2B\3\2\2\2\4D\3\2\2\2\6F\3\2\2\2\bH"+
		"\3\2\2\2\nM\3\2\2\2\fR\3\2\2\2\16[\3\2\2\2\20_\3\2\2\2\22c\3\2\2\2\24"+
		"g\3\2\2\2\26i\3\2\2\2\30o\3\2\2\2\32q\3\2\2\2\34~\3\2\2\2\36\u008e\3\2"+
		"\2\2 \u0090\3\2\2\2\"\u009f\3\2\2\2$\u00a1\3\2\2\2&\u00b0\3\2\2\2(\u00b4"+
		"\3\2\2\2*\u00b6\3\2\2\2,\u00c0\3\2\2\2.\u00ca\3\2\2\2\60\u00cc\3\2\2\2"+
		"\62\u00d1\3\2\2\2\64\u00db\3\2\2\2\66\u00dd\3\2\2\28\u00e5\3\2\2\2:\u00e7"+
		"\3\2\2\2<\u00e9\3\2\2\2>\u00eb\3\2\2\2@\u00ed\3\2\2\2BC\t\2\2\2C\3\3\2"+
		"\2\2DE\t\3\2\2E\5\3\2\2\2FG\t\4\2\2G\7\3\2\2\2HI\7\23\2\2I\t\3\2\2\2J"+
		"L\5\f\7\2KJ\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2NP\3\2\2\2OM\3\2\2\2"+
		"PQ\7\2\2\3Q\13\3\2\2\2RS\7\3\2\2ST\7\4\2\2TU\5\64\33\2UV\7\5\2\2VW\5\16"+
		"\b\2WX\7\5\2\2XY\5\20\t\2YZ\7\6\2\2Z\r\3\2\2\2[\\\7=\2\2\\\17\3\2\2\2"+
		"]`\5\22\n\2^`\5.\30\2_]\3\2\2\2_^\3\2\2\2`\21\3\2\2\2ad\5\24\13\2bd\5"+
		"\36\20\2ca\3\2\2\2cb\3\2\2\2d\23\3\2\2\2eh\5\26\f\2fh\5\30\r\2ge\3\2\2"+
		"\2gf\3\2\2\2h\25\3\2\2\2ij\5\36\20\2jk\5\4\3\2kl\5\36\20\2l\27\3\2\2\2"+
		"mp\5\32\16\2np\5\34\17\2om\3\2\2\2on\3\2\2\2p\31\3\2\2\2qr\b\16\1\2rs"+
		"\5\36\20\2st\7\13\2\2tu\5\36\20\2u{\3\2\2\2vw\f\3\2\2wx\7\13\2\2xz\5\36"+
		"\20\2yv\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2\2\2|\33\3\2\2\2}{\3\2\2\2~\177"+
		"\b\17\1\2\177\u0080\5\36\20\2\u0080\u0081\7\f\2\2\u0081\u0082\5\36\20"+
		"\2\u0082\u0088\3\2\2\2\u0083\u0084\f\3\2\2\u0084\u0085\7\f\2\2\u0085\u0087"+
		"\5\36\20\2\u0086\u0083\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086\3\2\2\2"+
		"\u0088\u0089\3\2\2\2\u0089\35\3\2\2\2\u008a\u0088\3\2\2\2\u008b\u008f"+
		"\5 \21\2\u008c\u008f\5\"\22\2\u008d\u008f\5$\23\2\u008e\u008b\3\2\2\2"+
		"\u008e\u008c\3\2\2\2\u008e\u008d\3\2\2\2\u008f\37\3\2\2\2\u0090\u0091"+
		"\5\2\2\2\u0091\u0092\7\7\2\2\u0092\u0093\5\62\32\2\u0093\u0094\7\b\2\2"+
		"\u0094\u0095\7\t\2\2\u0095\u0096\5\36\20\2\u0096!\3\2\2\2\u0097\u0098"+
		"\5\b\5\2\u0098\u0099\5\36\20\2\u0099\u00a0\3\2\2\2\u009a\u00a0\5&\24\2"+
		"\u009b\u009c\7\4\2\2\u009c\u009d\5\22\n\2\u009d\u009e\7\n\2\2\u009e\u00a0"+
		"\3\2\2\2\u009f\u0097\3\2\2\2\u009f\u009a\3\2\2\2\u009f\u009b\3\2\2\2\u00a0"+
		"#\3\2\2\2\u00a1\u00a6\58\35\2\u00a2\u00a3\7\4\2\2\u00a3\u00a4\5*\26\2"+
		"\u00a4\u00a5\7\n\2\2\u00a5\u00a7\3\2\2\2\u00a6\u00a2\3\2\2\2\u00a6\u00a7"+
		"\3\2\2\2\u00a7%\3\2\2\2\u00a8\u00a9\5(\25\2\u00a9\u00aa\7\26\2\2\u00aa"+
		"\u00ab\5(\25\2\u00ab\u00b1\3\2\2\2\u00ac\u00ad\5(\25\2\u00ad\u00ae\7\27"+
		"\2\2\u00ae\u00af\5(\25\2\u00af\u00b1\3\2\2\2\u00b0\u00a8\3\2\2\2\u00b0"+
		"\u00ac\3\2\2\2\u00b1\'\3\2\2\2\u00b2\u00b5\5$\23\2\u00b3\u00b5\5\60\31"+
		"\2\u00b4\u00b2\3\2\2\2\u00b4\u00b3\3\2\2\2\u00b5)\3\2\2\2\u00b6\u00bb"+
		"\5,\27\2\u00b7\u00b8\7\5\2\2\u00b8\u00ba\5,\27\2\u00b9\u00b7\3\2\2\2\u00ba"+
		"\u00bd\3\2\2\2\u00bb\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc+\3\2\2\2"+
		"\u00bd\u00bb\3\2\2\2\u00be\u00c1\5\60\31\2\u00bf\u00c1\58\35\2\u00c0\u00be"+
		"\3\2\2\2\u00c0\u00bf\3\2\2\2\u00c1-\3\2\2\2\u00c2\u00c3\58\35\2\u00c3"+
		"\u00c4\7\t\2\2\u00c4\u00c5\58\35\2\u00c5\u00cb\3\2\2\2\u00c6\u00c7\7\4"+
		"\2\2\u00c7\u00c8\5.\30\2\u00c8\u00c9\7\n\2\2\u00c9\u00cb\3\2\2\2\u00ca"+
		"\u00c2\3\2\2\2\u00ca\u00c6\3\2\2\2\u00cb/\3\2\2\2\u00cc\u00cf\7<\2\2\u00cd"+
		"\u00ce\7\t\2\2\u00ce\u00d0\58\35\2\u00cf\u00cd\3\2\2\2\u00cf\u00d0\3\2"+
		"\2\2\u00d0\61\3\2\2\2\u00d1\u00d6\5\60\31\2\u00d2\u00d3\7\5\2\2\u00d3"+
		"\u00d5\5\60\31\2\u00d4\u00d2\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3"+
		"\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\63\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9"+
		"\u00dc\58\35\2\u00da\u00dc\7/\2\2\u00db\u00d9\3\2\2\2\u00db\u00da\3\2"+
		"\2\2\u00dc\65\3\2\2\2\u00dd\u00e2\58\35\2\u00de\u00df\7\5\2\2\u00df\u00e1"+
		"\5\66\34\2\u00e0\u00de\3\2\2\2\u00e1\u00e4\3\2\2\2\u00e2\u00e0\3\2\2\2"+
		"\u00e2\u00e3\3\2\2\2\u00e3\67\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e5\u00e6"+
		"\t\5\2\2\u00e69\3\2\2\2\u00e7\u00e8\7:\2\2\u00e8;\3\2\2\2\u00e9\u00ea"+
		"\7;\2\2\u00ea=\3\2\2\2\u00eb\u00ec\t\6\2\2\u00ec?\3\2\2\2\u00ed\u00ee"+
		"\7>\2\2\u00eeA\3\2\2\2\25M_cgo{\u0088\u008e\u009f\u00a6\u00b0\u00b4\u00bb"+
		"\u00c0\u00ca\u00cf\u00d6\u00db\u00e2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}