package brandon.rodrigues.s301126222.ui.Rodrigues;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RodriguesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RodriguesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
// Brandon Aaron Rodrigues 301126222 SEC.02