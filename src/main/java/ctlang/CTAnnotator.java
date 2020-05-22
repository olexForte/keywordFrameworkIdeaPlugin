package ctlang;

import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
//import ctlang.CTCreatePropertyQuickFix;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.util.PsiUtil;
import ctlang.psi.CTCommand;
import ctlang.psi.CTCommandPart;
import ctlang.psi.CTProperty;
import org.jetbrains.annotations.NotNull;
import proplang.psi.PropProp;
import scriptslang.SCTUtil;

import java.util.List;


public class CTAnnotator implements Annotator {

    @Override
    public void annotate(@NotNull final PsiElement element, @NotNull AnnotationHolder holder) {
        // Ensure the Psi Element is an expression
        //if (!(element instanceof PsiLiteralExpression)) return;

        if (element instanceof CTCommandPart) {
            System.out.println("CommandPart - " + element.getText());
            processCommand(element, holder);
            return;
        }

        if ((element instanceof CTProperty)) {
            System.out.println("Property - " + element.getText());
            processProperty(element, holder);
            return;
        }

//        if (element.getNode().getElementType().toString().equals("CTTokenType.KEY")){
//            processPropertyKey(element, holder);
//            return;
//        }

        if (element instanceof CTCommand){
            System.out.println("Command - " + element.getText());
            return;
        }

    }

//    private void processPropertyKey(PsiElement element, AnnotationHolder holder) {
//        String value = element.getText(); //literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
//
//        if ((value == null)) return;
//        TextRange keyRange = new TextRange(element.getTextRange().getStartOffset(), element.getTextRange().getEndOffset());
//
//
//        // Get the list of properties from the Project
//        String possibleKey = value;//.substring(CT_PREFIX_STR.length() + CT_SEPARATOR_STR.length());
//        Project project = element.getProject();
//
//        List<PsiFile> properties =  CTUtil.findPropertiesKeys(project, possibleKey);
//
//        // Set the annotations using the text ranges.
//        Annotation keyAnnotation = holder.createInfoAnnotation(keyRange, null);
//        keyAnnotation.setTextAttributes(DefaultLanguageHighlighterColors.KEYWORD);
////        Annotation separatorAnnotation = holder.createInfoAnnotation(separatorRange, null);
////        separatorAnnotation.setTextAttributes(CTSyntaxHighlighter.SEPARATOR);
//        if (properties.isEmpty() && !(possibleKey.toLowerCase().equals("random") || possibleKey.toLowerCase().equals("general"))) {
//            // No well-formed property found following the key-separator
//            Annotation badProperty = holder.createErrorAnnotation(keyRange, "File not found");
//            badProperty.setTextAttributes(CTSyntaxHighlighter.BAD_CHARACTER);
//            // ** Tutorial step 18.3 - Add a quick fix for the string containing possible properties
//            // badProperty.registerFix(new CTCreatePropertyQuickFix(possibleProperties)); //TODO
//        } else {
//            // Found at least one property
//            Annotation annotation = holder.createInfoAnnotation(keyRange, null);
//            annotation.setTextAttributes(CTSyntaxHighlighter.VALUE);
//        }
//    }

    private void processProperty(PsiElement element, AnnotationHolder holder) {
        String value = element.getText(); //literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;

        if ((value == null)) return;
        TextRange keyRange = new TextRange(element.getTextRange().getStartOffset(), element.getTextRange().getEndOffset());

        Project project = element.getProject();

        if( value.toUpperCase().startsWith(CTUtil.GLOBAL_FILE_NAME)
                || value.toUpperCase().startsWith(CTUtil.RANDOM_FILE_NAME)
                || value.toUpperCase().startsWith(CTUtil.SAVED_FILE_NAME)
                || !value.contains(".")) {
            Annotation annotation = holder.createInfoAnnotation(keyRange, null);
            annotation.setTextAttributes(CTSyntaxHighlighter.VALUE);
        }else {

            List<PropProp> properties = CTUtil.findProperties(project, element);

            // Set the annotations using the text ranges.
            Annotation keyAnnotation = holder.createInfoAnnotation(keyRange, null);
            keyAnnotation.setTextAttributes(DefaultLanguageHighlighterColors.KEYWORD);

            if (properties == null || properties.isEmpty()) {
                // No well-formed property found following the key-separator
                Annotation badProperty = holder.createErrorAnnotation(keyRange, "Property not found");
                badProperty.setTextAttributes(CTSyntaxHighlighter.BAD_CHARACTER);

            } else {
                // Found at least one property
                Annotation annotation = holder.createInfoAnnotation(keyRange, null);
                annotation.setTextAttributes(CTSyntaxHighlighter.KEY);
            }
        }
    }

    private void processCommand(PsiElement element, AnnotationHolder holder) {
        String value = element.getText().replace("Optional ", "").replace("Final ", "");

        if ((value == null)) return;
        TextRange keyRange = new TextRange(element.getTextRange().getStartOffset(), element.getTextRange().getEndOffset());

        String possibleProperties = value.trim();
        Project project = element.getProject();
        List<PsiLiteralExpression> commands =  CTUtil.findCommands(project, possibleProperties);
        List<PsiFile> commandsFromFiles = SCTUtil.findStoryFiles(project, possibleProperties.replace(" ", "_"));

        // Set the annotations using the text ranges.
        Annotation keyAnnotation = holder.createInfoAnnotation(keyRange, null);
        keyAnnotation.setTextAttributes(DefaultLanguageHighlighterColors.KEYWORD);
        if (commands.isEmpty() && commandsFromFiles.isEmpty()) {
            // No well-formed property found following the key-separator
            Annotation badProperty = holder.createErrorAnnotation(keyRange, "Command/Action not found");
            badProperty.setTextAttributes(CTSyntaxHighlighter.BAD_CHARACTER);
        } else {
            // Found at least one property
            Annotation annotation = holder.createInfoAnnotation(keyRange, null);
            annotation.setTextAttributes(CTSyntaxHighlighter.VALUE);
        }
    }

}

