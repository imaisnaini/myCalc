package imaisnaini.mycalc;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvEdit) TextView tvEdit;
    @BindView(R.id.tvView) TextView tvView;

    Double input1 , input2, hasil;
    String op, flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //initFont();
        reset();
    }

    private void initFont(){
        Typeface typeface = Typeface.createFromAsset(getAssets(), "ERASLGHT.TTF");

        tvEdit.setTypeface(typeface);
        tvView.setTypeface(typeface);
    }

    public void setTvEdit (Button button){
        if (tvEdit.getText().toString().equals(hasil.toString())){
            reset();
            tvEdit.setText(tvEdit.getText().toString() + button.getText().toString());
        }else {
            tvEdit.setText(tvEdit.getText().toString() + button.getText().toString());
        }
    }

    public void reset (){
        input1 = Double.valueOf(0);
        input2 = Double.valueOf(0);
        hasil = Double.valueOf(0);
        flag = "0";
        tvEdit.setText("");
        tvView.setText("0");
    }

    public void hitung(){
        input2 = Double.parseDouble(tvEdit.getText().toString());
        if(op == "+"){
            hasil = input1 + input2;
        }else if(op == "-"){
            hasil = input1 - input2;
        }else if(op == "*"){
            hasil = input1 * input2;
        }else if(op == "/"){
            hasil = input1 / input2;
        }else if(op == "%"){
            hasil = input1 % input2;
        }
        tvView.setText(String.valueOf(input1) + op + String.valueOf(input2) + "=");
        tvEdit.setText(String.valueOf(hasil));
    }

    public void setOperation(String s){
        op = s;
        input1 = Double.parseDouble(tvEdit.getText().toString());
        tvEdit.setText("");
        tvView.setText(input1.toString() + op);
    }

    @OnClick(R.id.btn0) public void setBtn0 (Button button){
        setTvEdit(button);
    }
    @OnClick(R.id.btn1) public void setBtn1 (Button button){
        setTvEdit(button);
    }
    @OnClick(R.id.btn2) public void setBtn2 (Button button){
        setTvEdit(button);
    }
    @OnClick(R.id.btn3) public void setBtn3 (Button button){
        setTvEdit(button);
    }
    @OnClick(R.id.btn4) public void setBtn4 (Button button){
        setTvEdit(button);
    }
    @OnClick(R.id.btn5) public void setBtn5 (Button button){
        setTvEdit(button);
    }
    @OnClick(R.id.btn6) public void setBtn6 (Button button){
        setTvEdit(button);
    }
    @OnClick(R.id.btn7) public void setBtn7 (Button button){
        setTvEdit(button);
    }
    @OnClick(R.id.btn8) public void setBtn8 (Button button){
        setTvEdit(button);
    }
    @OnClick(R.id.btn9) public void setBtn9 (Button button){
        setTvEdit(button);
    }
    @OnClick(R.id.btnComa) public void setBtnComa (Button button){
        setTvEdit(button);
    }
    @OnClick(R.id.btnClear) public void setBtnClear (){
        reset();
    }
    @OnClick(R.id.btnAdd) public void setBtnAdd (Button button){
        setOperation("+");
    }
    @OnClick(R.id.btnMinus) public void setBtnMinus (Button button){
        setOperation("-");
    }
    @OnClick(R.id.btnMultiply) public void setBtnMultiply (Button button){
        setOperation("*");
    }
    @OnClick(R.id.btnDiv) public void setBtnDiv (Button button){
        setOperation("/");
    }
    @OnClick(R.id.btnMod) public void setBtnMod (Button button){
        setOperation("%");
    }
    @OnClick(R.id.btnResult) public void setBtnResult (){
        hitung();
    }
    @OnClick(R.id.btnDel) public void setBtnDel (Button button){
        
    }
}
