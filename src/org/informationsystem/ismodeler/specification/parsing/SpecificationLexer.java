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
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, WS=17, 
		Line_comment=18, Block_comment=19, Upper_word=20, Lower_word=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
		"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "Do_char", 
		"Sq_char", "Sign", "Exponent", "Non_zero_numeric", "Numeric", "Lower_alpha", 
		"Upper_alpha", "Alpha_numeric", "WS", "Line_comment", "Block_comment", 
		"Upper_word", "Lower_word"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'process'", "'{'", "'}'", "'place'", "'('", "')'", "'transition'", 
		"','", "':'", "';'", "'register'", "'deregister'", "'insert'", "'into'", 
		"'remove'", "'from'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, "WS", "Line_comment", "Block_comment", "Upper_word", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27\u00dc\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n"+
		"\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\22\3\22\3\22\5\22\u0096\n\22\3\23\3\23\3\23\5\23\u009b\n\23\3"+
		"\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3"+
		"\32\3\32\5\32\u00ad\n\32\3\33\6\33\u00b0\n\33\r\33\16\33\u00b1\3\33\3"+
		"\33\3\34\3\34\3\34\3\34\7\34\u00ba\n\34\f\34\16\34\u00bd\13\34\3\34\3"+
		"\34\3\35\3\35\3\35\3\35\7\35\u00c5\n\35\f\35\16\35\u00c8\13\35\3\35\3"+
		"\35\3\35\3\35\3\35\3\36\3\36\7\36\u00d1\n\36\f\36\16\36\u00d4\13\36\3"+
		"\37\3\37\7\37\u00d8\n\37\f\37\16\37\u00db\13\37\3\u00c6\2 \3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\2"+
		"%\2\'\2)\2+\2-\2/\2\61\2\63\2\65\23\67\249\25;\26=\27\3\2\16\5\2\"#%]"+
		"_\u0080\4\2$$^^\5\2\"(*]_\u0080\4\2))^^\4\2--//\4\2GGgg\3\2\63;\3\2\62"+
		";\3\2c|\3\2C\\\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u00dc\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2\65\3\2\2\2\2\67\3\2"+
		"\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\3?\3\2\2\2\5G\3\2\2\2\7I\3\2\2\2"+
		"\tK\3\2\2\2\13Q\3\2\2\2\rS\3\2\2\2\17U\3\2\2\2\21`\3\2\2\2\23b\3\2\2\2"+
		"\25d\3\2\2\2\27f\3\2\2\2\31o\3\2\2\2\33z\3\2\2\2\35\u0081\3\2\2\2\37\u0086"+
		"\3\2\2\2!\u008d\3\2\2\2#\u0095\3\2\2\2%\u009a\3\2\2\2\'\u009c\3\2\2\2"+
		")\u009e\3\2\2\2+\u00a0\3\2\2\2-\u00a2\3\2\2\2/\u00a4\3\2\2\2\61\u00a6"+
		"\3\2\2\2\63\u00ac\3\2\2\2\65\u00af\3\2\2\2\67\u00b5\3\2\2\29\u00c0\3\2"+
		"\2\2;\u00ce\3\2\2\2=\u00d5\3\2\2\2?@\7r\2\2@A\7t\2\2AB\7q\2\2BC\7e\2\2"+
		"CD\7g\2\2DE\7u\2\2EF\7u\2\2F\4\3\2\2\2GH\7}\2\2H\6\3\2\2\2IJ\7\177\2\2"+
		"J\b\3\2\2\2KL\7r\2\2LM\7n\2\2MN\7c\2\2NO\7e\2\2OP\7g\2\2P\n\3\2\2\2QR"+
		"\7*\2\2R\f\3\2\2\2ST\7+\2\2T\16\3\2\2\2UV\7v\2\2VW\7t\2\2WX\7c\2\2XY\7"+
		"p\2\2YZ\7u\2\2Z[\7k\2\2[\\\7v\2\2\\]\7k\2\2]^\7q\2\2^_\7p\2\2_\20\3\2"+
		"\2\2`a\7.\2\2a\22\3\2\2\2bc\7<\2\2c\24\3\2\2\2de\7=\2\2e\26\3\2\2\2fg"+
		"\7t\2\2gh\7g\2\2hi\7i\2\2ij\7k\2\2jk\7u\2\2kl\7v\2\2lm\7g\2\2mn\7t\2\2"+
		"n\30\3\2\2\2op\7f\2\2pq\7g\2\2qr\7t\2\2rs\7g\2\2st\7i\2\2tu\7k\2\2uv\7"+
		"u\2\2vw\7v\2\2wx\7g\2\2xy\7t\2\2y\32\3\2\2\2z{\7k\2\2{|\7p\2\2|}\7u\2"+
		"\2}~\7g\2\2~\177\7t\2\2\177\u0080\7v\2\2\u0080\34\3\2\2\2\u0081\u0082"+
		"\7k\2\2\u0082\u0083\7p\2\2\u0083\u0084\7v\2\2\u0084\u0085\7q\2\2\u0085"+
		"\36\3\2\2\2\u0086\u0087\7t\2\2\u0087\u0088\7g\2\2\u0088\u0089\7o\2\2\u0089"+
		"\u008a\7q\2\2\u008a\u008b\7x\2\2\u008b\u008c\7g\2\2\u008c \3\2\2\2\u008d"+
		"\u008e\7h\2\2\u008e\u008f\7t\2\2\u008f\u0090\7q\2\2\u0090\u0091\7o\2\2"+
		"\u0091\"\3\2\2\2\u0092\u0096\t\2\2\2\u0093\u0094\7^\2\2\u0094\u0096\t"+
		"\3\2\2\u0095\u0092\3\2\2\2\u0095\u0093\3\2\2\2\u0096$\3\2\2\2\u0097\u009b"+
		"\t\4\2\2\u0098\u0099\7^\2\2\u0099\u009b\t\5\2\2\u009a\u0097\3\2\2\2\u009a"+
		"\u0098\3\2\2\2\u009b&\3\2\2\2\u009c\u009d\t\6\2\2\u009d(\3\2\2\2\u009e"+
		"\u009f\t\7\2\2\u009f*\3\2\2\2\u00a0\u00a1\t\b\2\2\u00a1,\3\2\2\2\u00a2"+
		"\u00a3\t\t\2\2\u00a3.\3\2\2\2\u00a4\u00a5\t\n\2\2\u00a5\60\3\2\2\2\u00a6"+
		"\u00a7\t\13\2\2\u00a7\62\3\2\2\2\u00a8\u00ad\5/\30\2\u00a9\u00ad\5\61"+
		"\31\2\u00aa\u00ad\5-\27\2\u00ab\u00ad\7a\2\2\u00ac\u00a8\3\2\2\2\u00ac"+
		"\u00a9\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac\u00ab\3\2\2\2\u00ad\64\3\2\2"+
		"\2\u00ae\u00b0\t\f\2\2\u00af\u00ae\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00af"+
		"\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4\b\33\2\2"+
		"\u00b4\66\3\2\2\2\u00b5\u00b6\7\61\2\2\u00b6\u00b7\7\61\2\2\u00b7\u00bb"+
		"\3\2\2\2\u00b8\u00ba\n\r\2\2\u00b9\u00b8\3\2\2\2\u00ba\u00bd\3\2\2\2\u00bb"+
		"\u00b9\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00be\3\2\2\2\u00bd\u00bb\3\2"+
		"\2\2\u00be\u00bf\b\34\2\2\u00bf8\3\2\2\2\u00c0\u00c1\7\61\2\2\u00c1\u00c2"+
		"\7,\2\2\u00c2\u00c6\3\2\2\2\u00c3\u00c5\13\2\2\2\u00c4\u00c3\3\2\2\2\u00c5"+
		"\u00c8\3\2\2\2\u00c6\u00c7\3\2\2\2\u00c6\u00c4\3\2\2\2\u00c7\u00c9\3\2"+
		"\2\2\u00c8\u00c6\3\2\2\2\u00c9\u00ca\7,\2\2\u00ca\u00cb\7\61\2\2\u00cb"+
		"\u00cc\3\2\2\2\u00cc\u00cd\b\35\2\2\u00cd:\3\2\2\2\u00ce\u00d2\5\61\31"+
		"\2\u00cf\u00d1\5\63\32\2\u00d0\u00cf\3\2\2\2\u00d1\u00d4\3\2\2\2\u00d2"+
		"\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3<\3\2\2\2\u00d4\u00d2\3\2\2\2"+
		"\u00d5\u00d9\5/\30\2\u00d6\u00d8\5\63\32\2\u00d7\u00d6\3\2\2\2\u00d8\u00db"+
		"\3\2\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da>\3\2\2\2\u00db"+
		"\u00d9\3\2\2\2\13\2\u0095\u009a\u00ac\u00b1\u00bb\u00c6\u00d2\u00d9\3"+
		"\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}