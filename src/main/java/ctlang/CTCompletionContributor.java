package main.java.ctlang;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.util.ProcessingContext;
import main.java.ctlang.psi.CTTypes;
import org.jetbrains.annotations.NotNull;
import main.java.proplang.psi.PropProp;

import java.util.List;

public class CTCompletionContributor extends CompletionContributor {
    public CTCompletionContributor() {

        extend( CompletionType.BASIC,
                PlatformPatterns.psiElement(CTTypes.PROPERTY).withLanguage(CTLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        PsiElement key = parameters.getPosition();
                         List<PropProp> all = CTUtil.findPropertiesForKey(parameters.getPosition().getProject(), key);
                         for (PropProp item : all)
                                resultSet.addElement(LookupElementBuilder.create(item));

                        resultSet.addElement(LookupElementBuilder.create("RANDOM.label:10c"));
                        resultSet.addElement(LookupElementBuilder.create("RANDOM.number:5d"));
                        resultSet.addElement(LookupElementBuilder.create("RANDOM.password:10l"));
                        resultSet.addElement(LookupElementBuilder.create("RANDOM.curdate:CURRENT_DATE"));

                        resultSet.addElement(LookupElementBuilder.create("SAVED.value"));

                    }
                }
        );

//        extend( CompletionType.BASIC,
//                PlatformPatterns.psiElement(CTTypes.VALUE).withLanguage(CTLanguage.INSTANCE),
//                new CompletionProvider<CompletionParameters>() {
//                    public void addCompletions(@NotNull CompletionParameters parameters,
//                                               @NotNull ProcessingContext context,
//                                               @NotNull CompletionResultSet resultSet) {
//                            PsiElement key = parameters.getPosition().getPrevSibling().getPrevSibling();
//                            if(key.getText().toLowerCase().equals("random")){
//                                resultSet.addElement(LookupElementBuilder.create("label:10c"));
//                                resultSet.addElement(LookupElementBuilder.create("number:5d"));
//                                resultSet.addElement(LookupElementBuilder.create("pass:10l"));
//                                resultSet.addElement(LookupElementBuilder.create("curdate:CURRENT_DATE"));
//                            } else {
//                                List<PropProp> all = CTUtil.findPropertiesForKey(parameters.getPosition().getProject(), key);
//                                for (PropProp item : all)
//                                    resultSet.addElement(LookupElementBuilder.create(item));
//                            }
//
//                    }
//                }
//        );
//
//        extend( CompletionType.BASIC,
//                PlatformPatterns.psiElement(CTTypes.KEY).withLanguage(CTLanguage.INSTANCE),
//                new CompletionProvider<CompletionParameters>() {
//                    public void addCompletions(@NotNull CompletionParameters parameters,
//                                               @NotNull ProcessingContext context,
//                                               @NotNull CompletionResultSet resultSet) {
//
//        List<PsiFile> all = CTUtil.findPropertiesKeys(parameters.getPosition().getProject());
//        for(PsiFile item : all)
//            resultSet.addElement(LookupElementBuilder.create(item.getVirtualFile().getNameWithoutExtension()));
//        resultSet.addElement(LookupElementBuilder.create("RANDOM"));
//        resultSet.addElement(LookupElementBuilder.create("GENERAL"));
//    }
//}
//        );
        extend( CompletionType.BASIC,
                PlatformPatterns.psiElement(CTTypes.COMMAND_PART).withLanguage(CTLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {

                            List<PsiLiteralExpression> all = CTUtil.findCommands(parameters.getPosition().getProject());
                            for(PsiLiteralExpression item : all)
                                resultSet.addElement(LookupElementBuilder.create(item));

                            List<PsiFile> items = CTUtil.findActionFiles(parameters.getPosition().getProject());
                            for(PsiFile item : items)
                                resultSet.addElement(LookupElementBuilder.create(item));
                    }
                }
        );

        extend( CompletionType.BASIC,
                PlatformPatterns.psiElement(CTTypes.TAG).withLanguage(CTLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        PsiElement key = parameters.getPosition();
                        List<String> all = CTUtil.findTags(parameters.getPosition().getProject());
                        for (String item : all)
                            resultSet.addElement(LookupElementBuilder.create(item));

                    }
                }
        );
    }
}