package main.java.ctlang.psi;

import com.intellij.psi.tree.IElementType;
import main.java.ctlang.CTLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CTTokenType extends IElementType {
    public CTTokenType(@NotNull @NonNls String debugName) {
        super(debugName,CTLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "CTTokenType." + super.toString();
    }
}