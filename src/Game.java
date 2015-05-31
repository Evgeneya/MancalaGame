import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static javafx.geometry.HPos.*;


/**
 * Created by 1 on 08.05.2015.
 */
public class Game extends Application {

    private Board board = new Board();
    private boolean gear = true;
    private int step = 0;
    GridPane grid;
    MyButton pointOne;
    MyButton pointTwo;
    MyButton pointThree;
    MyButton pointFour;
    MyButton pointFive;
    MyButton pointSix;
    Label pointMancala;
    MyButton compOne;
    MyButton compTwo;
    MyButton compThree;
    MyButton compFour;
    MyButton compFive;
    MyButton compSix;
    Label compMancala;

    Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception {        primaryStage = stage;
        primaryStage.setTitle("Mancala");
        primaryStage.getIcons().add(new Image("mancalaIcon.png"));
        primaryStage.setResizable(false);
        grid = new GridPane();
        Scene scene = new Scene(grid, 640, 360);
        scene.getStylesheets().add
                (this.getClass().getResource("Style.css").toExternalForm());
        primaryStage.setScene(scene);

        grid.setPadding(new Insets(10, 5, 5, 5));

        grid.getRowConstraints().clear();
        grid.getColumnConstraints().clear();
        grid.getColumnConstraints().add(new ColumnConstraints(90));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(5));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(6));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(6));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(6));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(6));
        grid.getColumnConstraints().add(new ColumnConstraints(70));
        grid.getColumnConstraints().add(new ColumnConstraints(85));

        grid.getRowConstraints().add(new RowConstraints(50));
        grid.getRowConstraints().add(new RowConstraints(85));
        grid.getRowConstraints().add(new RowConstraints(85));
        grid.getRowConstraints().add(new RowConstraints(85));
        grid.getRowConstraints().add(new RowConstraints(50));


        Label label1 = new Label("CompMancala");
        label1.setStyle("-fx-font-size: 12; -fx-text-fill: yellow;");
        Label label2 = new Label("YourMancala");
        label2.setStyle("-fx-font-size: 12; -fx-text-fill: yellow;");
        grid.add(label1, 0, 0, 1, 1);
        grid.setHalignment(label1, CENTER);
        grid.setValignment(label1, VPos.BOTTOM);
        grid.add(label2, 12, 3, 1, 1);
        grid.setHalignment(label2, CENTER);
        grid.setValignment(label2, VPos.BOTTOM);

        pointOne = new MyButton();
        grid.add(pointOne, 1, 3, 1, 1);
        pointTwo = new MyButton();
        grid.add(pointTwo, 3, 3, 1, 1);
        pointThree = new MyButton();
        grid.add(pointThree, 5, 3, 1, 1);
        pointFour = new MyButton();
        grid.add(pointFour, 7, 3, 1, 1);
        pointFive = new MyButton();
        grid.add(pointFive, 9, 3, 1, 1);
        pointSix = new MyButton();
        grid.add(pointSix, 11, 3, 1, 1);

        compOne = new MyButton();
        grid.add(compOne, 1, 1, 1, 1);
        compTwo = new MyButton();
        grid.add(compTwo, 3, 1, 1, 1);
        compThree = new MyButton();
        grid.add(compThree, 5, 1, 1, 1);
        compFour = new MyButton();
        grid.add(compFour, 7, 1, 1, 1);
        compFive = new MyButton();
        grid.add(compFive, 9, 1, 1, 1);
        compSix = new MyButton();
        grid.add(compSix, 11, 1, 1, 1);

        pointMancala = new Label("0");
        pointMancala.setStyle("-fx-font-size: 30; -fx-text-fill: red;");
        grid.add(pointMancala, 12, 2, 10, 1);
        compMancala = new Label("0");
        compMancala.setStyle("-fx-font-size: 30; -fx-text-fill: red;");
        grid.add(compMancala, 0, 2, 1, 1);


        grid.setHalignment(pointMancala, CENTER);
        grid.setHalignment(compMancala, CENTER);

        pointOne.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                step(pointOne);
            }
        });

        pointTwo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                step(pointTwo);
            }
        });

        pointThree.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                step(pointThree);
            }
        });
        pointFour.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                step(pointFour);
            }
        });
        pointFive.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                step(pointFive);
            }
        });
        pointSix.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                step(pointSix);
            }
        });

        //grid.setGridLinesVisible(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void step(Button button) {
        this.gear = true;
        if(button.equals(this.pointOne)) {
            if(this.board.getSum(1) != 0) {
                this.gear = this.board.Step(1, 1);
            }
        } else if(button.equals(this.pointTwo)) {
            if(this.board.getSum(2) != 0) {
                this.gear = this.board.Step(2, 1);
            }
        } else if(button.equals(this.pointThree)) {
            if(this.board.getSum(3) != 0) {
                this.gear = this.board.Step(3, 1);
            }
        } else if(button.equals(this.pointFour)) {
            if(this.board.getSum(4) != 0) {
                this.gear = this.board.Step(4, 1);
            }
        } else if(button.equals(this.pointFive)) {
            if(this.board.getSum(5) != 0) {
                this.gear = this.board.Step(5, 1);
            }
        } else if(button.equals(this.pointSix) && this.board.getSum(6) != 0) {
            this.gear = this.board.Step(6, 1);
        }
        print();
        Comp comp;
        if(!this.gear) {
            for(this.gear = true; this.gear; this.gear = this.board.Step(comp.getBest())) {
                comp = new Comp(this.step, this.board);
                print();
            }
        }
        print();

        if(this.board.CheckEndGame()) {
            print();
            final Stage result = new Stage();
            result.setTitle("Result");
            String s = this.board.winner();
            if (s.equals("You lose!")){
                result.getIcons().add(new Image("loser.png"));
            }
            else if (s.equals("You win!")){                result.getIcons().add(new Image("winner.png"));

            }
            else {
                result.getIcons().add(new Image("drawn.png"));
            }

            Group root = new Group();
            Scene scene = new Scene(root, 200, 100, Color.SALMON);
            result.setResizable(false);
            result.setScene(scene);

            Label status = new Label();
            status.setText(" " + s);
            status.setStyle("-fx-font-size: 22px;" +
                    "    -fx-font-weight: bold;" +
                    "    -fx-text-fill: whitesmoke;" +
                    "    -fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
            Label question = new Label();
            question.setText("    Do You want to play again?");
            question.setStyle("-fx-font-size: 12px;" +
                    "    -fx-font-weight: bold;" +
                    "    -fx-text-fill: darkviolet;" +
                    "    -fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );");
            Button YES = new Button("YES");
            Button NO = new Button("NO");
            HBox hBox = new HBox();
            hBox.getChildren().addAll(YES, NO);
            hBox.setSpacing(20);
            hBox.setAlignment(Pos.CENTER);
            VBox vBox = new VBox();
            vBox.setSpacing(15);
            vBox.getChildren().addAll(status, question, hBox);
            vBox.setAlignment(Pos.CENTER);

            root.getChildren().add(vBox);
            result.show();
            NO.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    result.close();
                    primaryStage.close();
                    return;
                }
            });
            YES.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    result.close();
                    board = new Board();
                    print();
                }
            });
        }
    }

    public void print() {
        int[][] gameBoard = this.board.getBoard();          //Зачем создавать копию массива?
        //this.compOne.setText(String.valueOf(this.board.getBoard()[0][0]));
        this.compOne.setText(String.valueOf(gameBoard[0][0]));
        this.compTwo.setText(String.valueOf(gameBoard[0][1]));
        this.compThree.setText(String.valueOf(gameBoard[0][2]));
        this.compFour.setText(String.valueOf(gameBoard[0][3]));
        this.compFive.setText(String.valueOf(gameBoard[0][4]));
        this.compSix.setText(String.valueOf(gameBoard[0][5]));
        this.pointOne.setText(String.valueOf(gameBoard[1][0]));
        this.pointTwo.setText(String.valueOf(gameBoard[1][1]));
        this.pointThree.setText(String.valueOf(gameBoard[1][2]));
        this.pointFour.setText(String.valueOf(gameBoard[1][3]));
        this.pointFive.setText(String.valueOf(gameBoard[1][4]));
        this.pointSix.setText(String.valueOf(gameBoard[1][5]));
        this.compMancala.setText(String.valueOf(this.board.getCompMancala()));
        this.pointMancala.setText(String.valueOf(this.board.getYouMancala()));
    }
}
