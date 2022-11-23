package com.example.puzzlesolver;

import android.os.Bundle;
import android.widget.Button;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter_layout);

        //Get puzzle view component
        puzzle = (TextView) findViewById(R.id.puzzle_input);

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
            sb.append(", ");
        }

        //Check if values have been placed yet
        String prev = puzzle.getText().toString();
        if (prev.equals("____ ____ ____ ____ ____"))
            puzzle.setText(sb + " ");
        else
            puzzle.setText(prev + sb + " ");

        //sb.delete(sb.length() - 2, sb.length());
        //Toast.makeText(this, "Icons selected: " + sb, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onIconDialogCancelled() {}
}
