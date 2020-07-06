package tv.mongotheelder.pitg.items;

import tv.mongotheelder.pitg.setup.Config;

public enum GlazingToolMode {
    ROTATE("Rotate", 0, true),
    UNBREAKABLE("Unbreakable", 1, Config.ENABLE_UNBREAKABLE.get()),
    BREAK("Break", 2, Config.ENABLE_PANE_BREAK.get());

    private final String title;
    private final int index;
    private boolean enabled;

    GlazingToolMode(String title, int index, boolean enabled) {
        this.title = title;
        this.index = index;
        this.enabled = enabled;
    }

    public static void refresh() {
        UNBREAKABLE.enabled = Config.ENABLE_UNBREAKABLE.get();
        BREAK.enabled = Config.ENABLE_PANE_BREAK.get();
    }

    public String getTitle() {
        return title;
    }

    public int getIndex() {
        return index;
    }

    public static GlazingToolMode byIndex(int index) {
        for (GlazingToolMode m : GlazingToolMode.values()) {
            if (m.index == index && m.enabled) return m;
        }
        return ROTATE;
    }

    // Advance mode to the next available state, falling through unavailable modes
    public GlazingToolMode advanceMode() {
        switch (this) {
            case ROTATE:
                if (UNBREAKABLE.enabled) return UNBREAKABLE;
            case UNBREAKABLE:
                if (BREAK.enabled) return BREAK;
            case BREAK:
            default:
                return ROTATE;
        }
    }
}

