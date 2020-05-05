package proplang.psi;

import com.intellij.psi.tree.IElementType;
import proplang.PropLanguage;
import org.jetbrains.annotations.*;

public class PropTokenType extends IElementType {
    public PropTokenType(@NotNull @NonNls String debugName) {
        super(debugName, PropLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "CTTokenType." + super.toString();
    }
}