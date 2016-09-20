package nallapareddy.com.todo.adapters;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import nallapareddy.com.todo.R;
import nallapareddy.com.todo.model.Todo;

public class TodoAdapter extends BaseAdapter {

    private List<Todo> uncompletedTodos;
    private List<Todo> completedTodos;
    private Context context;

    public TodoAdapter(Context context, List<Todo> items) {
        this.context = context;
        uncompletedTodos = new ArrayList<>();
        completedTodos = new ArrayList<>();
        splitTodos(items);
    }

    private void splitTodos(List<Todo> items) {
        uncompletedTodos.clear();
        completedTodos.clear();
        for (Todo currentTodo : items) {
            if (currentTodo.isCompleted()) {
                completedTodos.add(currentTodo);
            } else {
                uncompletedTodos.add(currentTodo);
            }
        }
        Collections.sort(completedTodos);
        Collections.sort(uncompletedTodos);
    }

    @Override
    public int getCount() {
        return uncompletedTodos.size() + completedTodos.size();
    }

    @Override
    public Todo getItem(int position) {
        if (position < uncompletedTodos.size()) {
            return uncompletedTodos.get(position);
        } else {
            return completedTodos.get(position - uncompletedTodos.size());
        }
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.todo_list_row, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        final Todo currentTodo = getItem(position);
        holder.priority.setText(currentTodo.getPriority().getRegularName());
        holder.name.setText(currentTodo.getName());
        if (currentTodo.isCompleted()) {
            holder.name.setPaintFlags(holder.name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.name.setTextColor(Color.GRAY);
            holder.priority.setTextColor(Color.GRAY);
        } else {
            holder.name.setPaintFlags(holder.name.getPaintFlags() & (~ Paint.STRIKE_THRU_TEXT_FLAG));
            holder.name.setTextColor(Color.BLACK);
            int color;
            switch (currentTodo.getPriority()) {
                case HIGH:
                    color = Color.RED;
                    break;
                case MEDIUM:
                    color = Color.GREEN;
                    break;
                case LOW:
                    color = Color.CYAN;
                    break;
                default:
                    color = Color.BLACK;
            }
            holder.priority.setTextColor(color);
        }
        holder.checkBox.setChecked(currentTodo.isCompleted());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentTodo.setCompleted(holder.checkBox.isChecked());
                reorderItems();
            }
        });
        return view;
    }

    public void newItems(List<Todo> items) {
        splitTodos(items);
        notifyDataSetChanged();
    }

    public void reorderItems() {
        List<Todo> items = new ArrayList<>();
        items.addAll(completedTodos);
        items.addAll(uncompletedTodos);
        splitTodos(items);
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        @BindView(R.id.todo_check)
        CheckBox checkBox;
        @BindView(R.id.todo_name)
        TextView name;
        @BindView(R.id.todo_priority)
        TextView priority;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }
}
