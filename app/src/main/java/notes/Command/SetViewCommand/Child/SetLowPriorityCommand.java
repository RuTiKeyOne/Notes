package notes.Command.SetViewCommand.Child;

import androidx.databinding.ViewDataBinding;

import com.notes.R;
import com.notes.databinding.ItemNotesBinding;

import notes.Command.SetViewCommand.Parent.BaseSetViewCommand;

public class SetLowPriorityCommand extends BaseSetViewCommand {
    @Override
    public void setViewCommand(ViewDataBinding dataBinding) {
        if(dataBinding instanceof ItemNotesBinding){
            ItemNotesBinding notesBinding = (ItemNotesBinding) dataBinding;
            notesBinding.notesPriority.setBackgroundResource(R.drawable.green_shape);

        }
    }
}
