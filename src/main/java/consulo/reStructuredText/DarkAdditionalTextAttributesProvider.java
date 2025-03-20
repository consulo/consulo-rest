package consulo.reStructuredText;

import com.jetbrains.rest.RestSyntaxHighlighter;
import consulo.annotation.component.ExtensionImpl;
import consulo.colorScheme.AttributesFlyweightBuilder;
import consulo.colorScheme.EditorColorSchemeExtender;
import consulo.colorScheme.EditorColorsScheme;
import consulo.ui.color.RGBColor;
import jakarta.annotation.Nonnull;

/**
 * @author VISTALL
 * @since 15/01/2023
 */
@ExtensionImpl
public class DarkAdditionalTextAttributesProvider implements EditorColorSchemeExtender {
    @Override
    public void extend(Builder builder) {
        builder.add(RestSyntaxHighlighter.REST_BOLD, AttributesFlyweightBuilder.create()
            .withBoldFont()
            .build());

        builder.add(RestSyntaxHighlighter.REST_ITALIC, AttributesFlyweightBuilder.create()
            .withItalicFont()
            .build());

        builder.add(RestSyntaxHighlighter.REST_FIXED, AttributesFlyweightBuilder.create()
            .withBackground(new RGBColor(0xA3, 0xA0, 0x80))
            .build());

        builder.add(RestSyntaxHighlighter.REST_INLINE, AttributesFlyweightBuilder.create()
            .withBackground(new RGBColor(0x23, 0x64, 0x23))
            .build());

        builder.add(RestSyntaxHighlighter.REST_INTERPRETED, AttributesFlyweightBuilder.create()
            .withBackground(new RGBColor(0x15, 0x46, 0xC0))
            .build());
    }

    @Nonnull
    @Override
    public String getColorSchemeId() {
        return EditorColorsScheme.DARCULA_SCHEME_NAME;
    }
}
