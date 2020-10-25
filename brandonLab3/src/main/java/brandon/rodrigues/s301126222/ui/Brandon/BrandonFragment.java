package brandon.rodrigues.s301126222.ui.Brandon;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import brandon.rodrigues.s301126222.R;


public class BrandonFragment extends Fragment {

    private BrandonViewModel brandonViewModel;




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        brandonViewModel =
                ViewModelProviders.of(this).get(BrandonViewModel.class);
        View root = inflater.inflate(R.layout.fragment_brandon, container, false);
        final CanvasView myCanvas = (CanvasView) root.findViewById(R.id.brandonFrag1Canvas);

        final RadioButton redButton = root.findViewById(R.id.brandonRadioBttnRed);
        final RadioButton blackButton = root.findViewById(R.id.brandonRadioBttnBlack);
        final RadioButton greenButton = root.findViewById(R.id.brandonRadioBttnGreen);
        final RadioButton smallButton = root.findViewById(R.id.brandonRadioBttnSmall);
        final RadioButton medButton = root.findViewById(R.id.brandonRadioBttnMedium);
        final RadioButton largeButton = root.findViewById(R.id.brandonRadioBttnLarge);

        blackButton.setChecked(true);
        smallButton.setChecked(true);

        final Button brushButton = root.findViewById(R.id.brandonBrushButton);

        brushButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 if(redButton.isChecked()){
                    myCanvas.setColour(2);

                 }else if(greenButton.isChecked()){
                     myCanvas.setColour(3);
                 }else{
                     myCanvas.setColour(1);
                 }

                if(medButton.isChecked()){
                    myCanvas.setSize(2);
                }else if(largeButton.isChecked()){
                    myCanvas.setSize(3);
                }else{
                    myCanvas.setSize(1);
                }
            }
        });


        final Button clearButton = root.findViewById(R.id.brandonClearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               myCanvas.clearPath();

            }
        });

        return root;
    }
}
// Brandon Aaron Rodrigues 301126222 SEC.02