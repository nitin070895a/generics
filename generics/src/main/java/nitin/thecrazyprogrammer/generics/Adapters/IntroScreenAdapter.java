package nitin.thecrazyprogrammer.generics.Adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import nitin.thecrazyprogrammer.generics.Models.IntroScreenStyler.IntroScreenType;
import nitin.thecrazyprogrammer.generics.Models.IntroScreenData;
import nitin.thecrazyprogrammer.generics.R;

/**
 * Created by Nitin Khurana on 2/4/2018.
 */
public class IntroScreenAdapter extends PagerAdapter{

    private Context context;
    private IntroScreenType introScreenType;
    private ArrayList<IntroScreenData> introScreenData = new ArrayList<>();
    LayoutInflater inflater;

    public IntroScreenAdapter(Context context, ArrayList<IntroScreenData> introScreenData, IntroScreenType introScreenType){

        this.context = context;
        this.introScreenType = introScreenType;
        this.introScreenData = introScreenData;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.item_intro_screen, container, false);
        IntroScreenViewHolder holder = new IntroScreenViewHolder(layout);

        switch (introScreenType){

            case INTRO_SCREEN_WITH_TITLE_AND_IMAGE:
                holder.title.setText(introScreenData.get(position).getTitle());
                break;
            case INTRO_SCREEN_WITH_DESC_AND_IMAGE:
                holder.desc.setText(introScreenData.get(position).getDesc());
                break;
            case INTRO_SCREEN_WITH_TITLE_IMAGE_AND_DESC:
                holder.title.setText(introScreenData.get(position).getTitle());
                holder.desc.setText(introScreenData.get(position).getDesc());
                break;
        }

        layout.setBackgroundColor(introScreenData.get(position).getColor());
        holder.image.setImageResource(introScreenData.get(position).getIcon());
        container.addView(layout);

        return layout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return introScreenData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private class IntroScreenViewHolder{

        public TextView title;
        public ImageView image;
        public TextView desc;

        public IntroScreenViewHolder(ViewGroup view){

            title = view.findViewById(R.id.title);
            image = view.findViewById(R.id.image);
            desc = view.findViewById(R.id.desc);
        }
    }
}
