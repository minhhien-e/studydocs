package studydocs.media.shared.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public final class Unit {
    private static final Unit INSTANCE = new Unit();

    private Unit() {}

    public static Unit instance() {
        return INSTANCE;
    }
}
