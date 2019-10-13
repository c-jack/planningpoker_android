package uk.cjack.planningpoker.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.cjack.planningpoker.R;
import uk.cjack.planningpoker.database.entities.Config;
import uk.cjack.planningpoker.util.UnicodeEmojis;

public class PokerCardAdapter extends RecyclerView.Adapter<PokerCardAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private int mCardColor;
    private Context mContext;

    public PokerCardAdapter( Context context, List<String> data ) {
        this.mInflater = LayoutInflater.from( context );
        this.mData = data;
        this.mContext = context;
    }

    public PokerCardAdapter( Context context, List<String> data, final Config color ) {
        this.mInflater = LayoutInflater.from( context );
        this.mData = data;
        this.mContext = context;
        setCardColor( color );
    }

    public void setCardColor( final Config color )
    {
        if( color != null ) {
            this.mCardColor = Integer.parseInt( color.getConfigValue() );
            notifyDataSetChanged();
        }
    }

    void notify( final List<String> dataList ) {
        mData = dataList;
        notifyDataSetChanged();
    }


    // inflates the cell layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        View view = mInflater.inflate( R.layout.pokercardview_item, parent, false );
        return new ViewHolder( view );
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder( @NonNull ViewHolder holder, int position ) {
        String cardValue = mData.get( position );
        if( cardValue !=null && cardValue.matches( "(:[a-zA-Z_]{3,}:)" ))
        {
            cardValue = UnicodeEmojis.checkAndReturnEmoji( cardValue );
        }
        holder.myTextView.setText( cardValue );
    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder( View itemView ) {
            super( itemView );
            myTextView = itemView.findViewById( R.id.tv_card_select_item );

            Drawable unwrappedDrawable = AppCompatResources.getDrawable(mContext, R.drawable.card_item);
            Drawable wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable);
            DrawableCompat.setTint(wrappedDrawable, mCardColor);
//            myTextView.setBackgroundColor( mCardColor );
            itemView.setOnClickListener( this );
        }

        @Override
        public void onClick( View view ) {

            if ( mClickListener != null ) {
                mClickListener.onItemClick( view, getAdapterPosition() );
            }
        }
    }

    // convenience method for getting data at click position
    public String getItem( int id ) {
        return mData.get( id );
    }

    // allows clicks events to be caught
    public void setClickListener( ItemClickListener itemClickListener ) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick( View view, int position );
    }
}
