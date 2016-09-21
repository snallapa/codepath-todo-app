package nallapareddy.com.todo.model;

import android.support.annotation.NonNull;

import org.parceler.Parcel;

import java.util.Date;

@Parcel
public class Todo implements Comparable<Todo> {
    String name;
    boolean completed;
    int priority;
    Date date;

    public Todo() {

    }

    public Todo(String name, Priority priority) {
        this.name = name;
        this.priority = priority.ordinal();
    }

    public Priority getPriority() {
        return Priority.values()[priority];
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(@NonNull Todo todo) {
        if (todo.priority == this.priority) {
            return this.name.compareTo(todo.name);
        } else {
            return this.priority > todo.priority ? 1 : -1;
        }
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority.ordinal();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
