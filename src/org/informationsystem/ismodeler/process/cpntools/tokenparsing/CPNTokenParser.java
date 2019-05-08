// Generated from CPNToken.g4 by ANTLR 4.7.1
package org.informationsystem.ismodeler.process.cpntools.tokenparsing;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CPNTokenParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, WS=6, Line_comment=7, Block_comment=8, 
		Numeric=9;
	public static final int
		RULE_token_list = 0, RULE_token = 1, RULE_count = 2, RULE_token_value = 3, 
		RULE_id = 4, RULE_id_list = 5;
	public static final String[] ruleNames = {
		"token_list", "token", "count", "token_value", "id", "id_list"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'++'", "'`'", "'('", "','", "')'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, "WS", "Line_comment", "Block_comment", 
		"Numeric"
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
	public String getGrammarFileName() { return "CPNToken.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CPNTokenParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Token_listContext extends ParserRuleContext {
		public List<TokenContext> token() {
			return getRuleContexts(TokenContext.class);
		}
		public TokenContext token(int i) {
			return getRuleContext(TokenContext.class,i);
		}
		public TerminalNode EOF() { return getToken(CPNTokenParser.EOF, 0); }
		public Token_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_token_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPNTokenListener ) ((CPNTokenListener)listener).enterToken_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPNTokenListener ) ((CPNTokenListener)listener).exitToken_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPNTokenVisitor ) return ((CPNTokenVisitor<? extends T>)visitor).visitToken_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Token_listContext token_list() throws RecognitionException {
		Token_listContext _localctx = new Token_listContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_token_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			token();
			setState(17);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(13);
				match(T__0);
				setState(14);
				token();
				}
				}
				setState(19);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(20);
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

	public static class TokenContext extends ParserRuleContext {
		public CountContext count() {
			return getRuleContext(CountContext.class,0);
		}
		public Token_valueContext token_value() {
			return getRuleContext(Token_valueContext.class,0);
		}
		public TokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_token; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPNTokenListener ) ((CPNTokenListener)listener).enterToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPNTokenListener ) ((CPNTokenListener)listener).exitToken(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPNTokenVisitor ) return ((CPNTokenVisitor<? extends T>)visitor).visitToken(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TokenContext token() throws RecognitionException {
		TokenContext _localctx = new TokenContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_token);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			count();
			setState(23);
			match(T__1);
			setState(24);
			token_value();
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

	public static class CountContext extends ParserRuleContext {
		public List<TerminalNode> Numeric() { return getTokens(CPNTokenParser.Numeric); }
		public TerminalNode Numeric(int i) {
			return getToken(CPNTokenParser.Numeric, i);
		}
		public CountContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_count; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPNTokenListener ) ((CPNTokenListener)listener).enterCount(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPNTokenListener ) ((CPNTokenListener)listener).exitCount(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPNTokenVisitor ) return ((CPNTokenVisitor<? extends T>)visitor).visitCount(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CountContext count() throws RecognitionException {
		CountContext _localctx = new CountContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_count);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(26);
				match(Numeric);
				}
				}
				setState(29); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Numeric );
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

	public static class Token_valueContext extends ParserRuleContext {
		public IdContext id() {
			return getRuleContext(IdContext.class,0);
		}
		public Id_listContext id_list() {
			return getRuleContext(Id_listContext.class,0);
		}
		public Token_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_token_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPNTokenListener ) ((CPNTokenListener)listener).enterToken_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPNTokenListener ) ((CPNTokenListener)listener).exitToken_value(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPNTokenVisitor ) return ((CPNTokenVisitor<? extends T>)visitor).visitToken_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Token_valueContext token_value() throws RecognitionException {
		Token_valueContext _localctx = new Token_valueContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_token_value);
		try {
			setState(33);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Numeric:
				enterOuterAlt(_localctx, 1);
				{
				setState(31);
				id();
				}
				break;
			case T__2:
				enterOuterAlt(_localctx, 2);
				{
				setState(32);
				id_list();
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

	public static class IdContext extends ParserRuleContext {
		public List<TerminalNode> Numeric() { return getTokens(CPNTokenParser.Numeric); }
		public TerminalNode Numeric(int i) {
			return getToken(CPNTokenParser.Numeric, i);
		}
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPNTokenListener ) ((CPNTokenListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPNTokenListener ) ((CPNTokenListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPNTokenVisitor ) return ((CPNTokenVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_id);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(35);
				match(Numeric);
				}
				}
				setState(38); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==Numeric );
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

	public static class Id_listContext extends ParserRuleContext {
		public List<IdContext> id() {
			return getRuleContexts(IdContext.class);
		}
		public IdContext id(int i) {
			return getRuleContext(IdContext.class,i);
		}
		public Id_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CPNTokenListener ) ((CPNTokenListener)listener).enterId_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CPNTokenListener ) ((CPNTokenListener)listener).exitId_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof CPNTokenVisitor ) return ((CPNTokenVisitor<? extends T>)visitor).visitId_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Id_listContext id_list() throws RecognitionException {
		Id_listContext _localctx = new Id_listContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_id_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(T__2);
			setState(41);
			id();
			setState(46);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(42);
				match(T__3);
				setState(43);
				id();
				}
				}
				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(49);
			match(T__4);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\13\66\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\2\7\2\22\n\2\f\2\16\2\25"+
		"\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\4\6\4\36\n\4\r\4\16\4\37\3\5\3\5\5\5$"+
		"\n\5\3\6\6\6\'\n\6\r\6\16\6(\3\7\3\7\3\7\3\7\7\7/\n\7\f\7\16\7\62\13\7"+
		"\3\7\3\7\3\7\2\2\b\2\4\6\b\n\f\2\2\2\64\2\16\3\2\2\2\4\30\3\2\2\2\6\35"+
		"\3\2\2\2\b#\3\2\2\2\n&\3\2\2\2\f*\3\2\2\2\16\23\5\4\3\2\17\20\7\3\2\2"+
		"\20\22\5\4\3\2\21\17\3\2\2\2\22\25\3\2\2\2\23\21\3\2\2\2\23\24\3\2\2\2"+
		"\24\26\3\2\2\2\25\23\3\2\2\2\26\27\7\2\2\3\27\3\3\2\2\2\30\31\5\6\4\2"+
		"\31\32\7\4\2\2\32\33\5\b\5\2\33\5\3\2\2\2\34\36\7\13\2\2\35\34\3\2\2\2"+
		"\36\37\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 \7\3\2\2\2!$\5\n\6\2\"$\5\f\7"+
		"\2#!\3\2\2\2#\"\3\2\2\2$\t\3\2\2\2%\'\7\13\2\2&%\3\2\2\2\'(\3\2\2\2(&"+
		"\3\2\2\2()\3\2\2\2)\13\3\2\2\2*+\7\5\2\2+\60\5\n\6\2,-\7\6\2\2-/\5\n\6"+
		"\2.,\3\2\2\2/\62\3\2\2\2\60.\3\2\2\2\60\61\3\2\2\2\61\63\3\2\2\2\62\60"+
		"\3\2\2\2\63\64\7\7\2\2\64\r\3\2\2\2\7\23\37#(\60";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}