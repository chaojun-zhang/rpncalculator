package calculator.rpn;

import lombok.Data;
import org.apache.commons.lang3.math.NumberUtils;

import java.text.DecimalFormat;

@Data
public class NumberLiteralToken extends Token {

    private String number;

    private boolean calculated;

    public NumberLiteralToken(int id, String number){
        this(id, number, false);
    }

    public NumberLiteralToken(int id, String number, boolean calculated){
        super(id);
        this.number = number;
        this.calculated = calculated;
    }

    private Number numberObj;

    public NumberLiteralToken(int id, Number number, boolean calculated){
        super(id);
        this.numberObj = number;
        this.calculated = true;
    }

    public Number getLiteral(){

        if (numberObj != null) {
            return numberObj;
        }

        if (NumberUtils.isDigits(number)){
            return Long.valueOf(number);
        }
        return Double.valueOf(number);
    }

    public String formatText(){
        Number value = getLiteral();
        if (value instanceof Double) {
            DecimalFormat decimalFormat = new DecimalFormat("0.##########");
            return decimalFormat.format(value);
        }else{
            return value.toString();
        }
    }

    @Override
    void accept(TokenVisitor visitor) {

        visitor.visit(this);
    }
}