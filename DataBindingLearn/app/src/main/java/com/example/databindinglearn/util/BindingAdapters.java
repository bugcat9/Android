package com.example.databindinglearn.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.databinding.BindingAdapter;

import com.example.databindinglearn.R;
import com.example.databindinglearn.data.Popularity;

public class BindingAdapters {
    @BindingAdapter("app:hideIfZero")
    public static void hideIfZero(View view, int number) {
        int visibility = number == 0 ? View.GONE : View.VISIBLE;
        view.setVisibility(visibility);
    }

    /**
     * Sets the value of the progress bar so that 5 likes will fill it up.
     * <p>
     * Showcases Binding Adapters with multiple attributes. Note that this adapter is called
     * whenever any of the attribute changes.
     */
    @BindingAdapter(value = {"app:progressScaled", "android:max"}, requireAll = true)
    public static void setProgress(ProgressBar progress, int likes, int max) {
        progress.setProgress((likes * (max / 5)));
    }

    private static int getAssociatedColor(Popularity popularity, Context context) {
        if (popularity.equals(Popularity.NORMAL))
            return context.getTheme().obtainStyledAttributes(new int[]{android.R.attr.colorForeground}).getColor(0, 0x000000);
        else if (popularity.equals(Popularity.NORMAL))
            return ContextCompat.getColor(context, R.color.popular);
        else
            return ContextCompat.getColor(context, R.color.star);
    }

    private static Drawable getDrawblePopularity(Popularity popularity, Context context) {
        if (popularity.equals(Popularity.NORMAL))
            return ContextCompat.getDrawable(context, R.drawable.ic_person_black_96dp);
        else if (popularity.equals(Popularity.NORMAL))
            return ContextCompat.getDrawable(context, R.drawable.ic_whatshot_black_96dp);
        else
            return ContextCompat.getDrawable(context, R.drawable.ic_whatshot_black_96dp);
    }

    /**
     * A Binding Adapter that is called whenever the value of the attribute `app:popularityIcon`
     * changes. Receives a popularity level that determines the icon and tint color to use.
     */
    @BindingAdapter("app:popularityIcon")
    public static void popularityIcon(ImageView view, Popularity popularity) {
        int color = getAssociatedColor(popularity, view.getContext());
        ImageViewCompat.setImageTintList(view, ColorStateList.valueOf(color));
        view.setImageDrawable(getDrawblePopularity(popularity, view.getContext()));
    }
}
