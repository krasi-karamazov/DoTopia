package com.augeo.dotopia.util.validation;

/**
 * Created by krasimir.karamazov on 2/28/14.
 */

public abstract class AbstractValidator {
    private String mMessage;
    public AbstractValidator(String message) {
        setMessage(message);
    }
    public abstract boolean isValid(CharSequence value);
    public abstract boolean isValid(Boolean value);
    protected abstract int getConcreteMessageId();

    protected void setMessage(String message) {
        mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }
}

