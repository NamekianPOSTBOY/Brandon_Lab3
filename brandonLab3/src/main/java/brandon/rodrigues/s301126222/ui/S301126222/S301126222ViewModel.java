package brandon.rodrigues.s301126222.ui.S301126222;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class S301126222ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public S301126222ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
// Brandon Aaron Rodrigues 301126222 SEC.02