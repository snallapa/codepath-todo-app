package nallapareddy.com.todo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import nallapareddy.com.todo.R;
import nallapareddy.com.todo.adapters.TodoAdapter;
import nallapareddy.com.todo.dialogs.AddNewTodoDialog;
import nallapareddy.com.todo.interfaces.AddNewTodoListener;
import nallapareddy.com.todo.model.Todo;
import nallapareddy.com.todo.model.TodoItemAction;

public class MainActivity extends AppCompatActivity implements AddNewTodoListener {

    public static final String ITEM_EXTRA = "item";
    public static final String ACTION_EXTRA = "action";
    public static final String POSITION_EXTRA = "position";

    private static final int REQUEST_CODE = 1729;

    private ArrayList<Todo> items = new ArrayList<>();
    private TodoAdapter adapter;
    @BindView(R.id.listview)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new TodoAdapter(this, items);
        listView.setAdapter(adapter);
        setupEditAction();
    }

    private void setupEditAction() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                boolean editItem = adapter.getItemViewType(position) == TodoAdapter.VIEW_TODO;
                if (editItem) {
                    Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    Todo adapterItem = adapter.getItem(position);
                    int itemsPosition = items.indexOf(adapterItem);
                    intent.putExtra(ITEM_EXTRA, Parcels.wrap(adapterItem));
                    intent.putExtra(POSITION_EXTRA, itemsPosition);
                    startActivityForResult(intent, REQUEST_CODE);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            int todoActionInteger = data.getIntExtra(ACTION_EXTRA, -1);
            int position = data.getIntExtra(POSITION_EXTRA, -1);
            if (todoActionInteger == -1 || position == -1) {
                throw new RuntimeException("Illegal action received");
            }
            TodoItemAction action = TodoItemAction.values()[todoActionInteger];
            Todo currentTodo = Parcels.unwrap(data.getParcelableExtra(ITEM_EXTRA));
            switch (action) {
                case DELETE:
                    items.remove(position);
                    break;
                case SAVE:
                    items.set(position, currentTodo);
                    break;
            }
            adapter.newItems(items);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main_actionbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                AddNewTodoDialog addNewTodoDialog = new AddNewTodoDialog();
                addNewTodoDialog.show(getFragmentManager(),"add_new_dialog");
                break;
        }
        return false;
    }

    @Override
    public void addNewTodo(Todo todo) {
        items.add(todo);
        adapter.newItems(items);
    }
}
