package nallapareddy.com.todo.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import butterknife.ButterKnife;
import nallapareddy.com.todo.R;
import nallapareddy.com.todo.interfaces.AddNewTodoListener;
import nallapareddy.com.todo.model.Priority;
import nallapareddy.com.todo.model.Todo;


public class AddNewTodoDialog extends DialogFragment {

    private Spinner prioritySpinner;
    private EditText todoName;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setNeutralButton(android.R.string.cancel, null);
        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                commit();
            }
        });
        builder.setTitle(R.string.new_todo);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_add_todo, null);
        ArrayAdapter<Priority> priorityArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, Priority.values());
        prioritySpinner = ButterKnife.findById(view, R.id.priority_spinner);
        prioritySpinner.setAdapter(priorityArrayAdapter);
        todoName = ButterKnife.findById(view, R.id.priority_name);
        priorityArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        alertDialog.show();
        return alertDialog;
    }

    private void commit() {
        if (getActivity() instanceof AddNewTodoListener) {
            Todo todo = new Todo(todoName.getText().toString(), (Priority) prioritySpinner.getSelectedItem());
            ((AddNewTodoListener) getActivity()).addNewTodo(todo);
        } else {
            throw new RuntimeException("Activity did not implement AddNewTodo interface");
        }
    }
}
