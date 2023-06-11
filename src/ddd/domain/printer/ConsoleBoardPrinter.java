package ddd.domain.printer;

import ddd.domain.models.Board;

public interface ConsoleBoardPrinter {
    void print(Board board);

    String getRawString(Board board);
}
