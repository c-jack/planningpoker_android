package uk.cjack.planningpoker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rarepebble.colorpicker.ColorPickerView;

import java.util.ArrayList;
import java.util.List;

import uk.cjack.planningpoker.adapter.CardSelectorAdapter;
import uk.cjack.planningpoker.adapter.PokerCardAdapter;
import uk.cjack.planningpoker.database.entities.Config;
import uk.cjack.planningpoker.enums.PokerScalesEnum;
import uk.cjack.planningpoker.view.ConfigViewModel;

public class MainActivity extends AppCompatActivity implements PokerCardAdapter.ItemClickListener {

    public PokerCardAdapter mPokerCardAdapter;
    Context mContext;
    public List<String> mData;
    private ConfigViewModel mConfigViewModel;
    private ColorPickerView mPicker;
    private List<Config> mAllConfig;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        mContext = this;


        mConfigViewModel = ViewModelProviders.of( this ).get( ConfigViewModel.class );
        mConfigViewModel.getAllConfig().observe( this,
                babyList -> {
                    mAllConfig = babyList;
                    mPokerCardAdapter.setCardColor( getConfig( "color" ) );
                } );

        final PokerScalesEnum fibonacciScale = PokerScalesEnum.FIBONACCI;

        // data to populate the RecyclerView with
        mData = fibonacciScale.getPokerScale();

        // set up the RecyclerView
        mRecyclerView = findViewById( R.id.rv_chooseCard );
        int numberOfColumns = 3;
        mRecyclerView.setLayoutManager( new GridLayoutManager( mContext, numberOfColumns ) );

        mPokerCardAdapter = new PokerCardAdapter( mContext, mData );
        mPokerCardAdapter.setClickListener( this );
        mRecyclerView.setAdapter( mPokerCardAdapter );


        ////////

        Button selectCardSet = findViewById( R.id.btn_selectCardSet );
        selectCardSet.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( final View v ) {
                selectCardSetDialog();
            }
        } );

        Button selectCardColour = findViewById( R.id.btn_selectCardColour );
        selectCardColour.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( final View v ) {
                selectColourPickerDialog();
            }
        } );

    }

    public void setCards() {
        mPokerCardAdapter = new PokerCardAdapter( mContext, mData, getConfig( "color" ) );
        mPokerCardAdapter.setClickListener( this );
        mRecyclerView.setAdapter( mPokerCardAdapter );
    }

    @Override
    public void onItemClick( View view, int position ) {
        Log.i( "TAG", "You clicked number " + mPokerCardAdapter.getItem( position ) + ", which is" +
                " at cell " +
                "position " + position );
        final Intent intent = new Intent( MainActivity.this, CardActivity.class );
        intent.putExtra( CardActivity.SELECTED_CARD, mPokerCardAdapter.getItem( position ) );
        startActivity( intent );
    }


    private void selectCardSetDialog() {

        /*
         * Build the dialog
         */
        final AlertDialog dialog = new AlertDialog.Builder( this )
                .setView( R.layout.card_type )
                .setNegativeButton( getString( R.string.btn_cancel ), null )
                .create();
        dialog.show();


        List<String> cardList = new ArrayList<>();

        cardList.add( PokerScalesEnum.FIBONACCI.name() );
        cardList.add( PokerScalesEnum.TSHIRT.name() );

        RecyclerView recyclerView = dialog.findViewById( R.id.rv_cardSelection );
        CardSelectorAdapter cardSelector = new CardSelectorAdapter( this, dialog, cardList );
        recyclerView.setAdapter( cardSelector );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
    }


    private void selectColourPickerDialog() {

//        final ColorPickerView picker = new ColorPickerView(mContext);
        /*
         * Build the dialog
         */
        final AlertDialog dialog = new AlertDialog.Builder( this )
                .setView( R.layout.colour_picker )
                .setNegativeButton( getString( R.string.btn_cancel ), null )
                .setPositiveButton( getString( R.string.btn_select ),
                        new DialogInterface.OnClickListener() {
                            public void onClick( DialogInterface dialog, int id ) {
                                final int color = mPicker.getColor();
                                mConfigViewModel.insertOrUpdate( new Config( "color",
                                        String.valueOf( color ) ) );
                                setCards();
                            }
                        } )
                .create();

        dialog.show();

        final int startingColor;
        Config colorConfig = getConfig( "color" );

        if ( colorConfig != null ) {
            startingColor = Integer.parseInt( colorConfig.getConfigValue() );
        }
        else {
            startingColor = 0xffff0000;
        }
        mPicker = dialog.findViewById( R.id.colorPicker );
        mPicker.setColor( startingColor );


    }

    Config getConfig( final String key ) {
        if ( mAllConfig != null ) {
            for ( Config configItem : mAllConfig ) {
                if ( configItem.getConfigKey().equals( key ) ) {
                    return configItem;
                }
            }
        }
        return null;
    }
}
