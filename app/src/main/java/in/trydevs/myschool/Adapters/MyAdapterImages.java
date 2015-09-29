package in.trydevs.myschool.Adapters;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import in.trydevs.myschool.DataClasses.Image;
import in.trydevs.myschool.R;


/**
 * Created by Sundareswaran on 26-08-2015.
 */
public class MyAdapterImages extends RecyclerView.Adapter<MyAdapterImages.MyHolder> {

    List<Image> data;
    FragmentActivity context;
    LayoutInflater inflater;

    public MyAdapterImages(FragmentActivity context, List<Image> images) {
        if (images.size() > 0)
            this.data = images;
        else
            this.data = Collections.emptyList();
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row_images, parent, false);
        return (new MyHolder(view));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Image image = data.get(position);
        if (!image.getLink().equalsIgnoreCase("")) {
            Glide.with(context)
                    .load(image.getLink())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(holder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    protected class MyHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
