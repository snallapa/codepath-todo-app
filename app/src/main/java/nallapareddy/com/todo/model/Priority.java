package nallapareddy.com.todo.model;

public enum  Priority {
    HIGH("HIGH"), MEDIUM("MEDIUM"), LOW("LOW");

    private String regularName;

    Priority(String regularName) {
        this.regularName = regularName;
    }

    public String getRegularName() {
        return regularName;
    }


    @Override
    public String toString() {
        return getRegularName();
    }
}
