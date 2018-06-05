package com.cskn.texttospeech;

/**
 * Created by cskn_ on 3.06.2018.
 */

public class ContactActivity {
    int numara;
    String adSoyad;
    int vizenotu;
    int finalnotu;
    int ort;

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public int getNumara() {
        return numara;
    }

    public void setNumara(int numara) {
        this.numara = numara;
    }

    public int getVizenotu() {
        return vizenotu;
    }

    public void setVizenotu(int vizenotu) {
        this.vizenotu = vizenotu;
    }

    public int getFinalnotu() {
        return finalnotu;
    }

    public void setFinalnotu(int finalnotu) {
        this.finalnotu = finalnotu;
    }

    public int getOrt() {
        return ort;
    }

    public void setOrt(int ort) {
        this.ort = ort;
    }

    public ContactActivity(int numara, String adSoyad,  int vizenotu, int finalnotu, int ort) {
        this.numara = numara;
        this.adSoyad = adSoyad;
        this.vizenotu = vizenotu;
        this.finalnotu = finalnotu;
        this.ort = ort;
    }
}
