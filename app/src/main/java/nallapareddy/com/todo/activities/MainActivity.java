package nallapareddy.com.todo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import nallapareddy.com.todo.R;
import nallapareddy.com.todo.adapters.TodoAdapter;
import nallapareddy.com.todo.dialogs.AddNewTodoDialog;
import nallapareddy.com.todo.model.Todo;

public class MainActivity extends AppCompatActivity implements AddNewTodoDialog.AddNewTodo {

    public static final String ITEM_EXTRA = "item";
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
        setupDeleteAction();
        setupEditAction();
    }

    private void setupDeleteAction() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                items.remove(position);
                adapter.newItems(items);
                return false;
            }
        });
    }

    private void setupEditAction() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                //intent.putExtra(ITEM_EXTRA, items.get(position));
                intent.putExtra(POSITION_EXTRA, position);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            int position = data.getIntExtra(POSITION_EXTRA, -1);
            if (position == -1) {
                return;
            }
            //items.set(position, data.getStringExtra(ITEM_EXTRA));
            adapter.notifyDataSetChanged();
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
