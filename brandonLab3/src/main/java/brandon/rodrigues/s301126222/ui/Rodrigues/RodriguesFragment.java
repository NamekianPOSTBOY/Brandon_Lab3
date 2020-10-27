package brandon.rodrigues.s301126222.ui.Rodrigues;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.graphics.drawable.BitmapDrawable;
import brandon.rodrigues.s301126222.ui.Rodrigues.MyAnimationDrawable;
import android.widget.TextView;
import android.Manifest;
import android.os.Vibrator;
import androidx.annotation.NonNull;

import androidx.core.app.ActivityCompat;



import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.Objects;

import brandon.rodrigues.s301126222.R;

public class RodriguesFragment extends Fragment {
    private static final String TAG = "Contacts";
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;
    MyAnimationDrawable mframeAnimation = null;

    private RodriguesViewModel rodriguesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rodriguesViewModel =
                ViewModelProviders.of(this).get(RodriguesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_rodrigues, container, false);


        final Button button = root.findViewById(R.id.brandonPermissionBttn);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
              insertDummyContactsWrapper();
            }
        });

        final Button buttonStart = root.findViewById(R.id.brandonStartAnimBttn);
        buttonStart.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startAnimation();
            }
        });
        final Button buttonStop = root.findViewById(R.id.brandonStopAnimBttn);
        buttonStop.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                stopAnimation();
            }
        });

        final Button buttonHalf = root.findViewById(R.id.brandonButtonHalf);
        buttonHalf.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mframeAnimation.setDuration(450);
            }
        });
        final Button buttonNormal = root.findViewById(R.id.brandonButtonNormal);
        buttonNormal.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mframeAnimation.setDuration(300);
            }
        });


        final Button button2X = root.findViewById(R.id.brandonButton2X);
        button2X.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mframeAnimation.setDuration(150);
            }
        });

        final Button button4X = root.findViewById(R.id.brandonButton4X);
        button4X.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                mframeAnimation.setDuration(75);
            }
        });

        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,  String[] permissions,  int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    Toast.makeText(getActivity(), "Thanks! Contact Creation Allowed.", Toast.LENGTH_SHORT)
                            .show();
                   addContacts();
                } else {
                    // Permission Denied
                    Toast.makeText(getActivity(), "Contact Creation Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void insertDummyContactsWrapper() {

        int hasWriteContactsPermission = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hasWriteContactsPermission = ActivityCompat.checkSelfPermission(getActivity(),Manifest.permission.WRITE_CONTACTS);

            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS},
                        REQUEST_CODE_ASK_PERMISSIONS);
                return;
            }
          }
            addContacts();
        }
private void startAnimation(){
    ImageView img = getActivity().findViewById(R.id.brandonStarPower);

    BitmapDrawable frame1 = (BitmapDrawable)getResources().getDrawable(R.drawable.frame1);
    BitmapDrawable frame2 = (BitmapDrawable)getResources().getDrawable(R.drawable.frame2);
    BitmapDrawable frame3 = (BitmapDrawable)getResources().getDrawable(R.drawable.frame3);
    BitmapDrawable frame4 = (BitmapDrawable)getResources().getDrawable(R.drawable.frame4);
    BitmapDrawable frame5 = (BitmapDrawable)getResources().getDrawable(R.drawable.frame5);
    BitmapDrawable frame6 = (BitmapDrawable)getResources().getDrawable(R.drawable.frame6);

    // Get the background, which has been compiled to an AnimationDrawable object.
    int reasonableDuration = 0;
    mframeAnimation = new MyAnimationDrawable();
    mframeAnimation.setOneShot(false);	// loop continuously
    mframeAnimation.addFrame(frame1, reasonableDuration);
    mframeAnimation.addFrame(frame2, reasonableDuration);
    mframeAnimation.addFrame(frame3, reasonableDuration);
    mframeAnimation.addFrame(frame4, reasonableDuration);
    mframeAnimation.addFrame(frame5, reasonableDuration);
    mframeAnimation.addFrame(frame6, reasonableDuration);


    img.setBackground(mframeAnimation);

    mframeAnimation.setVisible(true,true);
    mframeAnimation.start();
}

    private void stopAnimation(){
        ImageView img = getActivity().findViewById(R.id.brandonStarPower);

        mframeAnimation.stop();
        mframeAnimation.setVisible(false,false);

    }



    public void addContacts (){
        ArrayList<ContentProviderOperation> operations = new ArrayList<>(2);

        ContentProviderOperation.Builder op =
                ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null);
        operations.add(op.build());

        op = ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE,
                        ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                        "__DUMMY CONTACT from runtime permissions sample");
        operations.add(op.build());

        // Apply the operations.
        ContentResolver resolver = getActivity().getContentResolver();
        try {
            resolver.applyBatch(ContactsContract.AUTHORITY, operations);
            Toast.makeText(getActivity(), "New contact inserted!", Toast.LENGTH_SHORT)
                    .show();
        } catch (RemoteException e) {
            Log.d(TAG, "Could not add a new contact: " + e.getMessage());
        } catch (OperationApplicationException e) {
            Log.d(TAG, "Could not add a new contact: " + e.getMessage());
        }
    }

}
// Brandon Aaron Rodrigues 301126222 SEC.02