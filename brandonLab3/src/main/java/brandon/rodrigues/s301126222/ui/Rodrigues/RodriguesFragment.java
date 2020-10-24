package brandon.rodrigues.s301126222.ui.Rodrigues;

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

public class RodriguesFragment extends Fragment {

    private RodriguesViewModel rodriguesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rodriguesViewModel =
                ViewModelProviders.of(this).get(RodriguesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_rodrigues, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        rodriguesViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
// Brandon Aaron Rodrigues 301126222 SEC.02