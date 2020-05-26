  package com.sreeharsha37.retirementcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

  class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCenter.start(application, "0eac16ae-a9df-43ee-85cf-2e6c11f5a1e9", Analytics::class.java, Crashes::class.java);

        calculateButton.setOnClickListener{
            // Crashes.generateTestCrash();
            try {
            val interestRate = interestEditText.text.toString().toFloat()
            val currentAge = ageEditText.text.toString().toInt()
            val retirementAge = retirementEditText.text.toString().toInt()
            val monthly = monthlySavingsEditText.text.toString().toFloat()
            val current = currentEditText.text.toString().toFloat()

            val properties:HashMap<String, String> = HashMap<String, String>()
            properties.put("interest_rate", interestRate.toString())
            properties.put("current_age", currentAge.toString())
            properties.put("retirement_age", retirementAge.toString())
            properties.put("monthly_savings", monthly.toString())
            properties.put("current_savings", current.toString())

            if(interestRate <=0){
                Analytics.trackEvent("wrong_interest_rate", properties);
            }
            if(retirementAge <= currentAge){
                Analytics.trackEvent("wrong_age", properties);
            }
        } catch (ex: Exception){
                Analytics.trackEvent(ex.message);
            }
        }

    }
}
