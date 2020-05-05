package proplang.psi;

import com.intellij.psi.tree.IElementType;
import proplang.PropLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class PropElementType extends IElementType {
    public PropElementType(@NotNull @NonNls String debugName) {
        super(debugName, PropLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "CTTokenType." + super.toString();
    }
}