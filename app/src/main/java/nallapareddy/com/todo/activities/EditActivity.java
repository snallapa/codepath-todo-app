package nallapareddy.com.todo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.parceler.Parcels;

import nallapareddy.com.todo.R;
import nallapareddy.com.todo.model.Todo;

public class EditActivity extends AppCompatActivity {

    private int position;
    private Todo currentTodo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        currentTodo  = Parcels.unwrap(getIntent().getParcelableExtra(MainActivity.ITEM_EXTRA));
        position = getIntent().getIntExtra(MainActivity.POSITION_EXTRA, -1);

    }
}
