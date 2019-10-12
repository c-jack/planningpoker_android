package uk.cjack.planningpoker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

import uk.cjack.planningpoker.adapter.PokerCardAdapter;
import uk.cjack.planningpoker.decoration.CardItemDecoration;
import uk.cjack.planningpoker.enums.PokerScalesEnum;

public class MainActivity extends AppCompatActivity implements PokerCardAdapter.ItemClickListener {

    PokerCardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PokerScalesEnum fibonacciScale = PokerScalesEnum.FIBONACCI;

        // data to populate the RecyclerView with
        final List<String> data = fibonacciScale.getPokerScale();

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.cardView);
        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing);
//        recyclerView.addItemDecoration(new CardItemDecoration(spacingInPixels));
        adapter = new PokerCardAdapter(this, data);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick( View view, int position) {
        Log.i("TAG", "You clicked number " + adapter.getItem(position) + ", which is at cell position " + position);
        final Intent intent = new Intent( MainActivity.this, CardActivity.class );
        intent.putExtra( CardActivity.SELECTED_CARD, adapter.getItem(position) );
        startActivity( intent );
    }
}
