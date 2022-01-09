package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Stack;

class SizedStack<T> extends Stack<T> {
    private final int maxSize;

    public SizedStack(int size) {
        super();
        this.maxSize = size;
    }

    @Override
    public T push(T object) {
        while(this.size() >= maxSize) this.remove(0);
        return super.push(object);
    }
}

public class MainActivity extends AppCompatActivity {
    TextView prompt;
    SizedStack<Character> operator = new SizedStack<>(1);
    SizedStack<Float> operands = new SizedStack<>(2);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button zero = findViewById(R.id.zero), one = findViewById(R.id.one), two = findViewById(R.id.two), three = findViewById(R.id.three), four = findViewById(R.id.four), five = findViewById(R.id.five), six = findViewById(R.id.six), seven = findViewById(R.id.seven), eight = findViewById(R.id.eight), nine = findViewById(R.id.nine), decimal = findViewById(R.id.decimal), clear = findViewById(R.id.clear), sum = findViewById(R.id.add), difference = findViewById(R.id.subtract), product = findViewById(R.id.multiply), quotient = findViewById(R.id.divide), result = findViewById(R.id.equals);

        zero.setOnClickListener(view -> prompt.setText(getConcat(new String[] {prompt.getText().toString(), "0"})));

        one.setOnClickListener(view -> prompt.setText(getConcat(new String[] {prompt.getText().toString(), "1"})));

        two.setOnClickListener(view -> prompt.setText(getConcat(new String[] {prompt.getText().toString(), "2"})));

        three.setOnClickListener(view -> prompt.setText(getConcat(new String[] {prompt.getText().toString(), "3"})));

        four.setOnClickListener(view -> prompt.setText(getConcat(new String[] {prompt.getText().toString(), "4"})));

        five.setOnClickListener(view -> prompt.setText(getConcat(new String[] {prompt.getText().toString(), "5"})));

        six.setOnClickListener(view -> prompt.setText(getConcat(new String[] {prompt.getText().toString(), "6"})));

        seven.setOnClickListener(view -> prompt.setText(getConcat(new String[] {prompt.getText().toString(), "7"})));

        eight.setOnClickListener(view -> prompt.setText(getConcat(new String[] {prompt.getText().toString(), "8"})));

        nine.setOnClickListener(view -> prompt.setText(getConcat(new String[] {prompt.getText().toString(), "9"})));

        decimal.setOnClickListener(view -> prompt.setText(getConcat(new String[] {prompt.getText().toString(), "."})));

        clear.setOnClickListener(view -> {
            getResetUtil();
            free();
        });

        sum.setOnClickListener(view -> {
            if(!getNumbers()) return;
            if(!operator.isEmpty()) pushResult();
            operator.push('+');
        });

        difference.setOnClickListener(view -> {
            if(!getNumbers()) return;
            if(!operator.isEmpty()) pushResult();
            operator.push('-');
        });

        product.setOnClickListener(view -> {
            if(!getNumbers()) return;
            if(!operator.isEmpty()) pushResult();
            operator.push('*');
        });

        quotient.setOnClickListener(view -> {
            if(!getNumbers()) return;
            if(!operator.isEmpty()) pushResult();
            operator.push('/');
            prompt.setText("");
        });

        result.setOnClickListener(view -> {
            getResetUtil();
            getResetUtil();
        });
    }

    public String getConcat(String[] strings) {
        StringBuilder res = new StringBuilder();
        for (String string : strings) res.append(string);
        return res.toString();
    }

    public boolean getNumbers() {
        prompt = findViewById(R.id.prompt);
        String s = prompt.getText().toString();
        if(s.equals("")) return false;
        operands.push(Float.valueOf(s));
        prompt.setText("");
        return true;
    }

    public Float getResult(Character operate) {
        if(operate == '+') return operands.pop() + operands.pop();
        else if(operate == '-') return operands.pop() - operands.pop();
        else if(operate == '*') return operands.pop() * operands.pop();
        return operands.pop() / operands.pop();
    }

    public void getResetUtil() {
        if(getNumbers()) return;
        prompt.setText(String.format("%s", getResult(operator.pop()).toString()));
    }

    public void pushResult() { operands.push(getResult(operator.pop())); }

    public void free() {
        operator.clear();
        operands.clear();
    }
}