package id.sch.smktelkom_mlg.tugas01.xiirpl1024.formpendaftaran;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    EditText etNama, etKelas, etNoHP;
    Button bOK;
    TextView tvHasil, tvAlasan;
    String hasil = "";
    RadioGroup rgGender;
    int nAlasan;
    CheckBox cb1, cb2, cb3, cb4, cb5;
    Spinner spProvinsi, spKota;
    String[][] arKota = {{"Jakarta Barat", "Jakata Pusat", "Jakarta Selatan", "Jakarta Timur", "Jakarta Utara"},
            {"Bandung", "Cirebon", "Bekasi"}, {"Semarang", "Magelang", "Surakarta"}, {"Surabaya", "Malang", "Blitar"}, {"Denpasar"}};
    ArrayList<String> listKota = new ArrayList();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        etKelas = (EditText) findViewById(R.id.editKelas);
        etNoHP = (EditText) findViewById(R.id.editTextNoHP);
        bOK = (Button) findViewById(R.id.buttonOK);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);
        rgGender = (RadioGroup) findViewById(R.id.RadioGroupGender);
        cb1 = (CheckBox) findViewById(R.id.checkBox1);
        cb2 = (CheckBox) findViewById(R.id.checkBox2);
        cb3 = (CheckBox) findViewById(R.id.checkBox3);
        cb4 = (CheckBox) findViewById(R.id.checkBox4);
        cb5 = (CheckBox) findViewById(R.id.checkBox5);
        tvAlasan = (TextView) findViewById(R.id.textViewAlasan);
        spProvinsi = (Spinner) findViewById(R.id.spinnerProvinsi);
        spKota = (Spinner) findViewById(R.id.spinnerKota);

        cb1.setOnCheckedChangeListener(this);
        cb2.setOnCheckedChangeListener(this);
        cb3.setOnCheckedChangeListener(this);
        cb4.setOnCheckedChangeListener(this);
        cb5.setOnCheckedChangeListener(this);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listKota);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKota.setAdapter(adapter);

        spProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
                listKota.clear();
                listKota.addAll(Arrays.asList(arKota[pos]));
                //adapter.setProvinsi((String)spProvinsi.getItemAtPosition(pos));
                adapter.notifyDataSetChanged();
                spKota.setSelection(0);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NameYearHP();
                Gender();
                Alasan();
                Asal();
                write();

            }
        });
    }

    private void write() {
        tvHasil.setText(hasil);
        hasil = "";
    }

    private void Asal() {
        hasil += "Asal      : " + spKota.getSelectedItem().toString() + ", " + spProvinsi.getSelectedItem().toString();
    }

    private void NameYearHP() {
        if (isValid()) {
            String nama = etNama.getText().toString();
            String hp = etNoHP.getText().toString();
            String kelas = etKelas.getText().toString();
            hasil += "Nama    : " + nama + "\nKelas     :  " + kelas + "\nNo HP   : " + hp;
        } else if (!isValid()) {
            hasil += "Nama atau Kelas atau Nomor HP belum benar";
        }
        hasil += "\n";

    }

    private boolean isValid() {
        boolean valid = true;

        String nama = etNama.getText().toString();
        String kelas = etKelas.getText().toString();
        String hp = etNoHP.getText().toString();

        if (nama.isEmpty()) {
            etNama.setError("Nama belum diisi");
            valid = false;
        } else if (nama.length() < 3) {
            etNama.setError("Nama minimal 3 karakter");
            valid = false;
        } else {
            etNama.setError(null);
        }

        if (kelas.isEmpty()) {
            etKelas.setError("Kelas belum diisi");
            valid = false;
        } else {
            etNoHP.setError(null);
        }

        if (hp.isEmpty()) {
            etNoHP.setError("Nomor HP belum diisi");
            valid = false;
        } else if (hp.length() != 12) {
            etNoHP.setError("Nomor HP harus 12 digit");
            valid = false;
        } else {
            etNoHP.setError(null);
        }

        return valid;

    }

    private void Gender() {
        String gender = null;

        if (rgGender.getCheckedRadioButtonId() != -1) {
            RadioButton rb = (RadioButton)
                    findViewById(rgGender.getCheckedRadioButtonId());
            gender = rb.getText().toString();
        }
        if (gender == null) {
            hasil += "Belum memilih gender";
        } else {
            hasil += "Gender  : " + gender;
        }
        hasil += "\n";
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) nAlasan += 1;
        else nAlasan -= 1;

        tvAlasan.setText("Alasan mengikuti Perpustakaan (" + nAlasan + " terpilih)");
    }

    private void Alasan() {
        String alasan = "Alasan mengikuti Perpustakaan:\n";
        int startlen = alasan.length();
        if (cb1.isChecked()) alasan += cb1.getText() + "\n";
        if (cb2.isChecked()) alasan += cb2.getText() + "\n";
        if (cb3.isChecked()) alasan += cb3.getText() + "\n";
        if (cb4.isChecked()) alasan += cb4.getText() + "\n";
        if (cb5.isChecked()) alasan += cb5.getText() + "\n";

        if (alasan.length() == startlen) alasan = "Tidak ada pada pilihan";

        hasil += alasan + "\n";
    }

}

