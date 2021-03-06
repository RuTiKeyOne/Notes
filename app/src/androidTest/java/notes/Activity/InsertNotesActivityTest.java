package notes.Activity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static org.junit.Assert.*;

import android.view.View;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.notes.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class InsertNotesActivityTest {

    @Rule
    public ActivityTestRule<InsertNotesActivity> insertTestRule = new ActivityTestRule<>(InsertNotesActivity.class);

    @Test
    public void initializationComponentsTest(){
        insertTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                insertTestRule.getActivity().initializationComponents();
                assertNotNull(insertTestRule.getActivity().getNotesViewModel());
                assertNotNull(insertTestRule.getActivity().getPriorityCommand());
                assertNotEquals(0, insertTestRule.getActivity().getPriorityLevel());
                assertNotNull(insertTestRule.getActivity().getNullActivity());
            }
        });
    }

    @Test
    public void initializationComponentsViewTest(){
        insertTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                insertTestRule.getActivity().initializationViewComponents();
                assertNotNull(insertTestRule.getActivity().getInsertBinding());
            }
        });
    }

    @Test
    public void onBackClickTest(){
        assertNotNull(insertTestRule.getActivity().getInsertBinding());
        onView(ViewMatchers.withId(R.id.addImageBack)).perform(click());
    }

    @Test
    public void onRedPriorityClickTest(){
        assertNotNull(insertTestRule.getActivity().getInsertBinding());
        onView(ViewMatchers.withId(R.id.redPriority)).perform(click());
        assertNotNull(insertTestRule.getActivity().getPriorityCommand());
        assertEquals(1, insertTestRule.getActivity().getPriorityLevel());
    }

    @Test
    public void onYellowPriorityClickTest(){
        assertNotNull(insertTestRule.getActivity().getInsertBinding());
        onView(ViewMatchers.withId(R.id.yellowPriority)).perform(click());
        assertNotNull(insertTestRule.getActivity().getPriorityCommand());
        assertEquals(2, insertTestRule.getActivity().getPriorityLevel());
    }

    @Test
    public void onGreenPriorityClickTest(){
        assertNotNull(insertTestRule.getActivity().getInsertBinding());
        onView(ViewMatchers.withId(R.id.greenPriority)).perform(click());
        assertNotNull(insertTestRule.getActivity().getPriorityCommand());
        assertEquals(3, insertTestRule.getActivity().getPriorityLevel());
    }

    public void addNewNoteIsDataBaseTest(){
        assertNotNull(insertTestRule.getActivity().getInsertBinding());
        assertNotNull(insertTestRule.getActivity().getNullActivity());
    }

    @Test
    public void isTitleNotNullTest(){
        insertTestRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String oldTitle =  insertTestRule.getActivity().getInsertBinding().notesTitle.getText().toString();
                insertTestRule.getActivity().getInsertBinding().notesTitle.setText("Hello");
                assertEquals(true, insertTestRule.getActivity().isTitleNotEmpty());
                insertTestRule.getActivity().getInsertBinding().notesTitle.setText(oldTitle);
            }
        });
    }

    @Test
    public void getNotesDataTest(){
        assertNotNull(insertTestRule.getActivity().getNotesData());
    }

    @Test
    public void getDateTest(){
        assertNotNull(insertTestRule.getActivity().getDate());
    }

    @Test
    public void checkInsertMainLayoutIsDisplayed(){
        onView(ViewMatchers.withId(R.id.insertMainLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void checkAddImageBackIsDisplayed(){
        onView(ViewMatchers.withId(R.id.addImageBack)).check(matches(isDisplayed()));
    }

    @Test
    public void checkAddNoteTextVieIsDisplayed(){
        onView(ViewMatchers.withId(R.id.addNoteTextView)).check(matches(isDisplayed()));
    }

    @Test
    public void checkInputLinearLayoutIsDisplayed(){
        onView(ViewMatchers.withId(R.id.inputLinearLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void checkNotesPriorityIsDisplayed(){
        onView(ViewMatchers.withId(R.id.notesPriority)).check(matches(isDisplayed()));
    }

    @Test
    public void checkPriorityTextIsDisplayed(){
        onView(ViewMatchers.withId(R.id.priorityText)).check(matches(isDisplayed()));
    }

    @Test
    public void checkRedPriorityIsDisplayed(){
        onView(ViewMatchers.withId(R.id.redPriority)).check(matches(isDisplayed()));
    }

    @Test
    public void checkYellowPriorityIsDisplayed(){
        onView(ViewMatchers.withId(R.id.yellowPriority)).check(matches(isDisplayed()));
    }

    @Test
    public void checkGreenPriorityIsDisplayed(){
        onView(ViewMatchers.withId(R.id.greenPriority)).check(matches(isDisplayed()));
    }

    @Test
    public void checkNotesScrollViewIsDisplayed(){
        onView(ViewMatchers.withId(R.id.notesScrollView)).check(matches(isDisplayed()));
    }

    @Test
    public void checkNotesTitleIsDisplayed(){
        onView(ViewMatchers.withId(R.id.notesData)).check(matches(isDisplayed()));
    }

    @Test
    public void checkNotesDataIsDisplayed(){
        onView(ViewMatchers.withId(R.id.notesData)).check(matches(isDisplayed()));
    }

    @Test
    public void checkDoneNotesButtonIsDisplayed(){
        onView(ViewMatchers.withId(R.id.doneNotesButton)).check(matches(isDisplayed()));
    }

    @Test
    public void checkDoneNotesButtonIsClickable(){
        onView(ViewMatchers.withId(R.id.doneNotesButton)).check(matches(isClickable()));
    }


}