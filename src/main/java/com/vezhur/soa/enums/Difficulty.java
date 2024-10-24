package com.vezhur.soa.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public enum Difficulty {

    VERY_EASY(1), EASY(2), NORMAL(3), IMPOSSIBLE(4), INSANE(5);

    private Integer value;

    Difficulty(Integer value) {
        this.value = value;
    }
}
