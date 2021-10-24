package notes.Activity;

import static notes.Utilities.TempDataViewModel.NOTE_INTENT_KEY;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import notes.Adapter.Child.NotesAdapter;
import notes.Intefaces.EditNoteListener;
import notes.Intefaces.OpenDetailBottomSheetListener;
import notes.Model.Notes;
import notes.ViewModel.NotesViewModel;

import com.notes.R;
import com.notes.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements EditNoteListener, OpenDetailBottomSheetListener {

    private ActivityMainBinding mainBinding;
    private NotesViewModel notesViewModel;
    private NotesAdapter notesAdapter;
    private DetailsBottomSheetActivity detailsActivity;
    private List<Notes> notesData = new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializationComponents();
        initializationViewComponents();
        getAllNotes();
        addNewNoteClickBehaviour();
    }

    @Override
    protected void onStop() {
        super.onStop();
        notesData.clear();
    }

    private void initializationViewComponents() {
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.notesRecycleView.setAdapter(notesAdapter);
        mainBinding.notesRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void initializationComponents() {
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        notesAdapter = new NotesAdapter(notesData, this);
        detailsActivity = new DetailsBottomSheetActivity(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getAllNotes() {
        notesViewModel.getAllNotes().observe(this, notes -> {
            Log.d("log", "notes: " + notes.size());
            notesData.addAll(notes);
            notesAdapter.notifyDataSetChanged();
        });
    }

    private void addNewNoteClickBehaviour() {
        mainBinding.newNotesButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InsertNotesActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onNoteEdit(Notes note) {
        Intent updateIntent = new Intent(getApplicationContext(), UpdateNotesActivity.class);
        updateIntent.putExtra(NOTE_INTENT_KEY, note);
        startActivity(updateIntent);
    }

    @Override
    public void onOpenSheet(Notes note) {
        if(detailsActivity != null){
            detailsActivity.onStartBottomSheetActivity(note);
        }
    }
}