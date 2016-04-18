package in.trydevs.myschool.Adapters;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import in.trydevs.myschool.DataClasses.Subscription;
import in.trydevs.myschool.Listners.ListItemClickListener;
import in.trydevs.myschool.R;

/**
 * Created by root on 28/9/15.
 */
public class MyAdapterSubscription extends RecyclerView.Adapter<MyAdapterSubscription.MyHolder> {

    AppCompatActivity context;
    List<Subscription> data;
    LayoutInflater inflater;
    ListItemClickListener listItemClickListener;

    public MyAdapterSubscription(AppCompatActivity context, List<Subscription> data, ListItemClickListener listItemClickListener) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
        this.listItemClickListener = listItemClickListener;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return (new MyHolder(inflater.inflate(R.layout.custom_row_subscription, null)));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        Subscription subscription = data.get(position);
        // Setting the School Name
        holder.name.setText(subscription.getName());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    protected class MyHolder extends RecyclerView.ViewHolder {

        TextView name;

        public MyHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.textViewName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listItemClickListener.OnListItemClick(data.get(getAdapterPosition()));
                }
            });
        }
    }
}
