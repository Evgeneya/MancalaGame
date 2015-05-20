import javafx.scene.control.Button;

/**
 * Created by 1 on 08.05.2015.
 */
public class MyButton extends Button{
    public MyButton(){
        super();
        setStyle("-fx-background-color: rgba(0,0, 0, 0); -fx-font-size: 30; -fx-text-fill: orange;");
        setMinSize(68, 85);
        setText("3");
    }
}
