// Generated from Specification.g4 by ANTLR 4.7.1
package org.informationsystem.ismodeler.specification.parsing;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SpecificationParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, WS=16, Line_comment=17, 
		Block_comment=18, Upper_word=19, Lower_word=20;
	public static final int
		RULE_specication_file = 0, RULE_process = 1, RULE_process_content = 2, 
		RULE_place = 3, RULE_transition = 4, RULE_argument_list = 5, RULE_variable_declaration = 6, 
		RULE_transaction = 7, RULE_register_operator = 8, RULE_insert_operator = 9, 
		RULE_remove_operator = 10, RULE_relation = 11, RULE_variable_list = 12, 
		RULE_name = 13, RULE_variable = 14, RULE_type = 15;
	public static final String[] ruleNames = {
		"specication_file", "process", "process_content", "place", "transition", 
		"argument_list", "variable_declaration", "transaction", "register_operator", 
		"insert_operator", "remove_operator", "relation", "variable_list", "name", 
		"variable", "type"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'process'", "'{'", "'}'", "'place'", "'('", "')'", "'transition'", 
		"','", "':'", "';'", "'register'", "'insert'", "'into'", "'remove'", "'from'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, "WS", "Line_comment", "Block_comment", "Upper_word", 
		"Lower_word"
	};
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
	public String getGrammarFileName() { return "Specification.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SpecificationParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Specication_fileContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(SpecificationParser.EOF, 0); }
		public List<ProcessContext> process() {
			return getRuleContexts(ProcessContext.class);
		}
		public ProcessContext process(int i) {
			return getRuleContext(ProcessContext.class,i);
		}
		public Specication_fileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_specication_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).enterSpecication_file(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).exitSpecication_file(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpecificationVisitor ) return ((SpecificationVisitor<? extends T>)visitor).visitSpecication_file(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Specication_fileContext specication_file() throws RecognitionException {
		Specication_fileContext _localctx = new Specication_fileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_specication_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(32);
				process();
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(38);
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

	public static class ProcessContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Process_contentContext process_content() {
			return getRuleContext(Process_contentContext.class,0);
		}
		public ProcessContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_process; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).enterProcess(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).exitProcess(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpecificationVisitor ) return ((SpecificationVisitor<? extends T>)visitor).visitProcess(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcessContext process() throws RecognitionException {
		ProcessContext _localctx = new ProcessContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_process);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(T__0);
			setState(41);
			name();
			setState(42);
			match(T__1);
			setState(43);
			process_content();
			setState(44);
			match(T__2);
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

	public static class Process_contentContext extends ParserRuleContext {
		public List<PlaceContext> place() {
			return getRuleContexts(PlaceContext.class);
		}
		public PlaceContext place(int i) {
			return getRuleContext(PlaceContext.class,i);
		}
		public List<TransitionContext> transition() {
			return getRuleContexts(TransitionContext.class);
		}
		public TransitionContext transition(int i) {
			return getRuleContext(TransitionContext.class,i);
		}
		public Process_contentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_process_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).enterProcess_content(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).exitProcess_content(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpecificationVisitor ) return ((SpecificationVisitor<? extends T>)visitor).visitProcess_content(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Process_contentContext process_content() throws RecognitionException {
		Process_contentContext _localctx = new Process_contentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_process_content);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3 || _la==T__6) {
				{
				setState(48);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__3:
					{
					setState(46);
					place();
					}
					break;
				case T__6:
					{
					setState(47);
					transition();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(52);
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

	public static class PlaceContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Argument_listContext argument_list() {
			return getRuleContext(Argument_listContext.class,0);
		}
		public List<TransactionContext> transaction() {
			return getRuleContexts(TransactionContext.class);
		}
		public TransactionContext transaction(int i) {
			return getRuleContext(TransactionContext.class,i);
		}
		public PlaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_place; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).enterPlace(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).exitPlace(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpecificationVisitor ) return ((SpecificationVisitor<? extends T>)visitor).visitPlace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PlaceContext place() throws RecognitionException {
		PlaceContext _localctx = new PlaceContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_place);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(T__3);
			setState(54);
			name();
			setState(55);
			match(T__4);
			setState(56);
			argument_list();
			setState(57);
			match(T__5);
			setState(58);
			match(T__1);
			setState(62);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__13))) != 0)) {
				{
				{
				setState(59);
				transaction();
				}
				}
				setState(64);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(65);
			match(T__2);
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

	public static class TransitionContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public Argument_listContext argument_list() {
			return getRuleContext(Argument_listContext.class,0);
		}
		public List<TransactionContext> transaction() {
			return getRuleContexts(TransactionContext.class);
		}
		public TransactionContext transaction(int i) {
			return getRuleContext(TransactionContext.class,i);
		}
		public TransitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).enterTransition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).exitTransition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpecificationVisitor ) return ((SpecificationVisitor<? extends T>)visitor).visitTransition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransitionContext transition() throws RecognitionException {
		TransitionContext _localctx = new TransitionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_transition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(T__6);
			setState(68);
			name();
			setState(69);
			match(T__4);
			setState(70);
			argument_list();
			setState(71);
			match(T__5);
			setState(72);
			match(T__1);
			setState(76);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__13))) != 0)) {
				{
				{
				setState(73);
				transaction();
				}
				}
				setState(78);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(79);
			match(T__2);
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
		public List<Variable_declarationContext> variable_declaration() {
			return getRuleContexts(Variable_declarationContext.class);
		}
		public Variable_declarationContext variable_declaration(int i) {
			return getRuleContext(Variable_declarationContext.class,i);
		}
		public Argument_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).enterArgument_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).exitArgument_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpecificationVisitor ) return ((SpecificationVisitor<? extends T>)visitor).visitArgument_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Argument_listContext argument_list() throws RecognitionException {
		Argument_listContext _localctx = new Argument_listContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_argument_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			variable_declaration();
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(82);
				match(T__7);
				setState(83);
				variable_declaration();
				}
				}
				setState(88);
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

	public static class Variable_declarationContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Variable_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).enterVariable_declaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).exitVariable_declaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpecificationVisitor ) return ((SpecificationVisitor<? extends T>)visitor).visitVariable_declaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_declarationContext variable_declaration() throws RecognitionException {
		Variable_declarationContext _localctx = new Variable_declarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_variable_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(89);
			variable();
			setState(90);
			match(T__8);
			setState(91);
			type();
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

	public static class TransactionContext extends ParserRuleContext {
		public Register_operatorContext register_operator() {
			return getRuleContext(Register_operatorContext.class,0);
		}
		public Insert_operatorContext insert_operator() {
			return getRuleContext(Insert_operatorContext.class,0);
		}
		public Remove_operatorContext remove_operator() {
			return getRuleContext(Remove_operatorContext.class,0);
		}
		public TransactionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_transaction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).enterTransaction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).exitTransaction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpecificationVisitor ) return ((SpecificationVisitor<? extends T>)visitor).visitTransaction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TransactionContext transaction() throws RecognitionException {
		TransactionContext _localctx = new TransactionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_transaction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
				{
				setState(93);
				register_operator();
				}
				break;
			case T__11:
				{
				setState(94);
				insert_operator();
				}
				break;
			case T__13:
				{
				setState(95);
				remove_operator();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(98);
			match(T__9);
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

	public static class Register_operatorContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public Register_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_register_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).enterRegister_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).exitRegister_operator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpecificationVisitor ) return ((SpecificationVisitor<? extends T>)visitor).visitRegister_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Register_operatorContext register_operator() throws RecognitionException {
		Register_operatorContext _localctx = new Register_operatorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_register_operator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(T__10);
			setState(101);
			variable();
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

	public static class Insert_operatorContext extends ParserRuleContext {
		public Variable_listContext variable_list() {
			return getRuleContext(Variable_listContext.class,0);
		}
		public TerminalNode Lower_word() { return getToken(SpecificationParser.Lower_word, 0); }
		public Insert_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insert_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).enterInsert_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).exitInsert_operator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpecificationVisitor ) return ((SpecificationVisitor<? extends T>)visitor).visitInsert_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Insert_operatorContext insert_operator() throws RecognitionException {
		Insert_operatorContext _localctx = new Insert_operatorContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_insert_operator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			match(T__11);
			setState(104);
			match(T__4);
			setState(105);
			variable_list();
			setState(106);
			match(T__5);
			setState(107);
			match(T__12);
			setState(108);
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

	public static class Remove_operatorContext extends ParserRuleContext {
		public Variable_listContext variable_list() {
			return getRuleContext(Variable_listContext.class,0);
		}
		public TerminalNode Lower_word() { return getToken(SpecificationParser.Lower_word, 0); }
		public Remove_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_remove_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).enterRemove_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).exitRemove_operator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpecificationVisitor ) return ((SpecificationVisitor<? extends T>)visitor).visitRemove_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Remove_operatorContext remove_operator() throws RecognitionException {
		Remove_operatorContext _localctx = new Remove_operatorContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_remove_operator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(T__13);
			setState(111);
			match(T__4);
			setState(112);
			variable_list();
			setState(113);
			match(T__5);
			setState(114);
			match(T__14);
			setState(115);
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

	public static class RelationContext extends ParserRuleContext {
		public TerminalNode Lower_word() { return getToken(SpecificationParser.Lower_word, 0); }
		public Variable_listContext variable_list() {
			return getRuleContext(Variable_listContext.class,0);
		}
		public RelationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).enterRelation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).exitRelation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpecificationVisitor ) return ((SpecificationVisitor<? extends T>)visitor).visitRelation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationContext relation() throws RecognitionException {
		RelationContext _localctx = new RelationContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_relation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(Lower_word);
			setState(118);
			match(T__4);
			setState(119);
			variable_list();
			setState(120);
			match(T__5);
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
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).enterVariable_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).exitVariable_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpecificationVisitor ) return ((SpecificationVisitor<? extends T>)visitor).visitVariable_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Variable_listContext variable_list() throws RecognitionException {
		Variable_listContext _localctx = new Variable_listContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_variable_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			variable();
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(123);
				match(T__7);
				setState(124);
				variable();
				}
				}
				setState(129);
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
		public TerminalNode Upper_word() { return getToken(SpecificationParser.Upper_word, 0); }
		public TerminalNode Lower_word() { return getToken(SpecificationParser.Lower_word, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).enterName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).exitName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpecificationVisitor ) return ((SpecificationVisitor<? extends T>)visitor).visitName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			_la = _input.LA(1);
			if ( !(_la==Upper_word || _la==Lower_word) ) {
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

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode Upper_word() { return getToken(SpecificationParser.Upper_word, 0); }
		public TerminalNode Lower_word() { return getToken(SpecificationParser.Lower_word, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpecificationVisitor ) return ((SpecificationVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			_la = _input.LA(1);
			if ( !(_la==Upper_word || _la==Lower_word) ) {
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

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode Lower_word() { return getToken(SpecificationParser.Lower_word, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SpecificationListener ) ((SpecificationListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SpecificationVisitor ) return ((SpecificationVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\26\u008b\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\7\2"+
		"$\n\2\f\2\16\2\'\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\7\4\63\n"+
		"\4\f\4\16\4\66\13\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5?\n\5\f\5\16\5B\13"+
		"\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6M\n\6\f\6\16\6P\13\6\3\6\3\6"+
		"\3\7\3\7\3\7\7\7W\n\7\f\7\16\7Z\13\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\5\tc"+
		"\n\t\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\7\16\u0080\n\16"+
		"\f\16\16\16\u0083\13\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\2\2\22\2\4"+
		"\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\3\3\2\25\26\2\u0083\2%\3\2\2\2"+
		"\4*\3\2\2\2\6\64\3\2\2\2\b\67\3\2\2\2\nE\3\2\2\2\fS\3\2\2\2\16[\3\2\2"+
		"\2\20b\3\2\2\2\22f\3\2\2\2\24i\3\2\2\2\26p\3\2\2\2\30w\3\2\2\2\32|\3\2"+
		"\2\2\34\u0084\3\2\2\2\36\u0086\3\2\2\2 \u0088\3\2\2\2\"$\5\4\3\2#\"\3"+
		"\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&(\3\2\2\2\'%\3\2\2\2()\7\2\2\3)"+
		"\3\3\2\2\2*+\7\3\2\2+,\5\34\17\2,-\7\4\2\2-.\5\6\4\2./\7\5\2\2/\5\3\2"+
		"\2\2\60\63\5\b\5\2\61\63\5\n\6\2\62\60\3\2\2\2\62\61\3\2\2\2\63\66\3\2"+
		"\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\7\3\2\2\2\66\64\3\2\2\2\678\7\6\2"+
		"\289\5\34\17\29:\7\7\2\2:;\5\f\7\2;<\7\b\2\2<@\7\4\2\2=?\5\20\t\2>=\3"+
		"\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2AC\3\2\2\2B@\3\2\2\2CD\7\5\2\2D\t"+
		"\3\2\2\2EF\7\t\2\2FG\5\34\17\2GH\7\7\2\2HI\5\f\7\2IJ\7\b\2\2JN\7\4\2\2"+
		"KM\5\20\t\2LK\3\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2OQ\3\2\2\2PN\3\2\2"+
		"\2QR\7\5\2\2R\13\3\2\2\2SX\5\16\b\2TU\7\n\2\2UW\5\16\b\2VT\3\2\2\2WZ\3"+
		"\2\2\2XV\3\2\2\2XY\3\2\2\2Y\r\3\2\2\2ZX\3\2\2\2[\\\5\36\20\2\\]\7\13\2"+
		"\2]^\5 \21\2^\17\3\2\2\2_c\5\22\n\2`c\5\24\13\2ac\5\26\f\2b_\3\2\2\2b"+
		"`\3\2\2\2ba\3\2\2\2cd\3\2\2\2de\7\f\2\2e\21\3\2\2\2fg\7\r\2\2gh\5\36\20"+
		"\2h\23\3\2\2\2ij\7\16\2\2jk\7\7\2\2kl\5\32\16\2lm\7\b\2\2mn\7\17\2\2n"+
		"o\7\26\2\2o\25\3\2\2\2pq\7\20\2\2qr\7\7\2\2rs\5\32\16\2st\7\b\2\2tu\7"+
		"\21\2\2uv\7\26\2\2v\27\3\2\2\2wx\7\26\2\2xy\7\7\2\2yz\5\32\16\2z{\7\b"+
		"\2\2{\31\3\2\2\2|\u0081\5\36\20\2}~\7\n\2\2~\u0080\5\36\20\2\177}\3\2"+
		"\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081\u0082\3\2\2\2\u0082\33"+
		"\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085\t\2\2\2\u0085\35\3\2\2\2\u0086"+
		"\u0087\t\2\2\2\u0087\37\3\2\2\2\u0088\u0089\7\26\2\2\u0089!\3\2\2\2\n"+
		"%\62\64@NXb\u0081";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}