package main.java.scriptslang;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiComment;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;

import com.intellij.psi.PsiWhiteSpace;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import org.jetbrains.annotations.NotNull;
import main.java.scriptslang.psi.SCTScriptfile;
import main.java.scriptslang.psi.SCTTokenType;

import java.util.List;


public class SCTAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {

        if (!(element instanceof SCTScriptfile)) return;
        String value = element.getText();
        if ((value == null)) return;

        TextRange keyRange = null;
        try {
             keyRange = new TextRange(element.getTextRange().getStartOffset(), element.getTextRange().getEndOffset() );
        }catch(Exception e){
            System.out.println();

        }
        // Get the list of properties from the Project
        String possibleProperties = value;//.substring(CT_PREFIX_STR.length() + CT_SEPARATOR_STR.length());
        Project project = element.getProject();
        List<String> properties = SCTUtil.findStories(project, possibleProperties);

        // Set the annotations using the text ranges.
        Annotation keyAnnotation = holder.createInfoAnnotation(keyRange, null);
        keyAnnotation.setTextAttributes(DefaultLanguageHighlighterColors.KEYWORD);

        if (properties.isEmpty()) {
            // No well-formed property found following the key-separator
            Annotation badProperty = holder.createErrorAnnotation(keyRange, "File not found");
            badProperty.setTextAttributes(SCTSyntaxHighlighter.BAD_CHARACTER);
            // ** Tutorial step 18.3 - Add a quick fix for the string containing possible properties
            badProperty.registerFix(new SCTCreateStoryfileQuickFix(possibleProperties));
        } else {
            // Found at least one property
            Annotation annotation = holder.createInfoAnnotation(keyRange, null);
            annotation.setTextAttributes(SCTSyntaxHighlighter.VALUE);
        }
    }

}

