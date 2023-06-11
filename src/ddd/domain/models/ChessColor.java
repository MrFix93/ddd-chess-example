package ddd.domain.models;

public enum ChessColor {
    WHITE,
    BLACK;

    public ChessColor invert() {
        return ChessColor.values()[(this.ordinal() + 1) % 2];
    }
}
