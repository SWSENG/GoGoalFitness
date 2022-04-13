package com.example.gogoalfitness.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.gogoalfitness.databinding.ActivitySignup2Binding
import com.example.gogoalfitness.statistic.WorkoutHistory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import java.time.LocalDate
import java.time.LocalDateTime


class SignupActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivitySignup2Binding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignup2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        //if no exist in database auto create for us
        database = FirebaseDatabase.getInstance().getReference("WorkoutHistory")
            //.child("Year")






        binding.btnSignUpSignUp.setOnClickListener(){
            var email  = binding.editTextEmailAddressSignUp.text.toString()
            var password = binding.editTextPasswordSignUp.text.toString()

            //insert user info into realtime database
            //username,gender,birthdate,height,weight
            var username = binding.editTextUsername.text.toString()
            val gender = intent.getStringExtra("Gender").toString()
            val birthdate = intent.getStringExtra("Birthdate").toString()
            val height = intent.getStringExtra("Height").toString()
            val weight = intent.getStringExtra("Weight").toString()
            var workName = "chest"
            var min = "10"
            var cal = "100"
            var workoutT = "112233"

            var time = LocalDateTime.now().withNano(0).toString()
            val dayTimetest = LocalDate.now().toString()
            var workoutInfo = WorkoutHistory(workName, min, cal, dayTimetest)
            var user: WorkoutHistory
            //Log.d("time",time)
            val databaseRef = database.orderByChild("workoutTime").equalTo(dayTimetest)


            //var newUser = UserInfo(username,gender,birthdate,height,weight)
            //registerNewUser(email, password)
            //addNewUserInfo(newUser)
            //database.child(time).setValue(workoutInfo)
            database.addValueEventListener( object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // Get Post object and use the values to update the UI
                    for(workoutSnapshot in dataSnapshot.children)
                    {
                        //Log.d("Database",workoutSnapshot.getValue().toString()
                        //Log.d("Database",workoutSnapshot.getValue().toString())
                        //Log.d("Database",workoutSnapshot.child("workoutName").value.toString())
                        if(workoutSnapshot.child("workoutTime").value.toString() == dayTimetest)
                        {
                            Log.d("Database",workoutSnapshot.getValue().toString())
                        }
                    }

                }

                override fun onCancelled(databaseError: DatabaseError) {
                    // Getting Post failed, log a message
                    Log.d("error", databaseError.toString())
                }
            })

        }

    }

    private fun registerNewUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password)
            .addOnSuccessListener {

                Toast.makeText(baseContext, "New user added",
                    Toast.LENGTH_LONG).show();

                val intent = Intent(this,AccountCreatedActivity::class.java)
                //startActivity(intent)
            }
            .addOnFailureListener { ex->

                Toast.makeText(baseContext, ex.message.toString(),
                    Toast.LENGTH_LONG).show();
            }

    }

    private fun addNewUserInfo(newUser: UserInfo) {

        Toast.makeText(baseContext, "adding new",
            Toast.LENGTH_LONG).show();

        database.child("userTable")
            .child(newUser.username).setValue(newUser)
            .addOnSuccessListener {
                Toast.makeText(baseContext, "User info added",
                    Toast.LENGTH_LONG).show();

            }
            .addOnFailureListener { ex->
                Toast.makeText(baseContext, ex.message.toString(),
                    Toast.LENGTH_LONG).show();
            }

    }

}