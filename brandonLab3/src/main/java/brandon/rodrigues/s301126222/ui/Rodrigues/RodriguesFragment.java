package brandon.rodrigues.s301126222.ui.Rodrigues;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
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