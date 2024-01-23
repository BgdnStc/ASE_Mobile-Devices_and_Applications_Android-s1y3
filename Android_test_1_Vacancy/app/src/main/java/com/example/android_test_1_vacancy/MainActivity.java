package com.example.android_test_1_vacancy;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android_test_1_vacancy.utils.Vacancy;
import com.example.android_test_1_vacancy.utils.VacancyAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lvVacancies;
    private FragmentContainerView fcvVacancies;
    private Button btnAdd, btnDetails;
    private List<Vacancy> vacancies = new ArrayList<>();
    private ActivityResultLauncher<Intent> addLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        addLauncher = registerAddLauncher();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAdd = new Intent(getApplicationContext(), AddActivity.class);
                addLauncher.launch(intentAdd);
            }
        });
    }

    private ActivityResultLauncher<Intent> registerAddLauncher() {
        ActivityResultCallback<ActivityResult> callback = getCallback();
        return registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), callback);
    }

    private ActivityResultCallback<ActivityResult> getCallback() {
        return new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Vacancy vacancy = (Vacancy) result.getData().getSerializableExtra(AddActivity.VACANCY_KEY);
                    vacancies.add(vacancy);
                }
            }
        };
    }

    private void initComponents() {
        lvVacancies = findViewById(R.id.lvVacancies);
        fcvVacancies = findViewById(R.id.fcvVacancies);
        btnAdd = findViewById(R.id.btnAdd);
        btnDetails = findViewById(R.id.btnDetails);

        // VacancyAdapter vacancyAdapter = new VacancyAdapter(getApplicationContext(), R.id.lvVacancies, vacancies, getLayoutInflater());
        // lvVacancies.setAdapter(vacancyAdapter);
    }
}