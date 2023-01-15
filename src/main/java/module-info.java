/**
 * @author VISTALL
 * @since 15/01/2023
 */
module org.jetbrains.plugins.rest {
    requires consulo.ide.api;
    
    exports com.jetbrains.rest;
    exports com.jetbrains.rest.actions;
    exports com.jetbrains.rest.completion;
    exports com.jetbrains.rest.formatter;
    exports com.jetbrains.rest.lexer;
    exports com.jetbrains.rest.parsing;
    exports com.jetbrains.rest.psi;
    exports com.jetbrains.rest.spellchecker;
    exports com.jetbrains.rest.structureView;
    exports com.jetbrains.rest.validation;
    exports consulo.reStructuredText;
    exports consulo.reStructuredText.icon;
}