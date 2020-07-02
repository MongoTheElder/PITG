package tv.mongotheelder.pitg.items;

import tv.mongotheelder.pitg.Config;

public enum GlazingToolMode {
    ROTATE("Rotate"),
    UNBREAKABLE("Unbreakable"),
    BREAK("Break");

    private final String title;

    GlazingToolMode(String title) {
        this.title = title;
    }

    public String getTitle() { return title; }

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
}

