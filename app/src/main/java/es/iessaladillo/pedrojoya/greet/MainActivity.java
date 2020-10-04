package es.iessaladillo.pedrojoya.greet;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import es.iessaladillo.pedrojoya.greet.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {

    MainActivityBinding binding;
    String name, sirname, alias;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupViews();
    }

    private void setupViews() {
        alias = binding.rdbMr.getText().toString();
        binding.lblCountBar.setText(getString(R.string.intentsOther, count++));
        binding.rdgColor.setOnCheckedChangeListener((radioGroup, i) -> showImage());
        binding.btnGreet.setOnClickListener(view -> showText());
        binding.swtPremium.setOnClickListener(view -> showBar());


    }

    private void showBar() {
        count = 0;
        binding.barProgress.setProgress(count);
        if (binding.swtPremium.isChecked()) {

            binding.barProgress.setVisibility(View.GONE);
            binding.lblCountBar.setVisibility(View.GONE);

        } else {
            premiumCheck();
            binding.barProgress.setVisibility(View.VISIBLE);
            binding.lblCountBar.setVisibility(View.VISIBLE);

        }
    }

    private void showImage() {
        if (binding.rdbMr.isChecked()) {
            binding.imgPhoto.setImageResource(R.drawable.ic_mr);
            alias = binding.rdbMr.getText().toString();
        } else if (binding.rdbMs.isChecked()) {
            binding.imgPhoto.setImageResource(R.drawable.ic_ms);
            alias = binding.rdbMs.getText().toString();
        } else {
            binding.imgPhoto.setImageResource(R.drawable.ic_mrs);
            alias = binding.rdbMrs.getText().toString();
        }

    }

    private void boolPolitely() {

        if (binding.chkPolitely.isChecked()) {
            binding.lblGreet.setText(getString(R.string.resultPolitely, alias, name, sirname));
        } else {
            binding.lblGreet.setText(getString(R.string.result, alias, name, sirname));
        }
    }

    private void premiumCheck() {
        if (binding.swtPremium.isChecked()) {
            binding.lblCountBar.setText(getString(R.string.intentsOther, count));

        } else {
            binding.lblCountBar.setText(getString(R.string.intentsOther, count++));
        }
    }


    private void showText() {
        name = binding.txtName.getText().toString();
        sirname = binding.txtSirName.getText().toString();

        if ( count > 10) {
            binding.lblGreet.setText(R.string.tenIntents);
        } else if (!name.isEmpty() && !sirname.isEmpty() ) {
            binding.barProgress.setProgress(count);
            premiumCheck();
            boolPolitely();
        }


    }


}