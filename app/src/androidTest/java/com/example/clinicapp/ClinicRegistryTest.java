package com.example.clinicapp;

import android.widget.TextView;

import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertNotEquals;

public class ClinicRegistryTest {
    @Rule
    public ActivityTestRule<ClinicRegistry> mActivityTestRule = new ActivityTestRule<>(ClinicRegistry.class);
    private ClinicRegistry mActivity = null;
    private TextView text;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    @UiThreadTest
    public void checkClinictName() throws Exception {
        text = mActivity.findViewById(R.id.clinicName);
        text.setText("clinicName1");
        String clinicName = text.getText().toString();
        assertNotEquals("clinicName", clinicName);
    }

    @Test
    @UiThreadTest
    public void checkClinicAddress() throws Exception {
        text = mActivity.findViewById(R.id.clinicAddress);
        text.setText("clinicAddress1");
        String clinicAddress = text.getText().toString();
        assertNotEquals("clinicAddress", clinicAddress);
    }

    @Test
    @UiThreadTest
    public void checkClinicInsuranceType() throws Exception {
        text = mActivity.findViewById(R.id.insuranceType);
        text.setText("insuranceType1");
        String insuranceType = text.getText().toString();
        assertNotEquals("insuranceType", insuranceType);
    }

    @Test
    @UiThreadTest
    public void checkClinicAcceptedPayment() throws Exception {
        text = mActivity.findViewById(R.id.paymentMethod);
        text.setText("paymentMethod1");
        String paymentMethod = text.getText().toString();
        assertNotEquals("paymentMethod", paymentMethod);
    }

    @Test
    @UiThreadTest
    public void checkPhoneNumber() throws Exception {
        text = mActivity.findViewById(R.id.clinicPhoneNumber);
        text.setText("clinicPhoneNumber1");
        String clinicPhoneNumber = text.getText().toString();
        assertNotEquals("clinicPhoneNumber", clinicPhoneNumber);
    }
}
