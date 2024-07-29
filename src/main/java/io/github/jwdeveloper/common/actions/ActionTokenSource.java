package io.github.jwdeveloper.common.actions;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ActionTokenSource
{
    private final Set<ActionToken> tokens = new HashSet<>();
    private boolean canceled = false;

    public ActionTokenSource() {
    }

    public void attacheToken(ActionToken cancellationToken) {
        this.tokens.add(cancellationToken);
    }

    public ActionToken createToken() {
        ActionToken ctx = new ActionToken();
        this.tokens.add(ctx);
        return ctx;
    }

    public void cancel() {
        this.canceled = true;
        Iterator var1 = this.tokens.iterator();
        while(var1.hasNext()) {
            ActionToken token = (ActionToken)var1.next();
            token.cancel();
        }
    }

    public boolean isCancel() {
        return this.canceled;
    }

    public boolean isNotCancel() {
        return !this.isCancel();
    }
}
