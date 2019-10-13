package uk.cjack.planningpoker.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

import uk.cjack.planningpoker.MainActivity;
import uk.cjack.planningpoker.R;
import uk.cjack.planningpoker.enums.PokerScalesEnum;

public class CardSelectorAdapter extends RecyclerView.Adapter<CardSelectorAdapter.ViewHolder> {

        private List<String> mData;
        private LayoutInflater mInflater;
        private Context mContext;
        private Dialog mDialog;

        // data is passed into the constructor
        public CardSelectorAdapter( Context context, final AlertDialog dialog, List<String> data ) {
            this.mInflater = LayoutInflater.from( context );
            this.mData = data;
            this.mContext = context;
            this.mDialog = dialog;
        }

        // inflates the cell layout from xml when needed
        @Override
        @NonNull
        public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.cardselector_item, parent, false);
            return new ViewHolder(view);
        }

        // binds the data to the TextView in each cell
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.myTextView.setText(mData.get( position ));
        }

        // total number of cells
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView myTextView;

            ViewHolder(View itemView) {
                super(itemView);
                myTextView = itemView.findViewById( R.id.tv_card_select_item );
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                String item = getItem( getAdapterPosition() );
                PokerScalesEnum scale = PokerScalesEnum.valueOf( item );

                if( mContext instanceof MainActivity )
                {
                    ( ( MainActivity ) mContext ).mPokerCardAdapter.notify( scale.getPokerScale() );
                    mDialog.dismiss();
                }
//                Toast.makeText( mContext, "test", Toast.LENGTH_SHORT ).show();
            }
        }

        // convenience method for getting data at click position
        private String getItem( int id ) {
            return mData.get( id );
        }

    }
