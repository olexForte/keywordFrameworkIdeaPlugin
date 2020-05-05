// This is a generated file. Not intended for manual editing.
package ctlang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static ctlang.psi.CTTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class CTParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return ctFile(b, l + 1);
  }

  /* ********************************************************** */
  // ( command_part [(props)*] ) | command_part
  public static boolean command(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = command_0(b, l + 1);
    if (!r) r = command_part(b, l + 1);
    exit_section_(b, m, COMMAND, r);
    return r;
  }

  // command_part [(props)*]
  private static boolean command_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = command_part(b, l + 1);
    r = r && command_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // [(props)*]
  private static boolean command_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_0_1")) return false;
    command_0_1_0(b, l + 1);
    return true;
  }

  // (props)*
  private static boolean command_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_0_1_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!command_0_1_0_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "command_0_1_0", c)) break;
    }
    return true;
  }

  // (props)
  private static boolean command_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_0_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = props(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // STRING
  public static boolean command_part(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "command_part")) return false;
    if (!nextTokenIs(b, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, STRING);
    exit_section_(b, m, COMMAND_PART, r);
    return r;
  }

  /* ********************************************************** */
  // item_*
  static boolean ctFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ctFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ctFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // command|COMMENT|CRLF
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    r = command(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, CRLF);
    return r;
  }

  /* ********************************************************** */
  // ( KEY PROPERTY_SEPARATOR? VALUE? [PARAMETER_SEPARATOR] ) | KEY
  public static boolean property(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property")) return false;
    if (!nextTokenIs(b, KEY)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = property_0(b, l + 1);
    if (!r) r = consumeToken(b, KEY);
    exit_section_(b, m, PROPERTY, r);
    return r;
  }

  // KEY PROPERTY_SEPARATOR? VALUE? [PARAMETER_SEPARATOR]
  private static boolean property_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, KEY);
    r = r && property_0_1(b, l + 1);
    r = r && property_0_2(b, l + 1);
    r = r && property_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PROPERTY_SEPARATOR?
  private static boolean property_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_0_1")) return false;
    consumeToken(b, PROPERTY_SEPARATOR);
    return true;
  }

  // VALUE?
  private static boolean property_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_0_2")) return false;
    consumeToken(b, VALUE);
    return true;
  }

  // [PARAMETER_SEPARATOR]
  private static boolean property_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "property_0_3")) return false;
    consumeToken(b, PARAMETER_SEPARATOR);
    return true;
  }

  /* ********************************************************** */
  // (SEPARATOR property* SEPARATOR [STRING]) | (SEPARATOR property* SEPARATOR)
  static boolean props(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "props")) return false;
    if (!nextTokenIs(b, SEPARATOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = props_0(b, l + 1);
    if (!r) r = props_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SEPARATOR property* SEPARATOR [STRING]
  private static boolean props_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "props_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEPARATOR);
    r = r && props_0_1(b, l + 1);
    r = r && consumeToken(b, SEPARATOR);
    r = r && props_0_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // property*
  private static boolean props_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "props_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!property(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "props_0_1", c)) break;
    }
    return true;
  }

  // [STRING]
  private static boolean props_0_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "props_0_3")) return false;
    consumeToken(b, STRING);
    return true;
  }

  // SEPARATOR property* SEPARATOR
  private static boolean props_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "props_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEPARATOR);
    r = r && props_1_1(b, l + 1);
    r = r && consumeToken(b, SEPARATOR);
    exit_section_(b, m, null, r);
    return r;
  }

  // property*
  private static boolean props_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "props_1_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!property(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "props_1_1", c)) break;
    }
    return true;
  }

}
