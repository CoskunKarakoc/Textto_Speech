package com.cskn.texttospeech;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static int REQUEST_CODE = 100;
    Button adBtn;
    Button numaraBtn;
    Button vizeBtn;
    Button finalBtn;
    Button ortBtn;
    Button kaydetBtn;
    Button silBtn;
    Button sorgulaBtn;
    Button listeleBtn;
    TextView adText;
    TextView numaraText;
    TextView vizeText;
    TextView finalText;
    TextView ortText;
    TableActivity tableActivity;
    char[] dizi = {' ', 'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'ğ', 'h', 'ı', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'ö', 'p', 'q', 'r', 's', 'ş', 't', 'u', 'ü', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'Ç', 'D', 'E', 'F', 'G', 'Ğ', 'H', 'I', 'İ', 'J', 'K', 'L', 'M', 'N', 'O', 'Ö', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ü', 'V', 'W', 'X', 'Y', 'Z'};
    String numboslukstring;
    double vizenot, finalnot, ortnot;
    int sayi;
    Context context = this;
    Dialog eslesenText;
    ListView textListesi;
    ArrayList<String> EslesenTextListe;
    String listAd, listNu, listVize, listFinal, listOrt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        adBtn = (Button) findViewById(R.id.adBtn);
        numaraBtn = (Button) findViewById(R.id.numaraBtn);
        vizeBtn = (Button) findViewById(R.id.vizeBtn);
        finalBtn = (Button) findViewById(R.id.finalBtn);
        ortBtn = (Button) findViewById(R.id.ortBtn);
        kaydetBtn = (Button) findViewById(R.id.kaydetBtn);
        silBtn = (Button) findViewById(R.id.silBtn);
        sorgulaBtn = (Button) findViewById(R.id.sorgulaBtn);
        listeleBtn = (Button) findViewById(R.id.listeleBtn);

        adText = (TextView) findViewById(R.id.adText);
        numaraText = (TextView) findViewById(R.id.numaraText);
        vizeText = (TextView) findViewById(R.id.vizeText);
        finalText = (TextView) findViewById(R.id.finalText);
        ortText = (TextView) findViewById(R.id.ortText);


        tableActivity = new TableActivity(getApplicationContext());


        final List<ContactActivity> contactActivities = tableActivity.getAllContacts();

        adBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayi = 0;
                if (bagliMi()) {
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    Toast.makeText(context, "Lütfen İnternet Bağlantınızı Yapın!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        numaraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayi = 1;
                if (bagliMi()) {
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    Toast.makeText(context, "Lütfen İnternet Bağlantınızı Yapın!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        vizeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayi = 2;
                if (bagliMi()) {
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    Toast.makeText(context, "Lütfen İnternet Bağlantınızı Yapın!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        finalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sayi = 3;
                if (bagliMi()) {
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    startActivityForResult(intent, REQUEST_CODE);
                } else {
                    Toast.makeText(context, "Lütfen İnternet Bağlantınızı Yapın!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vizenot = Integer.parseInt(vizeText.getText().toString()) * 0.4;
                finalnot = Integer.parseInt(finalText.getText().toString()) * 0.6;
                ortnot = vizenot + finalnot;
                ortText.setText("" + (int) ortnot);
            }
        });

        listeleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListeActivity.class);
                startActivity(intent);
            }
        });

        kaydetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adText.getText().toString() != "" && numaraText.getText().toString() != "" && vizeText.getText().toString() != "" && finalText.getText().toString() != "") {
                    boolean control = true;
                    try {
                        if (numaraText.getText().toString() != "") {
                            int kayitNo = Integer.parseInt(numaraText.getText().toString());
                            for (int i = 0; i < contactActivities.size(); i++) {
                                if (contactActivities.get(i).numara == kayitNo) {
                                    Toast.makeText(MainActivity.this, "Öğrenci Zaten Kayıtlı!", Toast.LENGTH_SHORT).show();
                                    control=false;
                                }
                            }
                        }

                        if(control){

                            int sonuc = (int) (Integer.parseInt(vizeText.getText().toString()) * 0.4 + Integer.parseInt(finalText.getText().toString()) * 0.6);
                            tableActivity.addContact(new ContactActivity(Integer.parseInt(numaraText.getText().toString()), adText.getText().toString(), Integer.parseInt(vizeText.getText().toString()), Integer.parseInt(finalText.getText().toString()), sonuc));
                            Toast.makeText(MainActivity.this, "Kayıt Başarılı!", Toast.LENGTH_SHORT).show();

                            Intent refIntent = new Intent(MainActivity.this, MainActivity.class);
                            finish();
                            startActivity(refIntent);
                        }

                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Öğrenci Zaten Kayıtlı!", Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(MainActivity.this, "Lütfen Uygun Kutucukları Doldurunuz!", Toast.LENGTH_SHORT).show();
            }
        });


        silBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean control = true;
                if (numaraText.getText().toString() != "") {
                    int silNo = Integer.parseInt(numaraText.getText().toString());
                    for (int i = 0; i < contactActivities.size(); i++) {
                        if (contactActivities.get(i).numara == silNo) {
                            tableActivity.deleteContact(contactActivities.get(i));
                            Toast.makeText(MainActivity.this, "Silme Başarılı!", Toast.LENGTH_SHORT).show();
                            control = false;

                            Intent refIntent = new Intent(MainActivity.this, MainActivity.class);
                            finish();
                            startActivity(refIntent);
                        }
                    }
                    if (control) {
                        Toast.makeText(MainActivity.this, "Bu Numaraya Ait Kayıt Bulunamadı!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Öğrenci Numarası Girilmelidir!", Toast.LENGTH_SHORT).show();
                }
            }
        });


        sorgulaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean control = true;
                if (numaraText.getText().toString() != "") {
                    int sorgulaNo = Integer.parseInt(numaraText.getText().toString());
                    for (int i = 0; i < contactActivities.size(); i++) {
                        if (contactActivities.get(i).numara == sorgulaNo) {
                            adText.setText(contactActivities.get(i).getAdSoyad());
                            vizeText.setText("" + contactActivities.get(i).getVizenotu());
                            finalText.setText("" + contactActivities.get(i).getFinalnotu());
                            ortText.setText("" + contactActivities.get(i).getOrt());
                            Toast.makeText(MainActivity.this, "Kayıt Bulundu!", Toast.LENGTH_SHORT).show();
                            control = false;
                        }
                    }
                    if (control) {
                        Toast.makeText(MainActivity.this, "Bu Numaraya Ait Kayıt Bulunamadı!", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Öğrenci Numarası Girilmelidir!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            eslesenText = new Dialog(context);
            eslesenText.setContentView(R.layout.eslesen_sesler_listesi);
            eslesenText.setTitle("Eşleşen Metni Seçiniz!");
            textListesi = (ListView) eslesenText.findViewById(R.id.liste);
            EslesenTextListe = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, EslesenTextListe);
            textListesi.setAdapter(adapter);
            eslesenText.show();
            textListesi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (sayi) {
                        case 0:
                            int deger = 0;
                            String adsoyad = EslesenTextListe.get(position);
                            for (int j = 0; j < adsoyad.length(); j++) {
                                char karakter = adsoyad.charAt(j);
                                for (int k = 0; k < dizi.length; k++) {
                                    if (dizi[k] == karakter) {
                                        deger++;
                                    }

                                }
                            }
                            if (deger == adsoyad.length()) {
                                adText.setText(adsoyad);
                            } else {
                                Toast.makeText(getApplicationContext(), "Geçersiz Veri Girişi!", Toast.LENGTH_SHORT).show();
                            }

                            break;
                        case 1:
                            numboslukstring = EslesenTextListe.get(position);
                            String[] numbosluk = numboslukstring.split(" ", -1);
                            numboslukstring = "";
                            for (int i = 0; i < numbosluk.length; i++) {
                                numboslukstring += numbosluk[i];
                            }
                            try {
                                Integer.parseInt(numboslukstring);
                                numaraText.setText(numboslukstring);
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Geçersiz Veri Girişi!", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case 2:
                            try {
                                Integer.parseInt(EslesenTextListe.get(position));
                                if (Integer.parseInt(EslesenTextListe.get(position)) <= 100 && Integer.parseInt(EslesenTextListe.get(position)) >= 0) {
                                    vizeText.setText(EslesenTextListe.get(position));
                                    break;
                                }

                                Toast.makeText(getApplicationContext(), "Geçersiz Veri Girişi!", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Geçersiz Veri Girişi!", Toast.LENGTH_SHORT).show();
                            }
                            break;
                        case 3:
                            try {
                                Integer.parseInt(EslesenTextListe.get(position));
                                if (Integer.parseInt(EslesenTextListe.get(position)) <= 100 && Integer.parseInt(EslesenTextListe.get(position)) >= 0) {
                                    finalText.setText(EslesenTextListe.get(position));
                                    break;
                                }

                                Toast.makeText(getApplicationContext(), "Geçersiz Veri Girişi!", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Geçersiz Veri Girişi!", Toast.LENGTH_SHORT).show();
                            }
                            break;
                    }
                    eslesenText.cancel();
                }
            });

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //İnternet Bağlantısının olup olmadığını kontrol eden metot
    public boolean bagliMi() {
        ConnectivityManager con = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo net = con.getActiveNetworkInfo();
        if (net != null && net.isAvailable() && net.isConnected()) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {

            Intent refIntent = new Intent(MainActivity.this, MainActivity.class);
            finish();
            startActivity(refIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

