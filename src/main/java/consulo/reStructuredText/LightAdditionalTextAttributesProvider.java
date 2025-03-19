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
public class LightAdditionalTextAttributesProvider implements EditorColorSchemeExtender {
    @Override
    public void extend(Builder builder) {
        builder.add(RestSyntaxHighlighter.REST_BOLD, AttributesFlyweightBuilder.create()
            .withBoldFont()
            .build());

        builder.add(RestSyntaxHighlighter.REST_ITALIC, AttributesFlyweightBuilder.create()
            .withItalicFont()
            .build());

        builder.add(RestSyntaxHighlighter.REST_FIXED, AttributesFlyweightBuilder.create()
            .withBackground(new RGBColor(0xD9, 0xD9, 0xF0))
            .build());

        builder.add(RestSyntaxHighlighter.REST_INLINE, AttributesFlyweightBuilder.create()
            .withBackground(new RGBColor(0xED, 0xFC, 0xED))
            .build());

        builder.add(RestSyntaxHighlighter.REST_INTERPRETED, AttributesFlyweightBuilder.create()
            .withBackground(new RGBColor(0xCA, 0xDA, 0xBA))
            .build());
    }

    @Nonnull
    @Override
    public String getColorSchemeId() {
        return EditorColorsScheme.DEFAULT_SCHEME_NAME;
    }
}
