package nallapareddy.com.todo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import nallapareddy.com.todo.R;

public class EditActivity extends AppCompatActivity {

    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        String placeHolder = getIntent().getStringExtra(MainActivity.ITEM_EXTRA);
        position = getIntent().getIntExtra(MainActivity.POSITION_EXTRA, -1);
        EditText editText = (EditText) findViewById(R.id.edit_item);
        editText.append(placeHolder);
    }

    public void saveItem(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_item);
        String savedString = editText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra(MainActivity.ITEM_EXTRA, savedString);
        intent.putExtra(MainActivity.POSITION_EXTRA, position);
        setResult(RESULT_OK, intent);
        finish();
    }
}
