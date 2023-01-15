package consulo.reStructuredText;

import consulo.annotation.component.ExtensionImpl;
import consulo.colorScheme.AdditionalTextAttributesProvider;
import consulo.colorScheme.EditorColorsScheme;

import javax.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 15/01/2023
 */
@ExtensionImpl
public class DarkAdditionalTextAttributesProvider implements AdditionalTextAttributesProvider {
    @Nonnull
    @Override
    public String getColorSchemeName() {
        return EditorColorsScheme.DARCULA_SCHEME_NAME;
    }

    @Nonnull
    @Override
    public String getColorSchemeFile() {
        return "/colorSchemes/RestDarcula.xml";
    }
}
