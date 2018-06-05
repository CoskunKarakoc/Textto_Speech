package com.cskn.texttospeech;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListeActivity extends AppCompatActivity {

    TableActivity tableActivity;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eslesen_sesler_listesi);

        listView = (ListView) findViewById(R.id.liste);

        try {
            List<ContactActivity> contactActivities = new ArrayList<>();

            tableActivity = new TableActivity(getApplicationContext());

            StringBuilder stringBuilder =new StringBuilder();
            contactActivities = tableActivity.getAllContacts();
            for (ContactActivity contactActivity : contactActivities){
                stringBuilder.append("* "+contactActivity.getNumara() + "  -  " +  contactActivity.getAdSoyad()+ "\n Vize: " + contactActivity.getVizenotu() +
                        "   Final: " + contactActivity.getFinalnotu() + "    Ortalama: " + contactActivity.getOrt() + "\n\n");
            }
            String []dizi=stringBuilder.toString().split("\n");
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.list_item,R.id.listText,dizi);
            listView.setAdapter(arrayAdapter);
        }
        catch (Exception e){
            Toast.makeText(this, ""+e, Toast.LENGTH_SHORT).show();
        }

    }
}
