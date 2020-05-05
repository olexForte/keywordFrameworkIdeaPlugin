
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import ctlang.psi.CTTypes;

%%

%class CTLexer
%implements com.intellij.lexer.FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

CRLF=\R
WHITE_SPACE= [\t\n\f] //[^a-zA-Z0-9_ .:';]
END_OF_LINE_COMMENT=("#"|"!")[^\r\n]*
SEPARATOR=[']
PARAMETER_SEPARATOR=[:]
PROPERTY_SEPARATOR=[.]
STRING= [a-zA-Z0-9_ ;]
PROPERTY_STRING = [^\t\n\f':]

%state WAITING_PROPERTY
%state WAITING_VALUE
%state WAITING_DETAILS
%%

<YYINITIAL> {END_OF_LINE_COMMENT}                           { yybegin(YYINITIAL); return CTTypes.COMMENT; }

<YYINITIAL> {STRING}+                                    { yybegin(YYINITIAL); return CTTypes.STRING; }

<YYINITIAL> {SEPARATOR}                                     { yybegin(WAITING_PROPERTY); return CTTypes.SEPARATOR; }

<WAITING_PROPERTY> {SEPARATOR}                               { yybegin(YYINITIAL); return CTTypes.SEPARATOR; }

<WAITING_PROPERTY> {PROPERTY_SEPARATOR}                     { yybegin(WAITING_VALUE); return CTTypes.PROPERTY_SEPARATOR; }

<WAITING_PROPERTY> {PARAMETER_SEPARATOR}                             { yybegin(WAITING_PROPERTY); return CTTypes.PARAMETER_SEPARATOR; }

<WAITING_PROPERTY> {PROPERTY_STRING}+                              { yybegin(WAITING_PROPERTY); return CTTypes.KEY; }

<WAITING_VALUE> {PROPERTY_SEPARATOR}                     { yybegin(WAITING_VALUE); return CTTypes.PROPERTY_SEPARATOR; }

<WAITING_VALUE> {PARAMETER_SEPARATOR}                             { yybegin(WAITING_PROPERTY); return CTTypes.PARAMETER_SEPARATOR; }

<WAITING_VALUE> {SEPARATOR}                                     { yybegin(YYINITIAL); return CTTypes.SEPARATOR; }

<WAITING_VALUE> {PROPERTY_STRING}+                              { yybegin(WAITING_VALUE); return CTTypes.VALUE; }

<WAITING_DETAILS> {SEPARATOR}                                     { yybegin(WAITING_PROPERTY); return CTTypes.SEPARATOR; }

<WAITING_DETAILS> {STRING}+                                    { yybegin(WAITING_DETAILS); return CTTypes.STRING; }


//<WAITING_VALUE> {CRLF}({CRLF}|{WHITE_SPACE})+               { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }
//

//
//<WAITING_VALUE> {FIRST_VALUE_CHARACTER}{VALUE_CHARACTER}*   { yybegin(YYINITIAL); return CTTypes.VALUE; }

{WHITE_SPACE}                             { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

({CRLF})+                                     { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }

//{COMMAND_END}                                 { yybegin(YYINITIAL); return TokenType.WHITE_SPACE; }



[^]                                                         { return TokenType.BAD_CHARACTER; }