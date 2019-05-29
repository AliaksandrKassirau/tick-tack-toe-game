package com.grsu.game.platform.ticktacktoe.events;

import com.grsu.game.platform.ticktacktoe.field.Figure;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FieldChangedEvent {
    private final Figure[][] field;
}
