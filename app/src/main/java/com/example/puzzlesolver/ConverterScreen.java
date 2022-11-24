package com.example.puzzlesolver;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.maltaisn.icondialog.IconDialog;
import com.maltaisn.icondialog.IconDialogSettings;
import com.maltaisn.icondialog.data.Icon;
import com.maltaisn.icondialog.pack.IconPack;

import java.util.List;

public class ConverterScreen extends AppCompatActivity implements IconDialog.Callback {

    private static final String ICON_DIALOG_TAG = "icon-dialog";

    private TextView puzzle;
    private ImageView lock;
    private Drawable icon_tray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter_layout);

        //Get lock view component
        lock = (ImageView) findViewById(R.id.puzzle_input);

        // If dialog is already added to fragment manager, get it. If not, create a new instance.
        IconDialog dialog = (IconDialog) getSupportFragmentManager().findFragmentByTag(ICON_DIALOG_TAG);
        IconDialog iconDialog = dialog != null ? dialog
                : IconDialog.newInstance(new IconDialogSettings.Builder().build());

        Button btn = findViewById(R.id.open_btn);
        btn.setOnClickListener(v -> {
            // Open icon dialog
            iconDialog.show(getSupportFragmentManager(), ICON_DIALOG_TAG);
        });
    }

    @Nullable
    @Override
    public IconPack getIconDialogIconPack() {
        return ((ConverterState) getApplication()).getIconPack();
    }

    @Override
    public void onIconDialogIconsSelected(@NonNull IconDialog dialog, @NonNull List<Icon> icons) {
        StringBuilder sb = new StringBuilder();
        for (Icon icon : icons) {
            sb.append(icon.getId());
            sb.append(" ");
        }

        //Drawable image will store the current image and the new image to be appended
        LayerDrawable appended_img = new LayerDrawable(new Drawable[] { lock.getDrawable(), icons.get(0).getDrawable() });

        //Append the image to the current image
        //This code must be placed inside this block to ensure compatibility, may be changed later
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            appended_img.setLayerInsetRight(0, icons.get(0).getDrawable().getIntrinsicWidth() * 2);
            appended_img.setLayerGravity(1, Gravity.CENTER_HORIZONTAL);
        }

        //Set the image
        lock.setImageDrawable(appended_img);

        //Check if values have been placed yet

        //sb.delete(sb.length() - 2, sb.length());
        //Toast.makeText(this, "Icons selected: " + sb, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onIconDialogCancelled() {}
}
