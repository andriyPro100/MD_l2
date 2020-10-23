package ua.kpi.comsys.myaplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.BreakIterator;
import java.util.Date;

import static android.os.SystemClock.sleep;

public class MainActivity extends AppCompatActivity {

    private static TextView errorTextView;
    private static TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        errorTextView = (TextView) findViewById(R.id.errorText);
        statusText = (TextView) findViewById(R.id.statusText);
        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TimeAP timeAP = new TimeAP();

                statusMessage("Екземпляр класу: 1....");
                TimeAP time1 = new TimeAP();
                statusText.append("ОК\n");
                statusText.append(time1.getTime()+"\n");
                statusText.append("Екземпляр класу: 2....");
                TimeAP time2 = new TimeAP(14, 45, 12);
                if (time2.checkData()){
                    statusText.append("ОК\n");
                }else return;

                statusMessage(time2.getTime()+"\n");
                statusMessage("Екземпляр класу: 3....");
                TimeAP time3 = new TimeAP(new Date(1212121212121L));
                if (time3.checkData()){
                    statusText.append("ОК\n");
                }else return;
                statusMessage(time3.getTime()+"\n");

                timeAP = time1.sumTime(time2);
                statusMessage("sum t1+t2 = " + timeAP.getTime() + "\n");
                timeAP = time3.sumTime(time2);
                statusMessage("sum t3+t2 = " + timeAP.getTime()+"\n");
                timeAP = time2.sumTime(time2);
                statusMessage("sum t2+t2 = " + timeAP.getTime()+"\n");
                timeAP = time3.subTime(time1);
                statusMessage("sub t3-t1 = " + timeAP.getTime()+"\n");


                timeAP = timeAP.sumTime(time1, time2);
                statusMessage("sum t1+t2 = " + timeAP.getTime() + "\n");
                timeAP = timeAP.subTime(time3, time1);
                statusMessage("sub t3-t1 = " + timeAP.getTime()+"\n");
                timeAP = timeAP.subTime(time1, time3);
                statusMessage("sub t1-t3 = " + timeAP.getTime()+"\n");
            }
        });
    }

    public static void errorMessage(String s) {
       errorTextView.setText(s);
    }

    public static void statusMessage(String s) {
        statusText.append(s);
    }
}