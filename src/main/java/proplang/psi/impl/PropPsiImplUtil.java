package proplang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import proplang.PropElementFactory;
import proplang.psi.PropProp;
import proplang.psi.PropTypes;

public class PropPsiImplUtil {
    public static String getKey(PropProp element) {
        ASTNode keyNode = element.getNode().findChildByType(PropTypes.KEY);
        if (keyNode != null) {
            // IMPORTANT: Convert embedded escaped spaces to ct spaces
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static String getValue(PropProp element) {
        ASTNode valueNode = element.getNode().findChildByType(PropTypes.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }


    public static String getName(PropProp element) {
        return getKey(element);
    }

    public static PsiElement setName(PropProp element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(PropTypes.KEY);
        if (keyNode != null) {

            PropProp property = PropElementFactory.createProperty(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(PropProp element) {
        ASTNode keyNode = element.getNode().findChildByType(PropTypes.KEY);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }


}