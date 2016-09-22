package nallapareddy.com.todo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import org.parceler.Parcels;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import nallapareddy.com.todo.R;
import nallapareddy.com.todo.model.Priority;
import nallapareddy.com.todo.model.Todo;
import nallapareddy.com.todo.model.TodoItemAction;

public class EditActivity extends AppCompatActivity {

    private int position;
    private Todo currentTodo;
    private boolean dateChanged;

    @BindView(R.id.edit_todo_name)
    EditText todoName;
    @BindView(R.id.edit_todo_date)
    DatePicker todoDate;
    @BindView(R.id.edit_todo_checkbox)
    CheckBox todoCompleted;
    @BindView(R.id.edit_todo_spinner)
    Spinner todoPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        currentTodo = Parcels.unwrap(getIntent().getParcelableExtra(MainActivity.ITEM_EXTRA));
        position = getIntent().getIntExtra(MainActivity.POSITION_EXTRA, -1);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        todoName.append(currentTodo.getName());
        Date date = currentTodo.getDate();
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        todoDate.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
                dateChanged = true;
            }
        });
        todoCompleted.setChecked(currentTodo.isCompleted());
        ArrayAdapter<Priority> priorityArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Priority.values());
        todoPriority.setAdapter(priorityArrayAdapter);
        priorityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        todoPriority.setSelection(currentTodo.getPriority().ordinal());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_edit_actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_item:
                saveData();
                finishEditing(TodoItemAction.SAVE);
                break;
            case R.id.delete_item:
                finishEditing(TodoItemAction.DELETE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void finishEditing(TodoItemAction action) {
        Intent deleteIntent = new Intent();
        deleteIntent.putExtra(MainActivity.ACTION_EXTRA, action.ordinal());
        deleteIntent.putExtra(MainActivity.ITEM_EXTRA, Parcels.wrap(currentTodo));
        deleteIntent.putExtra(MainActivity.POSITION_EXTRA, position);
        setResult(RESULT_OK, deleteIntent);
        finish();
    }

    private void saveData() {
        currentTodo.setName(todoName.getText().toString());
        currentTodo.setPriority((Priority) todoPriority.getSelectedItem());
        int day = todoDate.getDayOfMonth();
        int month = todoDate.getMonth();
        int year = todoDate.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        currentTodo.setDate(dateChanged ? calendar.getTime() : currentTodo.getDate());
        currentTodo.setCompleted(todoCompleted.isChecked());
    }
}
