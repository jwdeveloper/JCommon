package io.github.jwdeveloper.common.actions;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Optional;
import java.util.function.Function;

@Data
public class ActionResult<T> {
    private boolean success = true;
    private T content;
    private String message;

    @Accessors(chain = true, fluent = true)
    private ActionResult<?> previous;

    public ActionResult() {
    }

    protected ActionResult(T object) {
        this.content = object;
    }

    protected ActionResult(T object, boolean success) {
        this(object);
        this.success = success;
    }

    protected ActionResult(T object, boolean status, String message) {
        this(object, status);
        this.message = message;
    }

    public boolean isFailed() {
        return !isSuccess();
    }

    public boolean hasMessage() {
        return message != null;
    }

    public boolean hasContent() {
        return content != null;
    }

    public boolean hasPrevious() {
        return previous != null;
    }

    public T get() {
        return content;
    }

    public static <T> ActionResult<T> success() {
        return new ActionResult<>(null, true);
    }

    public <Output> ActionResult<Output> cast(Output output) {
        return new ActionResult<>(output, this.isSuccess(), this.getMessage());
    }

    public <Output> ActionResult<Output> cast() {
        return cast(null);
    }

    public Optional<T> toOptional() {
        return Optional.ofNullable(content);
    }

    public <U> ActionResult<U> map(Function<? super T, ? extends U> mapper) {
        return hasContent() ? cast(mapper.apply(content)) : cast();
    }

    public static <Input, Output> ActionResult<Output> cast(ActionResult<Input> action, Output output) {
        return new ActionResult<>(output, action.isSuccess(), action.getMessage());
    }

    public static <T> ActionResult<T> success(T payload) {
        return new ActionResult<>(payload, true);
    }

    public static <T> ActionResult<T> success(T payload, String message) {
        return new ActionResult<>(payload, true, message);
    }

    public static <T> ActionResult<T> failed() {
        return new ActionResult<>(null, false);
    }

    public static <T> ActionResult<T> failed(String message) {
        return new ActionResult<>(null, false, message);
    }

    public static <T> ActionResult<T> failed(T target, String message) {
        return new ActionResult<>(target, false, message);
    }

    public static <T> ActionResult<T> of(T target, String message) {
        if (target == null)
            return failed(target, message);
        return success(target, message);
    }

    public static <T> ActionResult<T> of(T target) {
        return of(target, "");
    }

    public static <T> ActionResult<T> of(Optional<T> optional) {
        return new ActionResult<>(optional.orElse(null), optional.isPresent());
    }

    public static <T> ActionResultBuilder<T> builder(T content) {
        return new ActionResultBuilder<T>(content);
    }


}
