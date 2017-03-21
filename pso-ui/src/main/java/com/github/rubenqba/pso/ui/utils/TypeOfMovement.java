package com.github.rubenqba.pso.ui.utils;

import org.apache.commons.lang3.text.WordUtils;

/**
 * Created by ruben.bresler on 21/03/17.
 */
public enum TypeOfMovement {
    STANDARD_MOVEMENT,
    IMPLICIT_TRAPEZE;

    public String toString() {
        return WordUtils.capitalizeFully(super.toString().replace('_', ' '));
    }
}
