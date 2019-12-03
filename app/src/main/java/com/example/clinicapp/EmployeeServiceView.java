package com.example.clinicapp;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.os.Bundle;
        import android.view.ContextMenu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.TextView;

        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

        import org.w3c.dom.Text;

        import java.util.ArrayList;
        import java.util.List;

public class EmployeeServiceView extends AppCompatActivity {
    TextView addServiceTxt;
    DatabaseReference databaseReference, dbr, database;
    List<Service> services;
    ListView adminListView, employeeListView;
    List<Service> employeeservices;
    String currentClinicID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_service_view);
        addServiceTxt = (TextView) findViewById(R.id.addServiceTxt);
        adminListView = (ListView) findViewById(R.id.adminListView);
        employeeListView = (ListView) findViewById(R.id.employeeListView);

        services = new ArrayList<>();
        employeeservices = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("Service");
        dbr = FirebaseDatabase.getInstance().getReference("Clinic");
        //String key = dbr.push().getKey();

        //database = FirebaseDatabase.getInstance().getReference("Clinic").child(currentClinicID);
        registerForContextMenu((adminListView));
        registerForContextMenu((employeeListView));

    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Service service = postSnapshot.getValue(Service.class);
                    services.add(service);
                }
                ServiceList servicesAdapter = new ServiceList(EmployeeServiceView.this, services);
                adminListView.setAdapter(servicesAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //get clinicid
        final String uid;
        FirebaseUser user;
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        database = FirebaseDatabase.getInstance().getReference("User").child("Employee").child(uid);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final String clinicid = dataSnapshot.child("clinicID").getValue(String.class);
                currentClinicID = clinicid;
                System.out.println(currentClinicID);
                database = FirebaseDatabase.getInstance().getReference("Clinic").child(currentClinicID).child("Service");
                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        employeeservices.clear();
                        for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                            Service service = postSnapshot.getValue(Service.class);
                            employeeservices.add(service);
                        }
                        ServiceList servicesAdapter = new ServiceList(EmployeeServiceView.this, employeeservices);
                        employeeListView.setAdapter(servicesAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.adminListView) { //For first listview
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.adminmenu, menu);

        }
        if (v.getId()==R.id.employeeListView) { //For second listview
            MenuInflater inflater = getMenuInflater();
            getMenuInflater().inflate(R.menu.employeemenu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position; //Use this for getting the list item value
        View view = info.targetView;

        switch(item.getItemId()) {
            case R.id.addServ:
                addEmployeeService(index);
                return true;

            case R.id.DeleteServ:
                deleteEmployeeService(index);
                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }
    private void addEmployeeService(int pos) {
        Service service= services.get(pos);
        String id = service.getId();
        database.child(id).setValue(service);
    }
    private void deleteEmployeeService(int pos) {
        Service service= employeeservices.get(pos);
        String id = service.getId();
        database.child(id).removeValue();
    }

}
