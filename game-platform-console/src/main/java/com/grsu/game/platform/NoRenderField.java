package com.grsu.game.platform;

import com.grsu.game.platform.ticktacktoe.field.BasicField;

public class NoRenderField extends BasicField {
    public NoRenderField(int fieldSize) {
        super(fieldSize);
    }

    @Override
    public void render() {
        //No action
    }
}
