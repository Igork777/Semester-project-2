package handIn_2.client.core;

import handIn_2.client.viewModel.ChatViewModel;
import handIn_2.client.viewModel.UserViewModel;

public class ViewModelFactory {
    private UserViewModel userViewModel;
    private ChatViewModel chatViewModel;
    private ModelFactory modelFactory;

    public ViewModelFactory(ModelFactory mf)
    {
        modelFactory = mf;
        this.userViewModel = new UserViewModel(modelFactory.getUserModel());
        this.chatViewModel = new ChatViewModel(modelFactory.getUserModel(), modelFactory.getChatModel());
    }

    public ChatViewModel getChatViewModel() {
        return chatViewModel;
    }

    public UserViewModel getUserViewModel() {
        return userViewModel;
    }

    public ModelFactory getModelFactory() {
        return modelFactory;
    }
}
