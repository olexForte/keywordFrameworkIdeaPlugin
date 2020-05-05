// This is a generated file. Not intended for manual editing.
package scriptslang.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static scriptslang.psi.SCTTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class SCTParser implements PsiParser, LightPsiParser {

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
    return sctFile(b, l + 1);
  }

  /* ********************************************************** */
  // scriptfile|COMMENT|CRLF
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    r = scriptfile(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = consumeToken(b, CRLF);
    return r;
  }

  /* ********************************************************** */
  // (VALUE?) | VALUE
  public static boolean scriptfile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "scriptfile")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SCRIPTFILE, "<scriptfile>");
    r = scriptfile_0(b, l + 1);
    if (!r) r = consumeToken(b, VALUE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // VALUE?
  private static boolean scriptfile_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "scriptfile_0")) return false;
    consumeToken(b, VALUE);
    return true;
  }

  /* ********************************************************** */
  // item_*
  static boolean sctFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "sctFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "sctFile", c)) break;
    }
    return true;
  }

}
