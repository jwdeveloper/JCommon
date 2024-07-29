package io.github.jwdeveloper.common.actions;

import io.github.jwdeveloper.common.exception.CancellationException;

public class ActionToken
{
    private boolean isCanceled = false;

    public ActionToken() {
    }

    public void cancel() {
        this.isCanceled = true;
    }

    public boolean isCancel() {
        return this.isCanceled;
    }

    public void throwIfCancel() {
        if (this.isCanceled) {
            throw new CancellationException("Token requested cancellation");
        }
    }

    public boolean isNotCancel() {
        return !this.isCancel();
    }

}
