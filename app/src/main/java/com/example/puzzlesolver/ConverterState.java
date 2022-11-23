package com.example.puzzlesolver;

import android.app.Application;

import androidx.annotation.Nullable;

import com.maltaisn.icondialog.pack.IconPack;
import com.maltaisn.icondialog.pack.IconPackLoader;
import com.maltaisn.iconpack.fa.IconPackFa;

import java.util.Collections;
import java.util.Locale;

public class ConverterState extends Application {

    @Nullable
    private IconPack iconPack;

    @Override
    public void onCreate() {
        super.onCreate();

        // Load the icon pack on application start.
        loadIconPack();
    }

    @Nullable
    public IconPack getIconPack() {
        return iconPack != null ? iconPack : loadIconPack();
    }

    private IconPack loadIconPack() {
        // Create an icon pack loader with application context.
        IconPackLoader loader = new IconPackLoader(this);

        // Create an icon pack and load all drawables.
        //iconPack = IconPackFa.createFontAwesomeIconPack(loader);
        IconPack iconPack = loader.load(R.xml.icons, R.xml.tags, Collections.singletonList(Locale.ENGLISH), null);
        iconPack.loadDrawables(loader.getDrawableLoader());

        return iconPack;
    }
}
