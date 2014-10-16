package com.xenojava.tournament;

public enum Setting {

    AUTO_START("auto-start-tournament", true),
    DEBUG("debug-mode", true),
    REQUIRED_AMOUNT_START("required-players-to-begin", 10),
    GAMING_TIME("game-time-in-seconds", 10 * 60);

    private Object def;
    private String path;

    private Object value;

    Setting(String path, Object def) {
        this.path = path;
        this.def = def;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getPath() {
        return path;
    }

    public Object getDefault() {
        return def;
    }

    public Object getObject() {
        if (value == null) return def;
        return value;
    }

    public boolean getBoolean() {
        if (value == null) return (Boolean) def;
        return (Boolean) value;
    }

    public int getInteger() {
        if (value == null) return (Integer) def;
        return (Integer) value;
    }

    public double getDouble() {
        if (value == null) return (Double) def;
        return (Double) value;
    }

    public String getString() {
        if (value == null) return (String) def;
        return (String) value;
    }

}
