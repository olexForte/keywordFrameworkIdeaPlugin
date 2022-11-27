package main.java.scriptslang;

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;

import org.jetbrains.annotations.NotNull;
import main.java.scriptslang.psi.SCTScriptfile;
import main.java.scriptslang.psi.SCTTypes;

import java.util.List;

public class SCTCompletionContributor extends CompletionContributor {
    public SCTCompletionContributor() {
        extend( CompletionType.BASIC,
                PlatformPatterns.psiElement(SCTTypes.VALUE).withLanguage(SCTLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        List<String> all = SCTUtil.findStories(parameters.getPosition().getProject());
                        for(String item : all) {
                            resultSet.addElement(LookupElementBuilder.create(item));
                        }
                    }
                }
        );
    }
}