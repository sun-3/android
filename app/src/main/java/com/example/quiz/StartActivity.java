package com.example.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StartActivity extends AppCompatActivity {
    float score = 0;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        startQuiz();

    }

   private void startQuiz() {
        final ArrayList<Ques> list = new ArrayList<>();
        insertQues(list);


        Button next = findViewById(R.id.button2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected())
                {

                    if (count < 9) {
                        Ques q = list.get(count);
                        String choice = validate();
                        if (choice.equals(q.O1)) {
                            score += 4;
                        }
                        else if(choice.equals(q.O2)) {
                            score += 3;
                        }
                        else if(choice.equals(q.O3)) {
                            score += 2;
                        }
                        else
                            score += 1 ;


                        count++;
                        enterQ(list.get(count));

                    } else {
                        Ques q = list.get(count);
                        String choice = validate();
                        if (choice.equals(q.O1)) {
                            score += 4;
                        }
                        else if(choice.equals(q.O2)) {
                            score += 3;
                        }
                        else if(choice.equals(q.O3)) {
                            score += 2;
                        }
                        else
                            score += 1 ;

                        if (choice.equals(q.O1)) {
                            score += 4;
                        }
                        else if(choice.equals(q.O2)) {
                            score += 3;
                        }
                        else if(choice.equals(q.O3)) {
                            score += 2;
                        }
                        else
                            score += 1 ;
                        Log.d("bhalu",score+ "");
                        Toast.makeText(getApplicationContext(),"score",Toast.LENGTH_SHORT).show();
                        // store

                        score /= list.size();
                        store(score);
                        Intent i = new Intent(StartActivity.this, endActivity.class);
                        i.putExtra("score", score);
                        startActivity(i);
                        finish();
                    }
                }
                else
                    {
                    Toast.makeText(getApplicationContext(), "option not selected", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    private void store(float score) {
        Map<String, Float> city = new HashMap<>();
        city.put("rating", score);


        db.collection("ratings").document(FirebaseAuth.getInstance().getUid())
                .set(city)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("bhalu", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("bhalu", "Error writing document", e);
                    }
                });



    }

    private boolean selected() {


        RadioButton option1 = findViewById(R.id.option1);
        RadioButton option2 = findViewById(R.id.option2);
        RadioButton option3 = findViewById(R.id.option3);
        RadioButton option4 = findViewById(R.id.option4);
        RadioButton option5 = findViewById(R.id.option5);
        if ((option1.isChecked() || option2.isChecked() || option3.isChecked() || option4.isChecked()) && !option5.isChecked()) {
            return true;
        }

        return false;
    }

    private String validate() {
        RadioGroup r = findViewById(R.id.radioGroup);
        String selection = "";

        if (r.getCheckedRadioButtonId() != -1) {
            int id = r.getCheckedRadioButtonId();
            View radioButton = r.findViewById(id);
            int radioId = r.indexOfChild(radioButton);
            RadioButton btn = (RadioButton) r.getChildAt(radioId);
            selection = (String) btn.getText();

        }
        return selection;
    }

    private void enterQ(Ques q) {
        TextView ques = findViewById(R.id.Ques);
        RadioButton option1 = findViewById(R.id.option1);
        RadioButton option2 = findViewById(R.id.option2);
        RadioButton option3 = findViewById(R.id.option3);
        RadioButton option4 = findViewById(R.id.option4);
        RadioButton option5 = findViewById(R.id.option5);
        option5.setChecked(true);


        ques.setText(q.getQ());
        option1.setText(q.getO1());
        option2.setText(q.getO2());
        option3.setText(q.getO3());
        option4.setText(q.getO4());


    }

    private void insertQues(ArrayList<Ques> list) {
        list.add(new Ques(1,"1. How did you hear about us?",
                "Advertisement",
                "Neighbours",
                "Friends",
                "Randomly"));

        list.add(new Ques(2, "2. Were you greeted in a friendly manner?",
                "Yes",
                "It was Ok",
                "Little Rude",
                "No"));

        list.add(new Ques(3,"3. What would you like to see changed?",

                "Nothing",
                "Behavior",
                "Greetings",
                "Everything"));

        list.add(new Ques(4,"4.How often do you visit us ?",

                "Most of the time",
                "Most Ocasionally",
                "Often",
                "Not at all"));

        list.add(new Ques(5,"5.Would you recommend us?",

                "Strongly agree",
                "Agree",
                "Maybe",
                "Not at all"));

        list.add(new Ques(6,"6.How would you describe the variety of offers?",

                "Excellent",
                "Good",
                "Fair",
                "Poor"));

        list.add(new Ques(7,"7.Sales staff professionalism?",

                "Excellent",
                "Good",
                "Fair",
                "Poor"));

        list.add(new Ques(8,"8.Were you satisfied ?",

                "Very satisfied",
                "Satisfied",
                "Neutral",
                "Dissatisfied"));

        list.add(new Ques(9,"9.How long have you been a customer?",

                "3+ years",
                "1+ year",
                "Less than six months",
                "My first purchase"));

        list.add(new Ques(10,"10.Please rate customer service?",

                "Excellent",
                "Good",
                "Average",
                "Bad"));

    }

}