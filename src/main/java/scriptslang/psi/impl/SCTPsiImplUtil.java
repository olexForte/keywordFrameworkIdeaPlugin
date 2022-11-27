package main.java.scriptslang.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import main.java.scriptslang.SCTElementFactory;
import main.java.scriptslang.psi.SCTScriptfile;
import main.java.scriptslang.psi.SCTTypes;

public class SCTPsiImplUtil {


    public static String getValue(SCTScriptfile element) {
        ASTNode valueNode = element.getNode().findChildByType(SCTTypes.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return null;
        }
    }


    public static String getName(SCTScriptfile element) {
        return getValue(element);
    }

    public static PsiElement setName(SCTScriptfile element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(SCTTypes.VALUE);
        if (keyNode != null) {

            SCTScriptfile property = SCTElementFactory.createProperty(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(SCTScriptfile element) {
        ASTNode keyNode = element.getNode().findChildByType(SCTTypes.VALUE);
        if (keyNode != null) {
            return keyNode.getPsi();
        } else {
            return null;
        }
    }


}