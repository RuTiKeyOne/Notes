package notes.Command.AdapterCommand.Child;

import static notes.Utilities.TempDataAdapter.HIGH_PRIORITY_KEY;
import static notes.Utilities.TempDataAdapter.LOW_PRIORITY_KEY;
import static notes.Utilities.TempDataAdapter.MEDIUM_PRIORITY_KEY;

import android.system.ErrnoException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import notes.Command.AdapterCommand.Parent.BaseAdapterCommand;
import notes.Command.SetViewCommand.Child.SetHighPriorityCommand;
import notes.Command.SetViewCommand.Child.SetLowPriorityCommand;
import notes.Command.SetViewCommand.Child.SetMediumPriorityCommand;
import notes.Command.SetViewCommand.Parent.BaseSetViewCommand;
import notes.Intefaces.NoteListener;
import notes.Model.Notes;

import com.notes.databinding.ItemNotesBinding;

public class NotesAdapterCommand extends BaseAdapterCommand<Notes> {

    private BaseSetViewCommand setViewCommand;

    public NotesAdapterCommand(NoteListener listener) {
        super(listener);
    }

    @Override
    public void bindItem(Notes data, ViewDataBinding dataBinding) {
        setDataNote(data, dataBinding);
        setPriorityView(data, dataBinding);
        setOnClickBehaviour(dataBinding, data);
    }

    private ItemNotesBinding getItemNotesBindingWithViewDataBinding(ViewDataBinding dataBinding) {
        if(dataBinding instanceof ItemNotesBinding) {
            return (ItemNotesBinding) dataBinding;
        }
        throw new NullPointerException("Data binding not instanceof ItemNotesBinding");
    }

    @Override
    public ViewDataBinding getItemContainerBinding(LayoutInflater layoutInflater, int idLayout, ViewGroup parent) {
        ItemNotesBinding notesBinding = DataBindingUtil.inflate(layoutInflater, idLayout, parent, false);
        return notesBinding;
    }

    private void setOnClickBehaviour(ViewDataBinding dataBinding, Notes note){
        getItemNotesBindingWithViewDataBinding(dataBinding).getRoot().setOnClickListener(v -> listener.onNoteClicked(note));
    }

    private void setDataNote(Notes data, ViewDataBinding dataBinding) {
        if (dataBinding instanceof ItemNotesBinding) {
            getItemNotesBindingWithViewDataBinding(dataBinding).setTitle(data.notesTitle);
            getItemNotesBindingWithViewDataBinding(dataBinding).setSubTitle(data.notesSubTitle);
            getItemNotesBindingWithViewDataBinding(dataBinding).setDate(data.notesDate);
        }
    }


    private void setPriorityView(Notes data, ViewDataBinding dataBinding) {
        ItemNotesBinding notesBinding = getItemNotesBindingWithViewDataBinding(dataBinding);
        if (data.notesPriority != null) {
            setPriorityViewCommandManagement(data.notesPriority, dataBinding);
        } else {
            setDefaultPriorityView(dataBinding);
        }
    }

    private void setPriorityViewCommandManagement(String priorityLevel, ViewDataBinding dataBinding) {
        switch (priorityLevel) {
            case HIGH_PRIORITY_KEY:
                setDefaultPriorityView(dataBinding);
                break;
            case MEDIUM_PRIORITY_KEY:
                setMediumPriorityView(dataBinding);
                break;
            case LOW_PRIORITY_KEY:
                setLowPriorityView(dataBinding);
                break;
        }
    }

    private void setDefaultPriorityView(ViewDataBinding dataBinding) {
        setViewCommand = new SetHighPriorityCommand();
        setViewCommand.setViewCommand(dataBinding);
    }

    private void setMediumPriorityView(ViewDataBinding dataBinding) {
        setViewCommand = new SetMediumPriorityCommand();
        setViewCommand.setViewCommand(dataBinding);
    }

    private void setLowPriorityView(ViewDataBinding dataBinding) {
        setViewCommand = new SetLowPriorityCommand();
        setViewCommand.setViewCommand(dataBinding);
    }


}
