package proplang;


import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

    public class PropFileType extends LanguageFileType {
        public static final PropFileType INSTANCE = new PropFileType();

        private PropFileType() {
            super(PropLanguage.INSTANCE);
        }

        @NotNull
        @Override
        public String getName() {
            return "PROP File";
        }

        @NotNull
        @Override
        public String getDescription() {
            return "Property file for CT language";
        }

        @NotNull
        @Override
        public String getDefaultExtension() {
            return "prop";
        }

        @Nullable
        @Override
        public Icon getIcon() {
            return PropIcons.FILE;
        }
    }