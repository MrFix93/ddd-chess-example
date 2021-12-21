package ddd.core;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public abstract class Command<T extends AggregateIdentifier> {
    @Getter
    private final T id;

    protected Command(T id) {
        this.id = id;
    }
}
