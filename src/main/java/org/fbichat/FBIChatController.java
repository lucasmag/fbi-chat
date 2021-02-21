package org.fbichat;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.fbichat.entrys.Message;
import org.fbichat.entrys.Spy;
import org.fbichat.entrys.User;
import org.fbichat.utils.UserResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class FBIChatController {

    @FXML
    private JFXTextField userNameField;

    @FXML
    private JFXListView<User> userList;

    @FXML
    private Label userResultLabel;

    @FXML
    private JFXListView<User> friendsList;

    @FXML
    private Label talkingWith;

    @FXML
    private VBox chat;

    @FXML
    private AnchorPane userWindow;

    @FXML
    private AnchorPane chatPane;

    @FXML
    private JFXTextArea message;

    @FXML
    private AnchorPane userView;

    @FXML
    private Label selectedUserLabel;

    @FXML
    private JFXTextField wordInput;

    @FXML
    private AnchorPane spyPane;

    @FXML
    private JFXButton removeWordButton;

    @FXML
    private JFXListView<Spy> wordList;

    User selectedUser;
    User selectedFriend;

    @FXML
    void cancelUser(ActionEvent event) {
        userWindow.setVisible(false);
        clearUserWindow();
    }

    @FXML
    void newUser(ActionEvent event) {
        userWindow.setVisible(true);
    }

    @FXML
    void addUser(ActionEvent event) {
        String userName = userNameField.getText();

        if(!userName.equals("")) {
            User user = new User(userName);

            UserResult result = user.save();

            if(result == UserResult.CREATED) {
                userList.getItems().setAll(User.getAll());
                userWindow.setVisible(false);

                clearUserWindow();
            } else {
                userResultLabel.setText(result.getText());
            }
        }
    }

    private void clearUserWindow(){
        userResultLabel.setText("");
        userNameField.setText("");
    }


    @FXML
    void sendMessage(ActionEvent event) {
        String messageToSend = message.getText();
        if (selectedUser != null && selectedFriend != null && !messageToSend.equals("")) {
            Message msg = new Message(selectedUser, selectedFriend, messageToSend, new Date());
            msg.send();
            Spy.scan(msg);

            chat.getChildren().setAll(Message.getAll(selectedUser, selectedFriend));
            message.setText("");
        }
    }

    @FXML
    void backToFriendsList(ActionEvent event) {
        chat.getChildren().clear();
        chatPane.setVisible(false);
        talkingWith.setText("");
    }

    @FXML
    void closeUserView(ActionEvent event) {
        userView.setVisible(false);
        chatPane.setVisible(false);
        chat.getChildren().clear();
        friendsList.getItems().removeAll();
    }

    private boolean lineWasDoubleClicked(MouseEvent event, User env) {
        return event.getButton() == MouseButton.PRIMARY
                && event.getClickCount() == 2
                && event.getTarget() instanceof ListCell
                && !((ListCell<?>) event.getTarget()).isEmpty()
                && env != null;
    }

    private boolean lineWasClicked(MouseEvent event, User env) {
        return event.getButton() == MouseButton.PRIMARY
                && event.getClickCount() == 1
                && event.getTarget() instanceof ListCell
                && !((ListCell<?>) event.getTarget()).isEmpty()
                && env != null;
    }

    @FXML
    void toggleSpy(MouseEvent event) {
        if (spyPane.getTranslateX() == 0.0)
            spyPane.setTranslateX(-320.0);
        else
            spyPane.setTranslateX(0.0);
    }

    public void initialize() {
        Space.start();

        userList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                User user = userList.getSelectionModel().getSelectedItem();
                //Handler na lista de usuarios, se houver dois clicks abre o "perfil" do usuário
                if (lineWasDoubleClicked(click, user)) {
                    userView.setVisible(true);
                    selectedUser = user;
                    selectedUserLabel.setText(user.getName());

                    ArrayList<User> list = (ArrayList<User>)
                            userList
                            .getItems()
                            .stream()
                            .filter((u) -> u != selectedUser)
                            .collect(Collectors.toList());

                    friendsList.getItems().setAll(list);
                }
            }
        });

        friendsList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                User friend = friendsList.getSelectionModel().getSelectedItem();
                if (lineWasDoubleClicked(click, friend)) {
                    selectedFriend = friend;
                    chat.getChildren().setAll(Message.getAll(selectedUser, selectedFriend));
                    chatPane.setVisible(true);
                    talkingWith.setText(friend.getName());
                }
            }
        });
    }

    @FXML
    void addWord(ActionEvent event) {
        String word = wordInput.getText();
        if(!word.equals("")) {
            Spy spy = new Spy(word);
            spy.save();
            wordList.getItems().setAll(Spy.getAll());
        }
    }

    @FXML
    void removeWord(ActionEvent event) {
        Spy spy = wordList.getSelectionModel().getSelectedItem();
        Space.get().take(spy);
        wordList.getItems().setAll(Spy.getAll());
    }
}
