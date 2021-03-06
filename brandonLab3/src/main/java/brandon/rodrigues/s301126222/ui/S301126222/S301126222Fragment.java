package brandon.rodrigues.s301126222.ui.S301126222;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import brandon.rodrigues.s301126222.R;

public class S301126222Fragment extends Fragment {

    private S301126222ViewModel s301126222ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        s301126222ViewModel =
                ViewModelProviders.of(this).get(S301126222ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_s301126222, container, false);
        s301126222ViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}
// Brandon Aaron Rodrigues 301126222 SEC.02