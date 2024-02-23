package mm.pp.clicker.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    public static MutableLiveData<String> port = new MutableLiveData<>("8080");
    public static MutableLiveData<Boolean> needPassword =new MutableLiveData<>(false);
    public static MutableLiveData<Boolean> serverRunning =new MutableLiveData<>(false);
}