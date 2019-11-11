package com.example.clinicapp;

import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;

public class SignUpActivityTest {
    @Rule
    public ActivityTestRule<SignupActivity> mActivityTestRule = new ActivityTestRule<>(SignupActivity.class);
    private SignupActivity mActivity = null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void checkFirstName() throws Exception {
        text = mActivity.findViewById(R.id.firstName);
        text.setText("firstName1");
        String firstName = text.getText().toString();
        assertNotEquals("firstName", firstName);
    }

    @Test
    @UiThreadTest
    public void checkLastName() throws Exception {
        text = mActivity.findViewById(R.id.lastName);
        text.setText("lastName1");
        String lastName = text.getText().toString();
        assertNotEquals("lastName", lastName);
    }

    @Test
    @UiThreadTest
    public void checkEmail() throws Exception {
        text = mActivity.findViewById(R.id.emailText);
        text.setText("email1");
        String email = text.getText().toString();
        assertNotEquals("email", email);
    }

    @Test
    @UiThreadTest
    public void checkPassword() throws Exception {
        text = mActivity.findViewById(R.id.passwordText);
        text.setText("password1");
        String password = text.getText().toString();
        assertNotEquals("password", password);
    }

    @Test
    @UiThreadTest
    public void checkPhoneNumber() throws Exception {
        text = mActivity.findViewById(R.id.phoneNumber);
        text.setText("phoneNumber1");
        String phoneNumber = text.getText().toString();
        assertNotEquals("phoneNumber", phoneNumber);
    }
}