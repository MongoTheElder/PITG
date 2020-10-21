package tv.mongotheelder.pitg.items;

import net.minecraft.util.IStringSerializable;
import tv.mongotheelder.pitg.Config;

public enum GlazingToolMode implements IStringSerializable {
    ROTATE("Rotate"),
    UNBREAKABLE("Unbreakable"),
    BREAK("Break");

    private final String name;

    GlazingToolMode(String name) {
        this.name = name;
    }

    @Override
    public String getString() {
        return name;
    }

    public GlazingToolMode advanceMode() {
        switch (this) {
            case ROTATE:
                return Config.ENABLE_UNBREAKABLE.get() ? UNBREAKABLE : Config.ENABLE_PANE_BREAK.get() ? BREAK : ROTATE;
            case UNBREAKABLE:
                return Config.ENABLE_PANE_BREAK.get() ? BREAK : ROTATE;
            case BREAK:
            default:
                return ROTATE;
        }
    }

    public static GlazingToolMode byName(String name) {
        for (GlazingToolMode mode : GlazingToolMode.values()) {
            if (mode.getString().toLowerCase().equals(name)) return mode;
        }
        return ROTATE;
    }
}

