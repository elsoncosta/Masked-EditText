package com.pc.edittext.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import com.pc.edittext.MaskedWatcher;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        EditText editTextCEP = (EditText) findViewById(R.id.editTextCEP);
        EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        EditText editTextCNPJ = (EditText) findViewById(R.id.editTextCNPJ);
        EditText editTextCPF = (EditText) findViewById(R.id.editTextCPF);
        EditText editTextRG = (EditText) findViewById(R.id.editTextRG);
        EditText editTextOther = (EditText) findViewById(R.id.editTextOther);

        MaskedWatcher cepWatcher = new MaskedWatcher("#####-###", editTextCEP);
        cepWatcher.setAcceptOnlyNumbers(true);

        MaskedWatcher phoneWatcher = new MaskedWatcher("(##) ####-####", editTextPhone);
        phoneWatcher.setAcceptOnlyNumbers(true);

        MaskedWatcher cnpjWatcher = new MaskedWatcher("##.###.###/####-##", editTextCNPJ);
        cnpjWatcher.setAcceptOnlyNumbers(true);

        new MaskedWatcher("###.###.###-##", editTextCPF).setAcceptOnlyNumbers(true);

        new MaskedWatcher("##########", editTextRG).setAcceptOnlyNumbers(true);

        new MaskedWatcher("[##]", editTextOther);

        new MaskedWatcher("## ##-## (#) [###-###]", (EditText) findViewById(R.id.editTextOther1));
    }
}
