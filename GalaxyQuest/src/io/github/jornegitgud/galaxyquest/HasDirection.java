package io.github.jornegitgud.galaxyquest;

import java.util.function.Consumer;

public interface HasDirection {
    Direction getDirection();
    void setDirection(Direction direction);
    Consumer<HasDirection> onDirectionChanged = (obj) -> {};
}
