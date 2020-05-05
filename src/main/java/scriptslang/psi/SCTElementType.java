package scriptslang.psi;

import com.intellij.psi.tree.IElementType;
import scriptslang.SCTLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class SCTElementType extends IElementType {
    public SCTElementType(@NotNull @NonNls String debugName) {
        super(debugName,SCTLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "SCTTokenType." + super.toString();
    }
}