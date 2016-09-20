package nallapareddy.com.todo.model;

import android.support.annotation.NonNull;

import nallapareddy.com.todo.enums.Priority;

public class Todo implements Comparable<Todo> {
    private String name;
    private boolean completed;
    private Priority priority;

    public Todo(String name, Priority priority) {
        this.name = name;
        this.priority = priority;
    }

    public Priority getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(@NonNull Todo todo) {
        if (todo.priority == this.priority) {
            return this.name.compareTo(todo.name);
        } else {
            return this.priority.ordinal() > todo.priority.ordinal() ? 1 : -1;
        }
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
