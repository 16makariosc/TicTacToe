package com.cis195calculator.maki.tictactoe;

import android.content.ComponentName;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getTargetContext;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by tsartang on 10/23/2017.
 */

@RunWith(AndroidJUnit4.class)
public class TTTTest {

    @Rule
    public IntentsTestRule mActivityRule = new IntentsTestRule<>(MainActivity.class);

    @Test
    public void backToStartMenu(){
        onView(withId(R.id.leaderboard)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), LBActivity.class)));
        pressBack();
        intended(hasComponent(new ComponentName(getTargetContext(), MainActivity.class)));
    }

    @Test
    public void testLeaderboardBackButtonOverride(){
        onView(withId(R.id.start_game)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), GameActivity.class)));

        onView(withId(R.id.i11)).perform(click());
        onView(withId(R.id.i12)).perform(click());
        onView(withId(R.id.i21)).perform(click());
        onView(withId(R.id.i22)).perform(click());
        onView(withId(R.id.i31)).perform(click());

        onView(withText("Player 1 Won!")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Player 2 Lost!")).perform(click());

        intended(hasComponent(new ComponentName(getTargetContext(), LBActivity.class)));
        pressBack();
        intended(hasComponent(new ComponentName(getTargetContext(), MainActivity.class)));
    }

    @Test
    public void testPlayer1WinNewNames(){
        onView(withId(R.id.start_game)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), GameActivity.class)));

        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 1")))).perform(replaceText("Maki"));
        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 2")))).perform(replaceText("Beri"));

        onView(withId(R.id.i11)).perform(click());
        onView(withId(R.id.i12)).perform(click());
        onView(withId(R.id.i21)).perform(click());
        onView(withId(R.id.i22)).perform(click());
        onView(withId(R.id.i31)).perform(click());

        onView(withText("Maki Won!")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Beri Lost!")).perform(click());

        intended(hasComponent(new ComponentName(getTargetContext(), LBActivity.class)));
        pressBack();
        intended(hasComponent(new ComponentName(getTargetContext(), MainActivity.class)));
    }

    @Test
    public void testPlayer2WinNewNames(){
        onView(withId(R.id.start_game)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), GameActivity.class)));

        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 1")))).perform(replaceText("Maki"));
        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 2")))).perform(replaceText("Beri"));

        onView(withId(R.id.i11)).perform(click());
        onView(withId(R.id.i12)).perform(click());
        onView(withId(R.id.i21)).perform(click());
        onView(withId(R.id.i22)).perform(click());
        onView(withId(R.id.i33)).perform(click());
        onView(withId(R.id.i32)).perform(click());

        onView(withText("Beri Won!")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Maki Lost!")).perform(click());

        intended(hasComponent(new ComponentName(getTargetContext(), LBActivity.class)));
        pressBack();
        intended(hasComponent(new ComponentName(getTargetContext(), MainActivity.class)));
    }

    @Test
    public void testPlayer1WinsGameLastMove(){
        onView(withId(R.id.start_game)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), GameActivity.class)));

        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 1")))).perform(replaceText("Maki"));
        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 2")))).perform(replaceText("Beri"));

        onView(withId(R.id.i11)).perform(click());
        onView(withId(R.id.i12)).perform(click());
        onView(withId(R.id.i21)).perform(click());
        onView(withId(R.id.i22)).perform(click());
        onView(withId(R.id.i13)).perform(click());
        onView(withId(R.id.i23)).perform(click());
        onView(withId(R.id.i32)).perform(click());
        onView(withId(R.id.i33)).perform(click());
        onView(withId(R.id.i31)).perform(click());

        onView(withText("Maki Won!")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Beri Lost!")).perform(click());

        intended(hasComponent(new ComponentName(getTargetContext(), LBActivity.class)));
        pressBack();
        intended(hasComponent(new ComponentName(getTargetContext(), MainActivity.class)));
    }

    @Test
    public void testTie(){
        onView(withId(R.id.start_game)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), GameActivity.class)));

        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 1")))).perform(replaceText("Maki"));
        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 2")))).perform(replaceText("Beri"));

        onView(withId(R.id.i11)).perform(click());
        onView(withId(R.id.i12)).perform(click());
        onView(withId(R.id.i21)).perform(click());
        onView(withId(R.id.i22)).perform(click());
        onView(withId(R.id.i13)).perform(click());
        onView(withId(R.id.i23)).perform(click());
        onView(withId(R.id.i13)).perform(click());
        onView(withId(R.id.i33)).perform(click());
        onView(withId(R.id.i31)).perform(click());
        onView(withId(R.id.i32)).perform(click());


        onView(withText("You both tied!")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("How does one win at tictactoe anyways?")).perform(click());

        intended(hasComponent(new ComponentName(getTargetContext(), LBActivity.class)));
        pressBack();
        intended(hasComponent(new ComponentName(getTargetContext(), MainActivity.class)));
    }

    @Test
    public void testDialogPushBackButton(){
        onView(withId(R.id.start_game)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), GameActivity.class)));

        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 1")))).perform(replaceText("Maki"));
        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 2")))).perform(replaceText("Beri"));

        onView(withId(R.id.i11)).perform(click());
        onView(withId(R.id.i12)).perform(click());
        onView(withId(R.id.i21)).perform(click());
        onView(withId(R.id.i22)).perform(click());
        onView(withId(R.id.i33)).perform(click());
        onView(withId(R.id.i32)).perform(click());

        onView(withText("Beri Won!")).inRoot(isDialog()).check(matches(isDisplayed()));
        pressBack();

        intended(hasComponent(new ComponentName(getTargetContext(), LBActivity.class)));
        pressBack();
        intended(hasComponent(new ComponentName(getTargetContext(), MainActivity.class)));
    }

    @Test
    public void testDeleteLeaderboardData(){
        onView(withId(R.id.leaderboard)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), LBActivity.class)));
        onView(withId(R.id.deleteData)).perform(click());
        onView(withText("Are you sure you want to erase all data? This cannot be undone.")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Yes")).perform(click());
        pressBack();

        onView(withId(R.id.start_game)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), GameActivity.class)));

        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 1")))).perform(replaceText("Maki"));
        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 2")))).perform(replaceText("Beri"));

        onView(withId(R.id.i11)).perform(click());
        onView(withId(R.id.i12)).perform(click());
        onView(withId(R.id.i21)).perform(click());
        onView(withId(R.id.i22)).perform(click());
        onView(withId(R.id.i13)).perform(click());
        onView(withId(R.id.i23)).perform(click());
        onView(withId(R.id.i13)).perform(click());
        onView(withId(R.id.i33)).perform(click());
        onView(withId(R.id.i31)).perform(click());
        onView(withId(R.id.i32)).perform(click());


        onView(withText("You both tied!")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("How does one win at tictactoe anyways?")).perform(click());

        onView(withId(R.id.deleteData)).perform(click());
        onView(withText("Are you sure you want to erase all data? This cannot be undone.")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Yes")).perform(click());
        onView(withId(R.id.lblist)).check(matches(not(hasDescendant(withText("should not exist")))));
    }

    @Test
    public void testDeleteLeaderboardDataNoButton(){
        onView(withId(R.id.leaderboard)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), LBActivity.class)));
        onView(withId(R.id.deleteData)).perform(click());
        onView(withText("Are you sure you want to erase all data? This cannot be undone.")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Yes")).perform(click());
        pressBack();

        onView(withId(R.id.start_game)).perform(click());
        intended(hasComponent(new ComponentName(getTargetContext(), GameActivity.class)));

        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 1")))).perform(replaceText("Maki"));
        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 2")))).perform(replaceText("Beri"));

        onView(withId(R.id.i11)).perform(click());
        onView(withId(R.id.i12)).perform(click());
        onView(withId(R.id.i21)).perform(click());
        onView(withId(R.id.i22)).perform(click());
        onView(withId(R.id.i13)).perform(click());
        onView(withId(R.id.i23)).perform(click());
        onView(withId(R.id.i13)).perform(click());
        onView(withId(R.id.i33)).perform(click());
        onView(withId(R.id.i31)).perform(click());
        onView(withId(R.id.i32)).perform(click());


        onView(withText("You both tied!")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("How does one win at tictactoe anyways?")).perform(click());

        onView(withId(R.id.deleteData)).perform(click());

        onView(withText("Are you sure you want to erase all data? This cannot be undone.")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("No")).perform(click());
        onView(withText("Maki W:0 L:0 T:1")).check(matches(isDisplayed()));
        onView(withText("Beri W:0 L:0 T:1")).check(matches(isDisplayed()));
    }

    @Test
    public void testTieIncrement(){

        onView(withId(R.id.leaderboard)).perform(click());
        onView(withId(R.id.deleteData)).perform(click());
        onView(withText("Are you sure you want to erase all data? This cannot be undone.")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Yes")).perform(click());
        onView(withId(R.id.lblist)).check(matches(not(hasDescendant(withText("should not exist")))));
        pressBack();

        onView(withId(R.id.start_game)).perform(click());

        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 1")))).perform(replaceText("Maki"));
        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 2")))).perform(replaceText("Beri"));

        onView(withId(R.id.i11)).perform(click());
        onView(withId(R.id.i12)).perform(click());
        onView(withId(R.id.i21)).perform(click());
        onView(withId(R.id.i22)).perform(click());
        onView(withId(R.id.i13)).perform(click());
        onView(withId(R.id.i23)).perform(click());
        onView(withId(R.id.i13)).perform(click());
        onView(withId(R.id.i33)).perform(click());
        onView(withId(R.id.i31)).perform(click());
        onView(withId(R.id.i32)).perform(click());


        onView(withText("You both tied!")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("How does one win at tictactoe anyways?")).perform(click());

        onView(withText("Maki W:0 L:0 T:1")).check(matches(isDisplayed()));
        onView(withText("Beri W:0 L:0 T:1")).check(matches(isDisplayed()));

        pressBack();
        onView(withId(R.id.start_game)).perform(click());

        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 1")))).perform(replaceText("Maki"));
        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 2")))).perform(replaceText("Beri"));

        onView(withId(R.id.i11)).perform(click());
        onView(withId(R.id.i12)).perform(click());
        onView(withId(R.id.i21)).perform(click());
        onView(withId(R.id.i22)).perform(click());
        onView(withId(R.id.i13)).perform(click());
        onView(withId(R.id.i23)).perform(click());
        onView(withId(R.id.i13)).perform(click());
        onView(withId(R.id.i33)).perform(click());
        onView(withId(R.id.i31)).perform(click());
        onView(withId(R.id.i32)).perform(click());

        onView(withText("You both tied!")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("How does one win at tictactoe anyways?")).perform(click());

        onView(withText("Maki W:0 L:0 T:2")).check(matches(isDisplayed()));
        onView(withText("Beri W:0 L:0 T:2")).check(matches(isDisplayed()));
    }

    @Test
    public void testWinIncrement(){

        onView(withId(R.id.leaderboard)).perform(click());
        onView(withId(R.id.deleteData)).perform(click());
        onView(withText("Are you sure you want to erase all data? This cannot be undone.")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Yes")).perform(click());
        onView(withId(R.id.lblist)).check(matches(not(hasDescendant(withText("should not exist")))));
        pressBack();

        onView(withId(R.id.start_game)).perform(click());

        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 1")))).perform(replaceText("Maki"));
        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 2")))).perform(replaceText("Beri"));

        onView(withId(R.id.i11)).perform(click());
        onView(withId(R.id.i12)).perform(click());
        onView(withId(R.id.i21)).perform(click());
        onView(withId(R.id.i22)).perform(click());
        onView(withId(R.id.i31)).perform(click());


        onView(withText("Maki Won!")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Beri Lost!")).perform(click());

        onView(withText("Maki W:1 L:0 T:0")).check(matches(isDisplayed()));
        onView(withText("Beri W:0 L:1 T:0")).check(matches(isDisplayed()));
        pressBack();

        onView(withId(R.id.start_game)).perform(click());

        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 1")))).perform(replaceText("Maki"));
        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 2")))).perform(replaceText("Beri"));

        onView(withId(R.id.i11)).perform(click());
        onView(withId(R.id.i12)).perform(click());
        onView(withId(R.id.i21)).perform(click());
        onView(withId(R.id.i22)).perform(click());
        onView(withId(R.id.i33)).perform(click());
        onView(withId(R.id.i32)).perform(click());

        onView(withText("Beri Won!")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Maki Lost!")).perform(click());

        onView(withText("Maki W:1 L:1 T:0")).check(matches(isDisplayed()));
        onView(withText("Beri W:1 L:1 T:0")).check(matches(isDisplayed()));
        pressBack();

        onView(withId(R.id.start_game)).perform(click());

        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 1")))).perform(replaceText("Maki"));
        onView(allOf(withClassName(endsWith("EditText")), withHint(is("Player 2")))).perform(replaceText("Beri"));

        onView(withId(R.id.i11)).perform(click());
        onView(withId(R.id.i12)).perform(click());
        onView(withId(R.id.i21)).perform(click());
        onView(withId(R.id.i22)).perform(click());
        onView(withId(R.id.i33)).perform(click());
        onView(withId(R.id.i32)).perform(click());

        onView(withText("Beri Won!")).inRoot(isDialog()).check(matches(isDisplayed()));
        onView(withText("Maki Lost!")).perform(click());

        onView(withText("Maki W:1 L:2 T:0")).check(matches(isDisplayed()));
        onView(withText("Beri W:2 L:1 T:0")).check(matches(isDisplayed()));

    }



}








































