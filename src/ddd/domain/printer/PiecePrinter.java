package ddd.domain.printer;

import ddd.domain.models.Piece;

public interface PiecePrinter<T> {
    T print(Piece piece);
}
