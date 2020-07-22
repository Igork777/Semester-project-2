package handin_3.client.view;

import handin_3.client.core.ViewHandler;
import handin_3.client.viewModel.ViewModel;

public interface ViewController {
    void init(ViewModel vm, ViewHandler vh);
}
