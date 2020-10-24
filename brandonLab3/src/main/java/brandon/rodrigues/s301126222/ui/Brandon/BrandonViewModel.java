package brandon.rodrigues.s301126222.ui.Brandon;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BrandonViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BrandonViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
// Brandon Aaron Rodrigues 301126222 SEC.02