package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int activeplayer=0;
    // 1 is for yellow and 0 is for red 2 is for empty
    int gamestate[]={2,2,2,2,2,2,2,2,2};


    int[][] winningposition={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    boolean gamestatus=true;

    public void dropIn(View view){

        ImageView counter=(ImageView)view;

        int tappedcounter=Integer.parseInt(counter.getTag().toString());

        if(gamestate[tappedcounter]==2 && gamestatus) {
            gamestate[tappedcounter] = activeplayer;

            counter.setTranslationY(-1000);

            if (activeplayer == 0) {

                counter.setImageResource(R.drawable.red);
                activeplayer = 1;
            } else {

                counter.setImageResource(R.drawable.purple);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1000).setDuration(300);

            for (int[] winningposition : winningposition) {
                if (gamestate[winningposition[0]] == gamestate[winningposition[1]] && gamestate[winningposition[1]] == gamestate[winningposition[2]] && gamestate[winningposition[0]] != 2) {
                    gamestatus=false;
                    if (activeplayer == 1) {
                        Toast.makeText(this, "red has won the game", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(this, "purple has won the game", Toast.LENGTH_SHORT).show();
                    }

                    Button playagainbutton=(Button)findViewById(R.id.playagainbutton);
                    playagainbutton.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    public void playAgain(View view){

        Button playagainbutton=(Button)findViewById(R.id.playagainbutton);
        playagainbutton.setVisibility(View.INVISIBLE);
        GridLayout gridlayout=(androidx.gridlayout.widget.GridLayout)findViewById(R.id.gridlayout);
        for (int i=0;i<gridlayout.getChildCount();i++){
            ImageView counter=(ImageView)gridlayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }
        activeplayer=0;
        gamestatus=true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
