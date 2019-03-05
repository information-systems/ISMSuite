// Generated from Specification.g4 by ANTLR 4.7.1
package org.informationsystem.ismodeler.specification.parsing;
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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, WS=16, Line_comment=17, 
		Block_comment=18, Upper_word=19, Lower_word=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "Do_char", "Sq_char", 
		"Sign", "Exponent", "Non_zero_numeric", "Numeric", "Lower_alpha", "Upper_alpha", 
		"Alpha_numeric", "WS", "Line_comment", "Block_comment", "Upper_word", 
		"Lower_word"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26\u00cf\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13"+
		"\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\21\3\21\3\21\5\21\u0089\n\21\3\22\3\22\3\22\5\22\u008e"+
		"\n\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31"+
		"\3\31\3\31\3\31\5\31\u00a0\n\31\3\32\6\32\u00a3\n\32\r\32\16\32\u00a4"+
		"\3\32\3\32\3\33\3\33\3\33\3\33\7\33\u00ad\n\33\f\33\16\33\u00b0\13\33"+
		"\3\33\3\33\3\34\3\34\3\34\3\34\7\34\u00b8\n\34\f\34\16\34\u00bb\13\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\35\3\35\7\35\u00c4\n\35\f\35\16\35\u00c7\13"+
		"\35\3\36\3\36\7\36\u00cb\n\36\f\36\16\36\u00ce\13\36\3\u00b9\2\37\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\2#\2%\2\'\2)\2+\2-\2/\2\61\2\63\22\65\23\67\249\25;\26\3\2\16\5\2\""+
		"#%]_\u0080\4\2$$^^\5\2\"(*]_\u0080\4\2))^^\4\2--//\4\2GGgg\3\2\63;\3\2"+
		"\62;\3\2c|\3\2C\\\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u00cf\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67"+
		"\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\3=\3\2\2\2\5E\3\2\2\2\7G\3\2\2\2\tI\3\2"+
		"\2\2\13O\3\2\2\2\rQ\3\2\2\2\17S\3\2\2\2\21^\3\2\2\2\23`\3\2\2\2\25b\3"+
		"\2\2\2\27d\3\2\2\2\31m\3\2\2\2\33t\3\2\2\2\35y\3\2\2\2\37\u0080\3\2\2"+
		"\2!\u0088\3\2\2\2#\u008d\3\2\2\2%\u008f\3\2\2\2\'\u0091\3\2\2\2)\u0093"+
		"\3\2\2\2+\u0095\3\2\2\2-\u0097\3\2\2\2/\u0099\3\2\2\2\61\u009f\3\2\2\2"+
		"\63\u00a2\3\2\2\2\65\u00a8\3\2\2\2\67\u00b3\3\2\2\29\u00c1\3\2\2\2;\u00c8"+
		"\3\2\2\2=>\7r\2\2>?\7t\2\2?@\7q\2\2@A\7e\2\2AB\7g\2\2BC\7u\2\2CD\7u\2"+
		"\2D\4\3\2\2\2EF\7}\2\2F\6\3\2\2\2GH\7\177\2\2H\b\3\2\2\2IJ\7r\2\2JK\7"+
		"n\2\2KL\7c\2\2LM\7e\2\2MN\7g\2\2N\n\3\2\2\2OP\7*\2\2P\f\3\2\2\2QR\7+\2"+
		"\2R\16\3\2\2\2ST\7v\2\2TU\7t\2\2UV\7c\2\2VW\7p\2\2WX\7u\2\2XY\7k\2\2Y"+
		"Z\7v\2\2Z[\7k\2\2[\\\7q\2\2\\]\7p\2\2]\20\3\2\2\2^_\7.\2\2_\22\3\2\2\2"+
		"`a\7<\2\2a\24\3\2\2\2bc\7=\2\2c\26\3\2\2\2de\7t\2\2ef\7g\2\2fg\7i\2\2"+
		"gh\7k\2\2hi\7u\2\2ij\7v\2\2jk\7g\2\2kl\7t\2\2l\30\3\2\2\2mn\7k\2\2no\7"+
		"p\2\2op\7u\2\2pq\7g\2\2qr\7t\2\2rs\7v\2\2s\32\3\2\2\2tu\7k\2\2uv\7p\2"+
		"\2vw\7v\2\2wx\7q\2\2x\34\3\2\2\2yz\7t\2\2z{\7g\2\2{|\7o\2\2|}\7q\2\2}"+
		"~\7x\2\2~\177\7g\2\2\177\36\3\2\2\2\u0080\u0081\7h\2\2\u0081\u0082\7t"+
		"\2\2\u0082\u0083\7q\2\2\u0083\u0084\7o\2\2\u0084 \3\2\2\2\u0085\u0089"+
		"\t\2\2\2\u0086\u0087\7^\2\2\u0087\u0089\t\3\2\2\u0088\u0085\3\2\2\2\u0088"+
		"\u0086\3\2\2\2\u0089\"\3\2\2\2\u008a\u008e\t\4\2\2\u008b\u008c\7^\2\2"+
		"\u008c\u008e\t\5\2\2\u008d\u008a\3\2\2\2\u008d\u008b\3\2\2\2\u008e$\3"+
		"\2\2\2\u008f\u0090\t\6\2\2\u0090&\3\2\2\2\u0091\u0092\t\7\2\2\u0092(\3"+
		"\2\2\2\u0093\u0094\t\b\2\2\u0094*\3\2\2\2\u0095\u0096\t\t\2\2\u0096,\3"+
		"\2\2\2\u0097\u0098\t\n\2\2\u0098.\3\2\2\2\u0099\u009a\t\13\2\2\u009a\60"+
		"\3\2\2\2\u009b\u00a0\5-\27\2\u009c\u00a0\5/\30\2\u009d\u00a0\5+\26\2\u009e"+
		"\u00a0\7a\2\2\u009f\u009b\3\2\2\2\u009f\u009c\3\2\2\2\u009f\u009d\3\2"+
		"\2\2\u009f\u009e\3\2\2\2\u00a0\62\3\2\2\2\u00a1\u00a3\t\f\2\2\u00a2\u00a1"+
		"\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5"+
		"\u00a6\3\2\2\2\u00a6\u00a7\b\32\2\2\u00a7\64\3\2\2\2\u00a8\u00a9\7\61"+
		"\2\2\u00a9\u00aa\7\61\2\2\u00aa\u00ae\3\2\2\2\u00ab\u00ad\n\r\2\2\u00ac"+
		"\u00ab\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2"+
		"\2\2\u00af\u00b1\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b2\b\33\2\2\u00b2"+
		"\66\3\2\2\2\u00b3\u00b4\7\61\2\2\u00b4\u00b5\7,\2\2\u00b5\u00b9\3\2\2"+
		"\2\u00b6\u00b8\13\2\2\2\u00b7\u00b6\3\2\2\2\u00b8\u00bb\3\2\2\2\u00b9"+
		"\u00ba\3\2\2\2\u00b9\u00b7\3\2\2\2\u00ba\u00bc\3\2\2\2\u00bb\u00b9\3\2"+
		"\2\2\u00bc\u00bd\7,\2\2\u00bd\u00be\7\61\2\2\u00be\u00bf\3\2\2\2\u00bf"+
		"\u00c0\b\34\2\2\u00c08\3\2\2\2\u00c1\u00c5\5/\30\2\u00c2\u00c4\5\61\31"+
		"\2\u00c3\u00c2\3\2\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c5\u00c6"+
		"\3\2\2\2\u00c6:\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c8\u00cc\5-\27\2\u00c9"+
		"\u00cb\5\61\31\2\u00ca\u00c9\3\2\2\2\u00cb\u00ce\3\2\2\2\u00cc\u00ca\3"+
		"\2\2\2\u00cc\u00cd\3\2\2\2\u00cd<\3\2\2\2\u00ce\u00cc\3\2\2\2\13\2\u0088"+
		"\u008d\u009f\u00a4\u00ae\u00b9\u00c5\u00cc\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}