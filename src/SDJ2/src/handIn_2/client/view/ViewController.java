package handIn_2.client.view;

import handIn_2.client.core.ViewHandler;
import handIn_2.client.viewModel.ViewModel;

public interface ViewController {
    void init(ViewModel vm, ViewHandler vh);
}
