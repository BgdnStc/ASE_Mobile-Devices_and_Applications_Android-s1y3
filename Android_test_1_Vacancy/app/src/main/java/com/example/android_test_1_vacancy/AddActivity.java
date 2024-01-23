package com.example.android_test_1_vacancy;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentContainerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android_test_1_vacancy.utils.Vacancy;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {
    public static final String VACANCY_KEY = "sendVacancy";
    private EditText etVacancyTitle, etnSalary, etnNoOfApplicants;
    private FloatingActionButton fabSave;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initComponents();
        intent = getIntent();

        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValid()) {
                    Vacancy vacancy = createVacancy();
                    intent.putExtra(VACANCY_KEY, vacancy.toString());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }

    private boolean isValid() {
        if(etVacancyTitle.getText() == null || etVacancyTitle.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.add_title_error, Toast.LENGTH_SHORT).show();
            return false;
        }

        if(etnSalary.getText() == null || etnSalary.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.add_salary_error, Toast.LENGTH_SHORT).show();
            return false;
        }

        if(etnNoOfApplicants.getText() == null || etnNoOfApplicants.getText().toString().trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), R.string.add_No_Applicants_error, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private Vacancy createVacancy() {
        String title = etVacancyTitle.getText().toString();
        int salary = Integer.parseInt(etnSalary.getText().toString());
        int noOfApplicants = Integer.parseInt(etnNoOfApplicants.getText().toString());
        return new Vacancy(title, salary, noOfApplicants);
    }

    private void initComponents() {
        etVacancyTitle = findViewById(R.id.etVacancyTitle);
        etnSalary = findViewById(R.id.etnSalary);
        etnNoOfApplicants = findViewById(R.id.etnNoOfApplicants);
        fabSave = findViewById(R.id.fabSave);
    }
}