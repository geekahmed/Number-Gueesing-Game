import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javax.swing.*;

public class GuessingGame extends Application {
    private Label game_tries;
    private Label game_msg;
    private Label game_instructions;
    private RadioButton easy_radioButton, medium_radioButton, hard_radioButton;
    private Button start_button, submit_button, quit_button;
    private Scene intro_scene, main_scene;
    private Game new_game;
    private TextField guess_field;
    private Stage window;
    private static final String gameInstructions = "1- You Should Choose Game difficulty.\n" +
            "- Easy: Range [1,100] and 15 Tries.\n" +
            "- Medium: Range [1, 600] and 10 tries.\n" +
            "- Hard: Range [1, 1000] and 5 tries.\n" +
            "2- Guess the number and Submit.\n";
    @Override
    public void start(Stage primaryStage){
        window = primaryStage;
        ui_intro();
        first_scene_control();

        primaryStage.setTitle("Guessing Game");
        primaryStage.setResizable(false);
        primaryStage.setScene(intro_scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    // Drawing the intro user interface
    public void ui_intro(){
        SplitPane mainPane = new SplitPane();
        intro_scene = new Scene(mainPane);
        window.setScene(intro_scene);
        // Setting UI Properties for the main pane.
        mainPane.setOrientation(Orientation.VERTICAL);
        mainPane.setMinHeight(Double.NEGATIVE_INFINITY);
        mainPane.setMinWidth(Double.NEGATIVE_INFINITY);
        mainPane.setMaxHeight(Double.NEGATIVE_INFINITY);
        mainPane.setMaxWidth(Double.NEGATIVE_INFINITY);
        mainPane.setPrefHeight(400.0);
        mainPane.setPrefWidth(600.0);
        mainPane.setDividerPositions(0.6683417085427136);


        AnchorPane anchorPaneTop = new AnchorPane();
        AnchorPane anchorPaneDown = new AnchorPane();
        mainPane.getItems().addAll(anchorPaneTop, anchorPaneDown);
        anchorPaneTop.setMinHeight(0.0);
        anchorPaneTop.setMinWidth(0.0);
        anchorPaneTop.setPrefHeight(100.0);
        anchorPaneTop.setPrefWidth(160.0);

        Label titleLabel = new Label();
        titleLabel.setLayoutX(172.0);
        titleLabel.setLayoutY(14.0);
        titleLabel.setText("Guessing Game");
        titleLabel.setFont(Font.font("System", FontWeight.BOLD, 29.0));

        HBox introHBox = new HBox();
        introHBox.setAlignment(Pos.TOP_CENTER);
        introHBox.setLayoutX(174.0);
        introHBox.setLayoutY(71.0);
        introHBox.setSpacing(8.0);
        introHBox.setPadding(new Insets(10, 10, 10, 10));

        easy_radioButton = new RadioButton();
        easy_radioButton.setMnemonicParsing(false);
        easy_radioButton.setText("Easy");
        easy_radioButton.setTextFill(Color.valueOf("#8608da"));
        easy_radioButton.setFont(Font.font("System", FontWeight.BOLD, 14.0));

        medium_radioButton = new RadioButton();
        medium_radioButton.setMnemonicParsing(false);
        medium_radioButton.setText("Medium");
        medium_radioButton.setTextFill(Color.valueOf("#ae108a"));
        medium_radioButton.setFont(Font.font("System", FontWeight.BOLD, 14.0));

        hard_radioButton = new RadioButton();

        hard_radioButton.setMnemonicParsing(false);
        hard_radioButton.setText("Hard");
        hard_radioButton.setTextFill(Color.RED);
        hard_radioButton.setFont(Font.font("System", FontWeight.BOLD, 14.0));

        ToggleGroup toggleGroupIntro = new ToggleGroup();
        easy_radioButton.setToggleGroup(toggleGroupIntro);
        hard_radioButton.setToggleGroup(toggleGroupIntro);
        medium_radioButton.setToggleGroup(toggleGroupIntro);


        introHBox.getChildren().addAll(easy_radioButton, medium_radioButton, hard_radioButton);

        start_button = new Button();

        start_button.setLayoutX(227.0);
        start_button.setLayoutY(131.0);
        start_button.setMnemonicParsing(false);
        start_button.setPrefHeight(28.0);
        start_button.setPrefWidth(144.0);
        start_button.setText("Start");
        start_button.setTextAlignment(TextAlignment.CENTER);
        start_button.setTextFill(Color.valueOf("#63901e"));
        start_button.setFont(Font.font("System", FontWeight.BOLD, 18.0));



        anchorPaneTop.getChildren().addAll(titleLabel, introHBox, start_button);

        anchorPaneDown.setMinHeight(0.0);
        anchorPaneDown.setMinWidth(0.0);
        anchorPaneDown.setPrefHeight(100.0);
        anchorPaneDown.setPrefWidth(160.0);

        Label instruction_label = new Label();
        instruction_label.setLayoutX(14.0);
        instruction_label.setLayoutY(6.0);
        instruction_label.setText("Game Instruction");
        instruction_label.setTextFill(Color.valueOf("#4f0fda"));
        instruction_label.setFont(Font.font("System", FontWeight.BOLD, 13.0));
        Label gameInstructionLabelText = new Label();
        gameInstructionLabelText.setText(gameInstructions);
        gameInstructionLabelText.setLayoutX(14.0);
        gameInstructionLabelText.setLayoutY(28.0);
        gameInstructionLabelText.setTextFill(Color.valueOf("#d02b2b"));
        gameInstructionLabelText.setFont(Font.font("System", FontWeight.BOLD, 12.0));
        anchorPaneDown.getChildren().addAll(instruction_label, gameInstructionLabelText);
    }

    // Drawing the game UI.
    public void main_scene_ui(){
        Pane gamePane = new Pane();
        game_instructions = new Label();
        game_msg = new Label();
        Label inGameTriesLabel = new Label();
        game_tries = new Label();
        submit_button = new Button();
        quit_button = new Button();
        Label yourGuessLabel = new Label();
        guess_field = new TextField();
        gamePane.setMinHeight(Double.NEGATIVE_INFINITY);
        gamePane.setMinWidth(Double.NEGATIVE_INFINITY);
        gamePane.setMaxHeight(Double.NEGATIVE_INFINITY);
        gamePane.setMaxWidth(Double.NEGATIVE_INFINITY);

        gamePane.setPadding(new Insets(10, 10, 10, 10));

        game_msg.setLayoutX(170.0);
        game_msg.setLayoutY(14.0);
        game_msg.setText("Guess The Secret Number");
        game_msg.setFont(Font.font("System" ,FontWeight.BOLD,FontPosture.ITALIC, 18.0));

        yourGuessLabel.setLayoutX(136.0);
        yourGuessLabel.setLayoutY(61.0);
        yourGuessLabel.setText("Your Guess?");

        guess_field.setLayoutX(241.0);
        guess_field.setLayoutY(56.0);

        submit_button.setLayoutX(148.0);
        submit_button.setLayoutY(109.0);
        submit_button.setMnemonicParsing(false);
        submit_button.setText("Submit");

        quit_button.setLayoutX(388.0);
        quit_button.setLayoutY(109.0);
        quit_button.setMnemonicParsing(false);
        quit_button.setText("Give Up");

        game_instructions.setLayoutX(152.0);
        game_instructions.setLayoutY(151.0);
        game_instructions.setText("Instructions Message");



        inGameTriesLabel.setLayoutX(504.0);
        inGameTriesLabel.setLayoutY(188.0);
        inGameTriesLabel.setText("Tries");

        game_tries.setLayoutX(555.0);
        game_tries.setLayoutY(188.0);
        game_tries.setTextFill(Color.valueOf("#da3636"));
        game_tries.setText("");
        gamePane.getChildren().addAll(game_msg, yourGuessLabel, guess_field, submit_button, quit_button, game_instructions, inGameTriesLabel, game_tries);
        main_scene = new Scene(gamePane);

    }

    public void btn_submission(){
        game_instructions.setText(""); // Setting the in_game instructions to empty when submitting.
        String input = guess_field.getText().trim(); // Getting the input from the text area.
        guess_field.setText(""); // Setting the guessing field to empty.
        if (input.isEmpty())
        {
            game_instructions.setText("The Text Field is Empty");
            return;
        }
        boolean ok = new_game.checkGuess( Integer.parseInt(input) ); // getting the correctness
        game_msg.setText(new_game.getMessage());
        game_tries.setText(new_game.getNumber_of_tries() + "");

        if (new_game.getMessage().equals("Game Over"))
        {
            game_tries.setText(0 + "");

            giveup_btn();
        }
        if (!ok)
        {
            return;
        }

        guess_field.setDisable(true);
        // Message in the end of the game.
        int reply = JOptionPane.showConfirmDialog(null, "Right! You guessed the secret number.\nPlay again?", "Guessing Game", JOptionPane.YES_NO_OPTION);
        if (reply != JOptionPane.YES_OPTION) Platform.exit();
        if (reply == JOptionPane.YES_OPTION) {
            window.setScene(intro_scene);
        }
    }



    // Give up button will show the true guess.
    public void giveup_btn(){
        guess_field.setDisable(true);
        int reply = JOptionPane.showConfirmDialog(null,
                "Game Over, The Correct Secret is " + new_game.getTrue_guess() + "\nPlay again?",
                "Guessing Game",
                JOptionPane.YES_NO_OPTION);
        if (reply != JOptionPane.YES_OPTION) Platform.exit();
        if (reply == JOptionPane.YES_OPTION)
        {
            window.setScene(intro_scene);
        }

    }


    // intro scene controller which controls the selection of the difficulty.
    public void main_scene_control(){
        if (easy_radioButton.isSelected())
        {
            new_game = Game.generateEasyGame();
        } else if (medium_radioButton.isSelected())
        {
            new_game = Game.generateMediumGame();
        } else if (hard_radioButton.isSelected())
        {
            new_game = Game.generateHardGame();
        }
        main_scene_ui();
        game_instructions.setText(new_game.getMessage());
        game_tries.setText(new_game.getNumber_of_tries()+ "");
        window.setScene(main_scene);

        submit_button.setOnAction(e -> {
            btn_submission();
        });

        quit_button.setOnAction(e -> giveup_btn());

    }


    public void first_scene_control(){
        ui_intro();
        start_button.setOnAction(e -> {
            if (!easy_radioButton.isSelected() && !medium_radioButton.isSelected() && !hard_radioButton.isSelected())
            {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error In Selecting Difficulty!!");
                alert.setContentText("Please Select Game Difficulty");
                alert.showAndWait();
            } else
                {
                main_scene_control();
            }
        });
    }

}
