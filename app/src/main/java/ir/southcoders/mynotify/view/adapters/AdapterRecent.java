package ir.southcoders.mynotify.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ir.southcoders.mynotify.R;
import ir.southcoders.mynotify.model.NotificationModel;

/**
 * Created by Farzad on 2/17/2018.
 */

public class AdapterRecent extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_ITEM = 1;
    private final Context context;
    private final List arrayList;
    private NotificationModel model;

    public AdapterRecent(Context context, List arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
            return new ViewHolderItem(v);
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        if (viewHolder instanceof AdapterRecent.ViewHolderItem) {
            model = (NotificationModel) arrayList.get(position);
            ((ViewHolderItem) viewHolder).txtTitle.setText(model.getText());
        }
    }


    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolderItem extends RecyclerView.ViewHolder {
        TextView txtTitle;

        public ViewHolderItem(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
        }
    }

}
