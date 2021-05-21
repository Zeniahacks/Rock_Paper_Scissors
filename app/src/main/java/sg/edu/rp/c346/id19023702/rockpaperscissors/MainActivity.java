package sg.edu.rp.c346.id19023702.rockpaperscissors;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_rock, button_paper, button_scissor;

    //default value assigned to user_selection
    private static Selection user_selection = Selection.SCISSORS;
    private static Selection cpu_selection = Selection.ROCK;

    // assigning boolean to winner or loser
    private static boolean user_winner = true;
    private static boolean match_draw = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        //Finding the different buttons by ID
        button_rock = findViewById(R.id.button_rock);
        button_paper = findViewById(R.id.button_paper);
        button_scissor = findViewById(R.id.button_scissor);

        // Able to have an action when clicked on
        button_rock.setOnClickListener(this);
        button_paper.setOnClickListener(this);
        button_scissor.setOnClickListener(this);
    }

    //  Cased is used for simple viewing of codes
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.button_rock:
                user_selection = Selection.ROCK;
                break;

            case R.id.button_paper:
                user_selection = Selection.PAPER;
                break;

            case R.id.button_scissor:
                user_selection = Selection.SCISSORS;
                break;
        }

        proceed();
    }

    private void proceed() {
        //get random cpu selection
        cpu_selection = getRandomCPUSelection();

        //get winner based on cpu_selection and user_selection
        checkWinner();

        //print result based on result
        showWinner();
    }

    //function return random value below 3
    private Selection getRandomCPUSelection() {
        int random = new Random().nextInt(3);

        switch (random){
            case 0: return Selection.ROCK;
            case 1: return Selection.PAPER;
            case 2: return Selection.SCISSORS;
        }
        return Selection.ROCK;
    }

    // Possible results from the Fights
    private void checkWinner() {

        if(user_selection == cpu_selection){
            match_draw = true;
            return;
        }

        //rock wins from scissor, loose from paper
        if(user_selection == Selection.ROCK){
            if(cpu_selection == Selection.PAPER){
                user_winner = false;
                return;
            }
            else if(cpu_selection == Selection.SCISSORS){
                user_winner = true;
                return;
            }
        }

        //paper wins from rock, loose from scissor
        if(user_selection == Selection.PAPER){
            if(cpu_selection == Selection.SCISSORS){
                user_winner = false;
                return;
            }
            else if(cpu_selection == Selection.ROCK){
                user_winner = true;
                return;
            }
        }

        //scissor wins from paper, loose from rock
        if(user_selection == Selection.SCISSORS){
            if(cpu_selection == Selection.ROCK){
                user_winner = false;
                return;
            }
            else if(cpu_selection == Selection.PAPER){
                user_winner = true;
                return;
            }
        }

        Log.e("cpu selected",cpu_selection.toString());
        Log.e("user selected",user_selection.toString());
    }

    //show dialog to show result
    private void showWinner() {

        String line1 = "USER selection : "+user_selection;
        String line2 = "CPU selection : "+cpu_selection;
        String result = getResultString();

        String message = line1 + "\n" + line2 + "\nResult: " + result;

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Result")
                .setMessage(message)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // close dialog
                        dialog.dismiss();
                    }
                })
                .setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        //reset values when dialog is dismissed
                        resetValues();
                    }
                })
                .show();
    }

    //values reset for next game
    private void resetValues() {
        user_selection = Selection.SCISSORS;
        cpu_selection = Selection.ROCK;

        user_winner = true;
        match_draw = false;
        Log.e(MainActivity.class.getName(), "values reset successful");
    }

    //function return result string based on user_selection and cpu_selection
    private String getResultString() {

        String result;
        if(match_draw == true){
            result = "Draw! Let's try again";
        }
        else{
            if(user_winner)
                result = "You win!";
            else
                result = "You lose!!!";
        }

        return result;
    }


}
