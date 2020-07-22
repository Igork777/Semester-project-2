package handIn_2.client.core;

import handIn_2.client.model.*;



public class ModelFactory  {
    private UserModel userModel;
    private ChatModel chatModel;
    private ClientFactory clientFactory;

    public UserModel getUserModel() {
        return userModel;
    }

    public ChatModel getChatModel() {
        return chatModel;
    }

    public ClientFactory getClientFactory() {
        return clientFactory;
    }

    public ModelFactory(ClientFactory cf) {
        clientFactory = cf;
        userModel = new UserModelImpl();
        chatModel = new ChatModelImpl();

        userModel.addListener(clientFactory.getClient());
        clientFactory.getClient().addListener(userModel);

        chatModel.addListener(clientFactory.getClient());
        clientFactory.getClient().addListener(chatModel);
    }
}
