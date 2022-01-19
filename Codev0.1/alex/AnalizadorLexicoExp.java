package alex;
import errors.GestionErroresExp;
import java.util.ArrayList;
import constructorast.ClaseLexica;


public class AnalizadorLexicoExp implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;

  public boolean hayError;
  private ArrayList<String> listaErrores;
  private int cont;
  private ALexOperations ops;
  private GestionErroresExp errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int col() {return yychar+1 - cont;}
  public void fijaGestionErrores(GestionErroresExp errores) {
   this.errores = errores;
  }
  public void setFileName(String file){
    this.ops.setFileName(file);
    }
 public void printErrores(){
 for(String s: listaErrores)
    System.out.println(s);
    }
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public AnalizadorLexicoExp (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public AnalizadorLexicoExp (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private AnalizadorLexicoExp () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

  hayError = false;
  listaErrores = new ArrayList<String>();
  ops = new ALexOperations(this);
  errores = new GestionErroresExp(); // Será sobreescrito por el init del Sintactico
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NOT_ACCEPT,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NOT_ACCEPT,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NOT_ACCEPT,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NOT_ACCEPT,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NOT_ACCEPT,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NOT_ACCEPT,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"53:8,2:2,1,53:2,2,53:18,2,40,52,26,53,37,32,4,44,45,36,30,35,31,29,3,27,28:" +
"9,34,33,41,39,42,53:2,51:26,46,53,47,38,53:2,12,17,10,20,6,15,22,18,14,50,2" +
"5,11,21,9,16,24,50,5,13,7,8,19,23,50:3,48,43,49,53:8601,36,53:56808,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,156,
"0,1:3,2,3,4,5,1,6,7,8,1:4,9,10,11,12,13,14,1:6,15,1,16,1:13,16:3,17,16:19,1" +
",18,19,20,21,22,23,24,17,25,26,25,1,27,28,29,30,31,32,33,34,35,36,37,38,39," +
"40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64," +
"65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89," +
"90,91,92,16,93,94,95,96,97,98,16,99,100")[0];

	private int yy_nxt[][] = unpackFromString(101,54,
"1,2,3,4,5,6,126,127,142,105,128,153:2,154,70,106,153,129,153,130,155,153:2," +
"143,144,153,69,7,71,8,9,10,11,12,13,14,15,16,17,18,19,20,21,73,22,23,24,25," +
"26,27,153:2,76,79,-1:57,28,-1:30,29,-1:21,68:2,-1,68:47,-1,68,-1:5,153,145," +
"153:19,-1,146:2,-1:21,153,146,-1:31,75,-1:54,31,-1:54,32,-1:2,33,-1:51,34,-" +
"1:55,35,-1:53,36,-1:58,37,-1:53,38,-1:53,39,-1:53,40,-1:16,28:52,-1:5,153:2" +
"1,-1,146:2,-1:21,153,146,-1:29,47:2,-1:29,43,-1:63,72,-1:44,153:4,77,153:5," +
"30,153:10,-1,146:2,-1:21,153,146,-1:29,71:2,75,-1:33,81,-1:87,41,-1:15,153:" +
"18,44,153:2,-1,146:2,-1:21,153,146,-1:4,78:2,-1,78:47,42,78,-1:5,153:2,45,1" +
"53:18,-1,146:2,-1:21,153,146,-1:7,46,153:20,-1,146:2,-1:21,153,146,-1:12,83" +
",-1:48,153:16,48,153:4,-1,146:2,-1:21,153,146,-1:13,85,-1:47,153,49,153:19," +
"-1,146:2,-1:21,153,146,-1:10,87,-1:50,153,50,153:19,-1,146:2,-1:21,153,146," +
"-1:22,89,-1:38,153,51,153:19,-1,146:2,-1:21,153,146,-1:8,67,-1:52,52,153:20" +
",-1,146:2,-1:21,153,146,-1:7,153:6,53,153:14,-1,146:2,-1:21,153,146,-1:7,15" +
"3:15,54,153:5,-1,146:2,-1:21,153,146,-1:7,153:17,55,153:3,-1,146:2,-1:21,15" +
"3,146,-1:7,153:8,56,153:12,-1,146:2,-1:21,153,146,-1:7,153:2,57,153:18,-1,1" +
"46:2,-1:21,153,146,-1:7,153,58,153:19,-1,146:2,-1:21,153,146,-1:7,153:20,59" +
",-1,146:2,-1:21,153,146,-1:7,153,60,153:19,-1,146:2,-1:21,153,146,-1:7,153:" +
"2,61,153:18,-1,146:2,-1:21,153,146,-1:7,153:2,62,153:18,-1,146:2,-1:21,153," +
"146,-1:7,153:4,63,153:16,-1,146:2,-1:21,153,146,-1:7,153:13,64,153:7,-1,146" +
":2,-1:21,153,146,-1:7,153:2,65,153:18,-1,146:2,-1:21,153,146,-1:7,153,66,15" +
"3:19,-1,146:2,-1:21,153,146,-1:7,153,74,153:19,-1,146:2,-1:21,153,146,-1:7," +
"153:6,133,134,153:3,80,153:9,-1,146:2,-1:21,153,146,-1:7,153:3,82,153:17,-1" +
",146:2,-1:21,153,146,-1:7,153:8,84,153:12,-1,146:2,-1:21,153,146,-1:7,153:3" +
",86,153:17,-1,146:2,-1:21,153,146,-1:7,153:8,88,153:12,-1,146:2,-1:21,153,1" +
"46,-1:7,153:7,90,153:13,-1,146:2,-1:21,153,146,-1:7,153:11,91,153:9,-1,146:" +
"2,-1:21,153,146,-1:7,153:9,92,153:11,-1,146:2,-1:21,153,146,-1:7,153:4,93,1" +
"53:16,-1,146:2,-1:21,153,146,-1:7,153:8,94,153:12,-1,146:2,-1:21,153,146,-1" +
":7,153:7,95,153:13,-1,146:2,-1:21,153,146,-1:7,153:8,96,153:12,-1,146:2,-1:" +
"21,153,146,-1:7,153:7,97,153:13,-1,146:2,-1:21,153,146,-1:7,153:6,98,153:14" +
",-1,146:2,-1:21,153,146,-1:7,153:4,99,153:16,-1,146:2,-1:21,153,146,-1:7,15" +
"3:4,100,153:16,-1,146:2,-1:21,153,146,-1:7,101,153:20,-1,146:2,-1:21,153,14" +
"6,-1:7,153:5,102,153:15,-1,146:2,-1:21,153,146,-1:7,153:6,103,153:14,-1,146" +
":2,-1:21,153,146,-1:7,153:3,104,153:17,-1,146:2,-1:21,153,146,-1:7,153:4,10" +
"7,153,108,153:14,-1,146:2,-1:21,153,146,-1:7,109,153:20,-1,146:2,-1:21,153," +
"146,-1:7,153:6,132,110,153:3,147,153,111,153:7,-1,146:2,-1:21,153,146,-1:7," +
"135,153:10,112,153:9,-1,146:2,-1:21,153,146,-1:7,153:11,113,153:9,-1,146:2," +
"-1:21,153,146,-1:7,153:9,114,153:11,-1,146:2,-1:21,153,146,-1:7,153:7,115,1" +
"53:13,-1,146:2,-1:21,153,146,-1:7,153:11,116,153:9,-1,146:2,-1:21,153,146,-" +
"1:7,153:6,117,153:14,-1,146:2,-1:21,153,146,-1:7,153,118,153:19,-1,146:2,-1" +
":21,153,146,-1:7,153:9,119,153:11,-1,146:2,-1:21,153,146,-1:7,153:3,120,153" +
":5,121,153:11,-1,146:2,-1:21,153,146,-1:7,153:3,122,153:17,-1,146:2,-1:21,1" +
"53,146,-1:7,153:2,123,153:18,-1,146:2,-1:21,153,146,-1:7,153:3,124,153:17,-" +
"1,146:2,-1:21,153,146,-1:7,153:4,125,153:16,-1,146:2,-1:21,153,146,-1:7,153" +
":8,131,153:12,-1,146:2,-1:21,153,146,-1:7,153:13,136,153:7,-1,146:2,-1:21,1" +
"53,146,-1:7,137,153:20,-1,146:2,-1:21,153,146,-1:7,153:2,138,153:18,-1,146:" +
"2,-1:21,153,146,-1:7,153:4,150,153:16,-1,146:2,-1:21,153,146,-1:7,153:9,139" +
",153:11,-1,146:2,-1:21,153,146,-1:7,153:10,151,153:10,-1,146:2,-1:21,153,14" +
"6,-1:7,153:2,152,153:18,-1,146:2,-1:21,153,146,-1:7,153:7,140,153:13,-1,146" +
":2,-1:21,153,146,-1:7,153:9,141,153:11,-1,146:2,-1:21,153,146,-1:7,153:18,1" +
"48,153:2,-1,146:2,-1:21,153,146,-1:7,153,149,153:19,-1,146:2,-1:21,153,146," +
"-1:2");

	public UnidadLexica next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

  return ops.ul(ClaseLexica.EOF,"<EOF>");
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{cont += col();}
					case -3:
						break;
					case 3:
						{}
					case -4:
						break;
					case 4:
						{return ops.ul(ClaseLexica.DIV, "/");}
					case -5:
						break;
					case 5:
						{listaErrores.add(errores.errorLexico(fila(), col(),lexema())); hayError = true; }
					case -6:
						break;
					case 6:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -7:
						break;
					case 7:
						{return ops.ul(ClaseLexica.NUMENT, lexema());}
					case -8:
						break;
					case 8:
						{return ops.ul(ClaseLexica.PUNT, lexema());}
					case -9:
						break;
					case 9:
						{return ops.ul(ClaseLexica.MAS, "+");}
					case -10:
						break;
					case 10:
						{return ops.ul(ClaseLexica.MINUS, "-");}
					case -11:
						break;
					case 11:
						{return ops.ul(ClaseLexica.DIRMEM, lexema());}
					case -12:
						break;
					case 12:
						{return ops.ul(ClaseLexica.PUNTOCOMA, lexema());}
					case -13:
						break;
					case 13:
						{return ops.ul(ClaseLexica.DOSPUNTOS, lexema());}
					case -14:
						break;
					case 14:
						{return ops.ul(ClaseLexica.COMA, lexema());}
					case -15:
						break;
					case 15:
						{return ops.ul(ClaseLexica.POR, "*");}
					case -16:
						break;
					case 16:
						{return ops.ul(ClaseLexica.MOD, "%");}
					case -17:
						break;
					case 17:
						{return ops.ul(ClaseLexica.EXP, lexema());}
					case -18:
						break;
					case 18:
						{return ops.ul(ClaseLexica.IGUAL, "=");}
					case -19:
						break;
					case 19:
						{return ops.ul(ClaseLexica.NOT, "!");}
					case -20:
						break;
					case 20:
						{return ops.ul(ClaseLexica.MENOR, "<");}
					case -21:
						break;
					case 21:
						{return ops.ul(ClaseLexica.MAYOR, ">");}
					case -22:
						break;
					case 22:
						{return ops.ul(ClaseLexica.PAP,"(");}
					case -23:
						break;
					case 23:
						{return ops.ul(ClaseLexica.PCIERRE,")");}
					case -24:
						break;
					case 24:
						{return ops.ul(ClaseLexica.CAP,"[");}
					case -25:
						break;
					case 25:
						{return ops.ul(ClaseLexica.CCIERRE,"]");}
					case -26:
						break;
					case 26:
						{return ops.ul(ClaseLexica.LAP,"{");}
					case -27:
						break;
					case 27:
						{return ops.ul(ClaseLexica.LCIERRE,"}");}
					case -28:
						break;
					case 28:
						{}
					case -29:
						break;
					case 29:
						{return ops.ul(ClaseLexica.DIVBIS, "/:");}
					case -30:
						break;
					case 30:
						{return ops.ul(ClaseLexica.IF, lexema());}
					case -31:
						break;
					case 31:
						{return ops.ul(ClaseLexica.MASMAS, lexema());}
					case -32:
						break;
					case 32:
						{return ops.ul(ClaseLexica.MENOSMENOS, lexema());}
					case -33:
						break;
					case 33:
						{return ops.ul(ClaseLexica.MINUSBIS, "-");}
					case -34:
						break;
					case 34:
						{return ops.ul(ClaseLexica.AND, "&&");}
					case -35:
						break;
					case 35:
						{return ops.ul(ClaseLexica.MODBIS, "%:");}
					case -36:
						break;
					case 36:
						{return ops.ul(ClaseLexica.EXPBIS, lexema());}
					case -37:
						break;
					case 37:
						{return ops.ul(ClaseLexica.IGUALLOG, "==");}
					case -38:
						break;
					case 38:
						{return ops.ul(ClaseLexica.NOTIGUALLOG, "!=");}
					case -39:
						break;
					case 39:
						{return ops.ul(ClaseLexica.MENORIGUAL, "<=");}
					case -40:
						break;
					case 40:
						{return ops.ul(ClaseLexica.MAYORIGUAL, ">=");}
					case -41:
						break;
					case 41:
						{return ops.ul(ClaseLexica.OR, "||");}
					case -42:
						break;
					case 42:
						{String s = lexema(); s = s.substring(1,s.length() - 1);
                             return ops.ul(ClaseLexica.STRING, s);}
					case -43:
						break;
					case 43:
						{return ops.ul(ClaseLexica.CARACTER, lexema());}
					case -44:
						break;
					case 44:
						{return ops.ul(ClaseLexica.NEW, lexema());}
					case -45:
						break;
					case 45:
						{return ops.ul(ClaseLexica.INT, lexema());}
					case -46:
						break;
					case 46:
						{return ops.ul(ClaseLexica.FOR, lexema());}
					case -47:
						break;
					case 47:
						{return ops.ul(ClaseLexica.NUMREAL, lexema());}
					case -48:
						break;
					case 48:
						{return ops.ul(ClaseLexica.ENUM, lexema());}
					case -49:
						break;
					case 49:
						{return ops.ul(ClaseLexica.ELSE, lexema());}
					case -50:
						break;
					case 50:
						{return ops.ul(ClaseLexica.TRUE, "true");}
					case -51:
						break;
					case 51:
						{return ops.ul(ClaseLexica.CASE, lexema());}
					case -52:
						break;
					case 52:
						{return ops.ul(ClaseLexica.CHAR, lexema());}
					case -53:
						break;
					case 53:
						{return ops.ul(ClaseLexica.BOOL, lexema());}
					case -54:
						break;
					case 54:
						{return ops.ul(ClaseLexica.VOID, lexema());}
					case -55:
						break;
					case 55:
						{return ops.ul(ClaseLexica.USING, lexema());}
					case -56:
						break;
					case 56:
						{return ops.ul(ClaseLexica.CLASS, lexema());}
					case -57:
						break;
					case 57:
						{return ops.ul(ClaseLexica.FLOAT, lexema());}
					case -58:
						break;
					case 58:
						{return ops.ul(ClaseLexica.FALSE, "false");}
					case -59:
						break;
					case 59:
						{return ops.ul(ClaseLexica.BREAK, lexema());}
					case -60:
						break;
					case 60:
						{return ops.ul(ClaseLexica.WHILE, lexema());}
					case -61:
						break;
					case 61:
						{return ops.ul(ClaseLexica.PRUNT, lexema());}
					case -62:
						break;
					case 62:
						{return ops.ul(ClaseLexica.PRINT, lexema());}
					case -63:
						break;
					case 63:
						{return ops.ul(ClaseLexica.RETURN, lexema());}
					case -64:
						break;
					case 64:
						{return ops.ul(ClaseLexica.SWITCH, lexema());}
					case -65:
						break;
					case 65:
						{return ops.ul(ClaseLexica.DEFAULT, lexema());}
					case -66:
						break;
					case 66:
						{return ops.ul(ClaseLexica.CONTINUE, lexema());}
					case -67:
						break;
					case 67:
						{return ops.ul(ClaseLexica.INCLUDE, lexema());}
					case -68:
						break;
					case 69:
						{listaErrores.add(errores.errorLexico(fila(), col(),lexema())); hayError = true; }
					case -69:
						break;
					case 70:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -70:
						break;
					case 71:
						{return ops.ul(ClaseLexica.NUMENT, lexema());}
					case -71:
						break;
					case 73:
						{listaErrores.add(errores.errorLexico(fila(), col(),lexema())); hayError = true; }
					case -72:
						break;
					case 74:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -73:
						break;
					case 76:
						{listaErrores.add(errores.errorLexico(fila(), col(),lexema())); hayError = true; }
					case -74:
						break;
					case 77:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -75:
						break;
					case 79:
						{listaErrores.add(errores.errorLexico(fila(), col(),lexema())); hayError = true; }
					case -76:
						break;
					case 80:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -77:
						break;
					case 82:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -78:
						break;
					case 84:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -79:
						break;
					case 86:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -80:
						break;
					case 88:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -81:
						break;
					case 90:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -82:
						break;
					case 91:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -83:
						break;
					case 92:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -84:
						break;
					case 93:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -85:
						break;
					case 94:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -86:
						break;
					case 95:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -87:
						break;
					case 96:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -88:
						break;
					case 97:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -89:
						break;
					case 98:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -90:
						break;
					case 99:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -91:
						break;
					case 100:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -92:
						break;
					case 101:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -93:
						break;
					case 102:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -94:
						break;
					case 103:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -95:
						break;
					case 104:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -96:
						break;
					case 105:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -97:
						break;
					case 106:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -98:
						break;
					case 107:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -99:
						break;
					case 108:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -100:
						break;
					case 109:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -101:
						break;
					case 110:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -102:
						break;
					case 111:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -103:
						break;
					case 112:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -104:
						break;
					case 113:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -105:
						break;
					case 114:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -106:
						break;
					case 115:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -107:
						break;
					case 116:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -108:
						break;
					case 117:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -109:
						break;
					case 118:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -110:
						break;
					case 119:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -111:
						break;
					case 120:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -112:
						break;
					case 121:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -113:
						break;
					case 122:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -114:
						break;
					case 123:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -115:
						break;
					case 124:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -116:
						break;
					case 125:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -117:
						break;
					case 126:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -118:
						break;
					case 127:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -119:
						break;
					case 128:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -120:
						break;
					case 129:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -121:
						break;
					case 130:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -122:
						break;
					case 131:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -123:
						break;
					case 132:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -124:
						break;
					case 133:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -125:
						break;
					case 134:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -126:
						break;
					case 135:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -127:
						break;
					case 136:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -128:
						break;
					case 137:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -129:
						break;
					case 138:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -130:
						break;
					case 139:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -131:
						break;
					case 140:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -132:
						break;
					case 141:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -133:
						break;
					case 142:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -134:
						break;
					case 143:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -135:
						break;
					case 144:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -136:
						break;
					case 145:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -137:
						break;
					case 146:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -138:
						break;
					case 147:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -139:
						break;
					case 148:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -140:
						break;
					case 149:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -141:
						break;
					case 150:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -142:
						break;
					case 151:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -143:
						break;
					case 152:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -144:
						break;
					case 153:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -145:
						break;
					case 154:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -146:
						break;
					case 155:
						{return ops.ul(ClaseLexica.IDEN, lexema());}
					case -147:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
