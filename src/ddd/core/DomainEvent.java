package ddd.core;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public abstract class DomainEvent<T extends AggregateIdentifier> {
    @Getter
    private final T id;

    protected DomainEvent(T id) {
        this.id = id;
    }
}
