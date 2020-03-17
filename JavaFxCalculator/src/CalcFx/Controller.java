package CalcFx;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class Controller {

    public Label calcLabel = null, calcLabel1 = null, hLabel = null;
    public Button eqBtn = null, oneBtn = null, twoBtn = null, threeBtn = null, fourBtn = null, fiveBtn = null, sixBtn = null,
            sevenBtn = null, eightBtn = null, nineBtn = null, plusBtn = null, minusBtn = null, mulBtn = null, sqrtBtn = null, divBtn = null,
            clearBtn = null, eBtn = null, delBtn = null, zeroBtn = null, piBtn = null, sinBtn = null, cosBtn = null, tanBtn = null, caretBtn = null,
            modBtn = null, dotBtn = null, facBtn = null, lParenBtn = null, rParenBtn = null, upBtn = null, downBtn = null;
    Calculate calc = new Calculate();
    String result = "";
    int hnum = 0, prenum = 0;

    ArrayList<String> arrList = new ArrayList<String>();

    public void onClickEvent(MouseEvent mouseEvent){

        if(mouseEvent.getSource() == eqBtn) {
            calcLabel1.setText(calc.calcMain(calcLabel.getText()));
            if(arrList.size() < 1) {
                arrList.add(result);
                hnum++;
            }
            prenum = hnum;
            if(arrList.size() >= 1)
                if(!result.equalsIgnoreCase(arrList.get(arrList.size() - 1))){
                    hnum++;
                    arrList.add(result);
                }
            hLabel.setText(Integer.toString(hnum));
            prenum = hnum - 1;
        }
        else if(mouseEvent.getSource() == upBtn) {
            if(prenum < arrList.size()-1 && prenum >= 0){
                prenum++;
            }
            hLabel.setText(Integer.toString(prenum+1));
            calcLabel.setText(arrList.get(prenum));
        }
        else if(mouseEvent.getSource() == downBtn) {
            if(prenum >= 1) {
                prenum = prenum - 1;
                hLabel.setText(Integer.toString(prenum + 1));
                calcLabel.setText(arrList.get(prenum));
            }
        }
        else if(mouseEvent.getSource() == oneBtn) {
            result = result + "1";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == twoBtn){
            result = result + "2";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == threeBtn){
            result = result + "3";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == fourBtn){
            result = result + "4";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == fiveBtn){
            result = result + "5";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == sixBtn){
            result = result + "6";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == sevenBtn){
            result = result + "7";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == eightBtn){
            result = result + "8";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == nineBtn){
            result = result + "9";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == zeroBtn){
            result = result + "0";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == piBtn){
            result = result + "π";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == sinBtn){
            result = result + "sin";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == cosBtn){
            result = result + "cos";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == tanBtn){
            result = result + "tan";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == caretBtn){
            result = result + "^";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == modBtn){
            result = result + "%";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == plusBtn){
            result = result + "+";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == minusBtn){
            result = result + "-";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == mulBtn){
            result = result + "*";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == divBtn){
            result = result + "/";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == dotBtn){
            result = result + ".";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == eBtn){
            result = result + "e";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == sqrtBtn){
            result = result + "√";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == facBtn){
            result = result + "!";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == lParenBtn){
            result = result + "(";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == rParenBtn){
            result = result + ")";
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == delBtn){
            result = result.substring(0,(result.length()-1));
            calcLabel.setText(result);
        }
        else if(mouseEvent.getSource() == clearBtn) {
            calcLabel.setText("");
            calcLabel1.setText("");
            hLabel.setText("");
            arrList.clear();
            prenum = 0;
            hnum = 0;
            result = "";
        }

    }
}
