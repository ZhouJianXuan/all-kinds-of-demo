package work.koreyoshi.project.videos.constant;

public enum Behavior {
    /**
     * 更新行文
     */
    update("更新", "update"),
    ;

    private String name;

    private String behavior;

    Behavior(String name, String behavior) {
        this.name = name;
        this.behavior = behavior;
    }

    public String getBehavior() {
        return behavior;
    }

    public String getName() {
        return name;
    }
}
