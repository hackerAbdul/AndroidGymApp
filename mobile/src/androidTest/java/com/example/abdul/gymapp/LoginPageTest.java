package com.example.abdul.gymapp;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static org.junit.Assert.*;

public class LoginPageTest {

    @Rule
    public ActivityTestRule<LoginPage> mActivityTestRule = new ActivityTestRule<LoginPage>(LoginPage.class);

    private LoginPage mLoginPage = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(RegisterPage.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {

        mLoginPage = mActivityTestRule.getActivity();

    }

    @Test
    public void testLaunchOfSecondActivityOnButtonClick(){
        assertNotNull(mLoginPage.findViewById(R.id.register));

        onView(withID(R.id.register)).perform(click());

        Activity RegisterPage = getInstrumentation().waitForMonitorWithTimeout(monitor,5000);

        assertNotNull(RegisterPage);

        RegisterPage.finish();
    }


    @After
    public void tearDown() throws Exception {
    }
}