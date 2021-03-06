package notes.Activity;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import notes.Adapter.Child.NotesAdapter;
import notes.Command.MainActivityCommand;
import notes.Model.Notes;
import notes.Utilities.TempDataActivity;
import notes.ViewModel.NotesViewModel;
import com.notes.R;
import com.notes.databinding.ActivityMainBinding;
import java.io.Serializable;
import java.util.*;

public class MainActivity extends TempDataActivity {

    private ActivityMainBinding mainBinding;
    private NotesViewModel notesViewModel;
    private NotesAdapter notesAdapter;
    private MainActivityCommand mainActivityCommand;
    private List<Notes> notesData = new ArrayList<>();

    public ActivityMainBinding getMainBinding() {
        return mainBinding;
    }

    public NotesViewModel getNotesViewModel() {
        return notesViewModel;
    }

    public NotesAdapter getNotesAdapter() {
        return notesAdapter;
    }

    public MainActivityCommand getMainActivityCommand() {
        return mainActivityCommand;
    }

    public List<Notes> getNotesData() {
        return notesData;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializationComponents();
        initializationViewComponents();
        getAllNotes(notesViewModel, notesData, notesAdapter);
        addNewNoteClickBehaviour();
        onClickSearch();
        onClickNoFilter();
        onClickHighToLowFilter();
        onClickLowToHighFilter();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getAllNotes(notesViewModel, notesData, notesAdapter);
        mainActivityCommand.onSetDefaultNoFilterView(mainBinding);
    }

    @Override
    protected void onStop() {
        super.onStop();
        notesData.clear();
    }

    public void initializationViewComponents() {
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainBinding.notesRecycleView.setAdapter(notesAdapter);
        mainBinding.notesRecycleView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        mainActivityCommand.setDefaultFilter(mainBinding);
    }

    public void initializationComponents() {
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        notesAdapter = new NotesAdapter(notesData, this);
        this.detailsActivity = new DetailsBottomSheetActivity(this);
        mainActivityCommand = new MainActivityCommand();
    }

    public void onClickSearch() {
        mainBinding.imageSearch.setOnClickListener(v -> {
            intentSearchActivity();
        });
    }

    public void intentSearchActivity() {
        Intent searchIntent = new Intent(getApplicationContext(), SearchActivity.class);
        searchIntent.putExtra(NOTES_DATA_KEY, (Serializable) notesData);
        startActivity(searchIntent);
    }

    public void onClickNoFilter() {
        mainBinding.noFilter.setOnClickListener(v -> {
            getAllNotes(notesViewModel, notesData, notesAdapter);
            mainActivityCommand.onSetDefaultNoFilterView(mainBinding);
        });
    }

    public void onClickHighToLowFilter() {
        mainBinding.highToLow.setOnClickListener(v -> {
            getHighToLowNotes(notesViewModel, notesData, notesAdapter);
            mainActivityCommand.onSetHighToLowFilterView(mainBinding);
        });
    }

    public void onClickLowToHighFilter() {
        mainBinding.lowToHigh.setOnClickListener(v -> {
            getLowToHighNotes(notesViewModel, notesData, notesAdapter);
            mainActivityCommand.onSetLowToHighFilterView(mainBinding);
        });
    }

    private void addNewNoteClickBehaviour() {
        mainBinding.newNotesButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InsertNotesActivity.class);
            startActivity(intent);
        });
    }


}