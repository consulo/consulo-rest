package com.jetbrains.rest.formatter;

import com.jetbrains.rest.RestElementTypes;
import com.jetbrains.rest.RestTokenTypes;
import consulo.document.util.TextRange;
import consulo.language.ast.ASTNode;
import consulo.language.ast.IElementType;
import consulo.language.codeStyle.*;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RestBlock implements ASTBlock {
  private RestBlock myParent;
  private ASTNode myNode;
  private final Alignment myAlignment;
  private final Indent myIndent;
  private Wrap myWrap;
  private List<RestBlock> mySubBlocks = null;

  public RestBlock(RestBlock parent, ASTNode node, final Alignment alignment, Indent indent, Wrap wrap) {
    myParent = parent;
    myNode = node;
    myAlignment = alignment;
    myIndent = indent;
    myWrap = wrap;
  }


  @Override
  public ASTNode getNode() {
    return myNode;
  }

  @Nonnull
  @Override
  public TextRange getTextRange() {
    return myNode.getTextRange();
  }

  @Nonnull
  public List<Block> getSubBlocks() {
    if (mySubBlocks == null) {
      mySubBlocks = buildSubBlocks();
    }
    return new ArrayList<Block>(mySubBlocks);
  }
  private List<RestBlock> buildSubBlocks() {
    List<RestBlock> blocks = new ArrayList<RestBlock>();
    for (ASTNode child = myNode.getFirstChildNode(); child != null; child = child.getTreeNext()) {

      IElementType childType = child.getElementType();
      if (child.getTextRange().getLength() == 0) continue;
      if (childType == RestTokenTypes.WHITESPACE) {
        continue;
      }

      blocks.add(buildSubBlock(child));
    }
    return Collections.unmodifiableList(blocks);
  }

  private RestBlock buildSubBlock(ASTNode child) {
    IElementType parentType = myNode.getElementType();
    IElementType childType = child.getElementType();
    IElementType grandparentType = myNode.getTreeParent() == null ? null : myNode.getTreeParent().getElementType();
    Wrap wrap = null;
    Indent childIndent = Indent.getNoneIndent();
    Alignment childAlignment = null;

    if (grandparentType == RestElementTypes.FIELD_LIST && parentType == RestElementTypes.LINE_TEXT &&
      childType == RestTokenTypes.LINE) {
      childIndent = Indent.getNormalIndent();
    }
    return new RestBlock(this, child, childAlignment, childIndent, wrap);
  }


  @Nullable
  @Override
  public Wrap getWrap() {
    return myWrap;
  }

  @Nullable
  @Override
  public Indent getIndent() {
    return myIndent;
  }

  @Nullable
  @Override
  public Alignment getAlignment() {
    return myAlignment;
  }

  @Nullable
  @Override
  public Spacing getSpacing(@Nullable Block child1, @Nonnull Block child2) {
    return null;
  }

  @Nonnull
  @Override
  public ChildAttributes getChildAttributes(int newChildIndex) {
    return new ChildAttributes(Indent.getNoneIndent(), null);
  }

  @Override
  public boolean isIncomplete() {
    return false;
  }

  @Override
  public boolean isLeaf() {
    return myNode.getFirstChildNode() == null;
  }
}
