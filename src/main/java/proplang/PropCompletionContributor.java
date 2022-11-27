package main.java.proplang;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.patterns.PlatformPatterns;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.util.ProcessingContext;
import main.java.proplang.psi.PropTypes;
import org.jetbrains.annotations.NotNull;

public class PropCompletionContributor extends CompletionContributor {
    public PropCompletionContributor() {
        extend( CompletionType.BASIC,
                PlatformPatterns.psiElement(PropTypes.VALUE).withLanguage(PropLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("Hello"));
                    }
                }
        );
    }
}