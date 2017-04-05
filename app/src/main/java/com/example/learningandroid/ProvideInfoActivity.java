package com.example.learningandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ProvideInfoActivity extends AppCompatActivity {

    //<editor-fold desc="Extras">

    public static final String ClassNameExtra="classNameExtra";
    public static final String PersonNameExtra="personNameExtra";
    public static final String PersonEmailExtra="personEmailExtra";

    //</editor-fold>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provide_info);

        setupViews();
    }

    private void setupViews() {
        Button doneButton = (Button) findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDoneButton((Button)view);
            }
        });
    }

    private void handleDoneButton(Button button){
        String className  = getSelectedValueOfRadioGroup(R.id.classSelectGroup);
        String personName = getEditTextValue(R.id.nameEditBox);
        String personEmail = getEditTextValue(R.id.emailEditBox);

        Intent resultIntent = new Intent();
        resultIntent.putExtra(ClassNameExtra, className);
        resultIntent.putExtra(PersonNameExtra, personName);
        resultIntent.putExtra(PersonEmailExtra, personEmail);

        setResult(RESULT_OK, resultIntent);
        finish();
    }


    private String getEditTextValue(int textViewId){
        EditText editText = (EditText) findViewById(textViewId);
        Editable editable = editText.getText();
        return  editable!=null? editable.toString():"";
    }

    private String getSelectedValueOfRadioGroup(int groupId) {
        RadioGroup radioGroup = (RadioGroup) findViewById(groupId);

        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(radioButtonId);

        return (String) radioButton.getText();
    }
}
