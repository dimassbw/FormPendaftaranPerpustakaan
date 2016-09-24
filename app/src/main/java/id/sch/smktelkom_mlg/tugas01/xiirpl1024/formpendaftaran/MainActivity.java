package id.sch.smktelkom_mlg.tugas01.xiirpl1024.formpendaftaran;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    EditText etNama, etKelas, etNoHP;
    Button bOK;
    TextView tvHasil;
    String hasil = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNama = (EditText) findViewById(R.id.editTextNama);
        etKelas = (EditText) findViewById(R.id.editKelas);
        etNoHP = (EditText) findViewById(R.id.editTextNoHP);
        bOK = (Button) findViewById(R.id.buttonOK);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);

        bOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NameYearHP();
                write();

            }
        });
    }

    private void write() {
        tvHasil.setText(hasil);
        hasil = "";
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


}

