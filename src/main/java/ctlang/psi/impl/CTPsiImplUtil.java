package ctlang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import ctlang.CTElementFactory;
import ctlang.psi.CTProperty;
import ctlang.psi.CTTypes;

public class CTPsiImplUtil {
    public static String getKey(CTProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(CTTypes.KEY);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to ct spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static String getValue(CTProperty element) {
        ASTNode valueNode = element.getNode().findChildByType(CTTypes.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }

//    public static String getParameter(CTProperty element) {
//        ASTNode valueNode = element.getNode().findChildByType(CTTypes.PARAMETER);
//        if (valueNode != null) {
//            return valueNode.getText();
//        } else {
//            return null;
//        }
//    }

    public static String getName(CTProperty element) {
        return getKey(element);
    }

    public static PsiElement setName(CTProperty element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(CTTypes.KEY);
        if (keyNode != null) {

            CTProperty property = CTElementFactory.createProperty(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(CTProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(CTTypes.KEY);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }


}