package nitin.thecrazzyprogrammer.generics.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nitin.thecrazzyprogrammer.generics.R;

/**
 * Created by Nitin Khurana on 1/18/2018.
 *
 * A recycler view adapter to show a title and a description in a cardview
 */
public class CardViewRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private String titles[];
    private String licenses[];

    /**
     * Pass both parameters in same order
     * @param titles All the titles
     * @param licences All the sub titles or descriptions
     */
    public CardViewRecyclerAdapter(String[] titles, String licences[]){

        this.titles = titles;
        this.licenses = licences;
    }

    /**
     * ViewHolder for the license item
     */
    private class LicenseViewHolder extends RecyclerView.ViewHolder {

        TextView title,license;

        public LicenseViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            license = (TextView) view.findViewById(R.id.license);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_license, parent, false);
        return new LicenseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        LicenseViewHolder licenseViewHolder = (LicenseViewHolder) holder;

        licenseViewHolder.title.setText(titles[position]);
        licenseViewHolder.license.setText(licenses[position]);

    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}

