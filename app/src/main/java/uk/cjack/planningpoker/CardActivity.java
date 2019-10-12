package uk.cjack.planningpoker;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import uk.cjack.planningpoker.adapter.PokerCardAdapter;

public class CardActivity extends AppCompatActivity {


    private static final String DEBUG_TAG = "DEBUG";
    public static String SELECTED_CARD = "uk.cjack.planningpoker.selectedcard";
    private String mSelectedCardValue;
    private TextView mSelectedCard;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.selected_card );
        mSelectedCard = findViewById( R.id.selectedCardValue );

        mSelectedCardValue = ( String ) getIntent().getSerializableExtra( SELECTED_CARD );

        if ( mSelectedCardValue != null ) {
            mSelectedCard.setText( mSelectedCardValue );
            ViewGroup.LayoutParams params = mSelectedCard.getLayoutParams();
            mSelectedCard.measure( RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT );
            float factor = 1;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics( displayMetrics );
            int width = ( int ) ( displayMetrics.widthPixels * 0.8 );

            params.height = ( int ) ( width * 1.5 * factor );
            params.width = ( int ) ( width * factor );
            mSelectedCard.setLayoutParams( params );


            mSelectedCard.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick( View view ) {
                    flipCard();
                }
            } );
        }


    }

    private void flipCard() {
        final String endText;

        if ( mSelectedCard.getText().length() == 0 ) {
//                        mSelectedCard.setText( mSelectedCardValue );
            endText = mSelectedCardValue;
        }
        else
        {
//                        mSelectedCard.setText( null );
            endText = null;
        }
        mSelectedCard.animate().withLayer()
                .rotationY( 90 )
                .setDuration( 200 )
                .withEndAction(
                        new Runnable() {
                            @Override
                            public void run() {

                                    mSelectedCard.setText( endText );
                                // second quarter turn
                                float scale =
                                        getApplicationContext().getResources().getDisplayMetrics().density;
                                int distance =
                                        ( int ) ( mSelectedCard.getCameraDistance() * ( scale + ( scale / 3 ) ) );
                                mSelectedCard.setCameraDistance( distance );
                                mSelectedCard.setRotationY( -90 );
                                mSelectedCard.animate().withLayer()
                                        .rotationY( 0 )
                                        .setDuration( 200 )
                                        .start();
                            }
                        }
                ).start();
    }

    @Override
    public boolean onTouchEvent( MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                Log.d(DEBUG_TAG,"Action was DOWN");
                return true;

            case (MotionEvent.ACTION_MOVE) :
                Log.d(DEBUG_TAG,"Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP) :
                Log.d(DEBUG_TAG,"Action was UP");
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                Log.d(DEBUG_TAG,"Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                Log.d(DEBUG_TAG,"Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }
}
