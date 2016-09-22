package nallapareddy.com.todo.model;

import android.support.annotation.NonNull;

import org.parceler.Parcel;

import java.util.Date;
import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Parcel(analyze = {Todo.class})
@Table(name = "Items")
public class Todo extends Model implements Comparable<Todo> {
    @Column(name = "Name")
    String name;
    @Column(name = "Completed")
    boolean completed;
    @Column(name = "Priority")
    int priority;
    @Column(name = "Date")
    Date date;

    public Todo() {
        super();
    }

    public Todo(String name, Priority priority) {
        super();
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
