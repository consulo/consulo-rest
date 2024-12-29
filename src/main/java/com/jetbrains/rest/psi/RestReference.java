package com.jetbrains.rest.psi;

import com.jetbrains.rest.validation.RestElementVisitor;
import consulo.language.ast.ASTNode;
import consulo.language.psi.PsiFile;
import consulo.language.psi.util.PsiTreeUtil;
import consulo.util.lang.StringUtil;

import jakarta.annotation.Nonnull;

/**
 * User : catherine
 */
public class RestReference extends RestElement {
  public RestReference(@Nonnull final ASTNode node) {
    super(node);
  }

  @Override
  public String toString() {
    return "RestReference:" + getNode().getElementType().toString();
  }

  public String getReferenceText() {
    String text = getNode().getText();
    text = StringUtil.replace(text, "\n", " ");
    text = text.replaceAll("\\\\([^\\\\]+)", "$1");
    if (text.startsWith("`") && text.endsWith("`_"))
      return text.substring(1, text.length()-2);
    if (text.endsWith("__"))
      return "__";
    if (text.startsWith("|") && text.endsWith("|"))
      return text;
    return text.substring(0, text.length()-1);
  }

  @Override
  protected void acceptRestVisitor(RestElementVisitor visitor) {
    visitor.visitReference(this);
  }

  public RestElement resolve() {
    String name = getReferenceText();
    PsiFile file = getContainingFile();
    RestReferenceTarget[] elements = PsiTreeUtil.getChildrenOfType(file, RestReferenceTarget.class);
    if (elements != null) {
      if (name.equals("__") || name.equals("[*]") || name.equals("[#]"))
        return findAnonimousTarget(file, elements);

      for (RestReferenceTarget element : elements) {
        if (element.getReferenceName().equalsIgnoreCase(name) || element.getReferenceName(false).equalsIgnoreCase(name)) {
          return element;
        }
      }
    }
    //TODO[catherine]: targets are not better than titles for resolving
    // they should have the same ancestor
    RestTitle[] titles = PsiTreeUtil.getChildrenOfType(file, RestTitle.class);
    if (titles != null) {
      for (RestTitle element : titles) {
        if (name.equalsIgnoreCase(element.getName())) {
          return element;
        }
      }
    }
    return null;
  }

  private RestReferenceTarget findAnonimousTarget(PsiFile file, RestReferenceTarget[] targets) {
    String name = getReferenceText();
    RestReference[] references = PsiTreeUtil.getChildrenOfType(file, RestReference.class);
    int refIndex = 1;
    int i = 0;
    while (!references[i].equals(this)) {
      if (references[i].getReferenceText().equals(name))
        ++refIndex;
      ++i;
    }

    int targetIndex = 0;
    for (int j = 0; j != targets.length; ++j) {
      if (targets[j].getReferenceName().equals(name))
        ++targetIndex;
      if (targetIndex == refIndex)
        return targets[j];
    }
    return null;
  }
}
