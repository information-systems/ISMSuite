// Generated from Specification.g4 by ANTLR 4.7.1
package org.architecturemining.ismodeler.specification.parsing;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SpecificationLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, WS=14, Line_comment=15, Block_comment=16, 
		Upper_word=17, Lower_word=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "Do_char", "Sq_char", "Sign", "Exponent", 
		"Non_zero_numeric", "Numeric", "Lower_alpha", "Upper_alpha", "Alpha_numeric", 
		"WS", "Line_comment", "Block_comment", "Upper_word", "Lower_word"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'process'", "'{'", "'}'", "'place'", "'('", "')'", "'transition'", 
		"','", "':'", "';'", "'register'", "'insert'", "'remove'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, "WS", "Line_comment", "Block_comment", "Upper_word", "Lower_word"
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


	public SpecificationLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Specification.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24\u00c1\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\17\3\17\3\17\5\17{\n\17\3\20\3\20\3\20\5\20\u0080\n\20"+
		"\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27"+
		"\3\27\3\27\5\27\u0092\n\27\3\30\6\30\u0095\n\30\r\30\16\30\u0096\3\30"+
		"\3\30\3\31\3\31\3\31\3\31\7\31\u009f\n\31\f\31\16\31\u00a2\13\31\3\31"+
		"\3\31\3\32\3\32\3\32\3\32\7\32\u00aa\n\32\f\32\16\32\u00ad\13\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\33\3\33\7\33\u00b6\n\33\f\33\16\33\u00b9\13\33"+
		"\3\34\3\34\7\34\u00bd\n\34\f\34\16\34\u00c0\13\34\3\u00ab\2\35\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\2\37\2!\2#"+
		"\2%\2\'\2)\2+\2-\2/\20\61\21\63\22\65\23\67\24\3\2\16\5\2\"#%]_\u0080"+
		"\4\2$$^^\5\2\"(*]_\u0080\4\2))^^\4\2--//\4\2GGgg\3\2\63;\3\2\62;\3\2c"+
		"|\3\2C\\\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u00c1\2\3\3\2\2\2\2\5\3\2"+
		"\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21"+
		"\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\3"+
		"9\3\2\2\2\5A\3\2\2\2\7C\3\2\2\2\tE\3\2\2\2\13K\3\2\2\2\rM\3\2\2\2\17O"+
		"\3\2\2\2\21Z\3\2\2\2\23\\\3\2\2\2\25^\3\2\2\2\27`\3\2\2\2\31i\3\2\2\2"+
		"\33p\3\2\2\2\35z\3\2\2\2\37\177\3\2\2\2!\u0081\3\2\2\2#\u0083\3\2\2\2"+
		"%\u0085\3\2\2\2\'\u0087\3\2\2\2)\u0089\3\2\2\2+\u008b\3\2\2\2-\u0091\3"+
		"\2\2\2/\u0094\3\2\2\2\61\u009a\3\2\2\2\63\u00a5\3\2\2\2\65\u00b3\3\2\2"+
		"\2\67\u00ba\3\2\2\29:\7r\2\2:;\7t\2\2;<\7q\2\2<=\7e\2\2=>\7g\2\2>?\7u"+
		"\2\2?@\7u\2\2@\4\3\2\2\2AB\7}\2\2B\6\3\2\2\2CD\7\177\2\2D\b\3\2\2\2EF"+
		"\7r\2\2FG\7n\2\2GH\7c\2\2HI\7e\2\2IJ\7g\2\2J\n\3\2\2\2KL\7*\2\2L\f\3\2"+
		"\2\2MN\7+\2\2N\16\3\2\2\2OP\7v\2\2PQ\7t\2\2QR\7c\2\2RS\7p\2\2ST\7u\2\2"+
		"TU\7k\2\2UV\7v\2\2VW\7k\2\2WX\7q\2\2XY\7p\2\2Y\20\3\2\2\2Z[\7.\2\2[\22"+
		"\3\2\2\2\\]\7<\2\2]\24\3\2\2\2^_\7=\2\2_\26\3\2\2\2`a\7t\2\2ab\7g\2\2"+
		"bc\7i\2\2cd\7k\2\2de\7u\2\2ef\7v\2\2fg\7g\2\2gh\7t\2\2h\30\3\2\2\2ij\7"+
		"k\2\2jk\7p\2\2kl\7u\2\2lm\7g\2\2mn\7t\2\2no\7v\2\2o\32\3\2\2\2pq\7t\2"+
		"\2qr\7g\2\2rs\7o\2\2st\7q\2\2tu\7x\2\2uv\7g\2\2v\34\3\2\2\2w{\t\2\2\2"+
		"xy\7^\2\2y{\t\3\2\2zw\3\2\2\2zx\3\2\2\2{\36\3\2\2\2|\u0080\t\4\2\2}~\7"+
		"^\2\2~\u0080\t\5\2\2\177|\3\2\2\2\177}\3\2\2\2\u0080 \3\2\2\2\u0081\u0082"+
		"\t\6\2\2\u0082\"\3\2\2\2\u0083\u0084\t\7\2\2\u0084$\3\2\2\2\u0085\u0086"+
		"\t\b\2\2\u0086&\3\2\2\2\u0087\u0088\t\t\2\2\u0088(\3\2\2\2\u0089\u008a"+
		"\t\n\2\2\u008a*\3\2\2\2\u008b\u008c\t\13\2\2\u008c,\3\2\2\2\u008d\u0092"+
		"\5)\25\2\u008e\u0092\5+\26\2\u008f\u0092\5\'\24\2\u0090\u0092\7a\2\2\u0091"+
		"\u008d\3\2\2\2\u0091\u008e\3\2\2\2\u0091\u008f\3\2\2\2\u0091\u0090\3\2"+
		"\2\2\u0092.\3\2\2\2\u0093\u0095\t\f\2\2\u0094\u0093\3\2\2\2\u0095\u0096"+
		"\3\2\2\2\u0096\u0094\3\2\2\2\u0096\u0097\3\2\2\2\u0097\u0098\3\2\2\2\u0098"+
		"\u0099\b\30\2\2\u0099\60\3\2\2\2\u009a\u009b\7\61\2\2\u009b\u009c\7\61"+
		"\2\2\u009c\u00a0\3\2\2\2\u009d\u009f\n\r\2\2\u009e\u009d\3\2\2\2\u009f"+
		"\u00a2\3\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a3\3\2"+
		"\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a4\b\31\2\2\u00a4\62\3\2\2\2\u00a5\u00a6"+
		"\7\61\2\2\u00a6\u00a7\7,\2\2\u00a7\u00ab\3\2\2\2\u00a8\u00aa\13\2\2\2"+
		"\u00a9\u00a8\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ab\u00a9"+
		"\3\2\2\2\u00ac\u00ae\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ae\u00af\7,\2\2\u00af"+
		"\u00b0\7\61\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b2\b\32\2\2\u00b2\64\3\2"+
		"\2\2\u00b3\u00b7\5+\26\2\u00b4\u00b6\5-\27\2\u00b5\u00b4\3\2\2\2\u00b6"+
		"\u00b9\3\2\2\2\u00b7\u00b5\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\66\3\2\2"+
		"\2\u00b9\u00b7\3\2\2\2\u00ba\u00be\5)\25\2\u00bb\u00bd\5-\27\2\u00bc\u00bb"+
		"\3\2\2\2\u00bd\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf"+
		"8\3\2\2\2\u00c0\u00be\3\2\2\2\13\2z\177\u0091\u0096\u00a0\u00ab\u00b7"+
		"\u00be\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}