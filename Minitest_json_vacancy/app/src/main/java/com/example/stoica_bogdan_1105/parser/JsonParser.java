package com.example.stoica_bogdan_1105.parser;

import android.util.Log;

import com.example.stoica_bogdan_1105.utils.Constants;
import com.example.stoica_bogdan_1105.utils.DateFormatter;
import com.example.stoica_bogdan_1105.utils.Vacancy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonParser {
    private final List<Vacancy> vacancies = new ArrayList<>();

    public List<Vacancy> getBookingsJson(String json) {
        if (json != null) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray(Constants.KEY_JSON_ARRAY);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonVacancy = jsonArray.getJSONObject(i);
                    String vacancyTitle = jsonVacancy.getString(Constants.KEY_VACANCY_TITLE);
                    int salary = jsonVacancy.getInt(Constants.KEY_SALARY);
                    int noOfApplicants = jsonVacancy.getInt(Constants.KEY_NO_APPLICANTS);
                    String vacancyStartDate = jsonVacancy.getString(Constants.KEY_VACANCY_DATE);
                    Vacancy vacancy = new Vacancy(vacancyTitle, salary, noOfApplicants, DateFormatter.fromString(vacancyStartDate));
                    vacancies.add(vacancy);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("parseJson", "JSON object is null");
        }
        return vacancies;
    }
}

