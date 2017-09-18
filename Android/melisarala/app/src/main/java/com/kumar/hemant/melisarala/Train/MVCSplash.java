package com.kumar.hemant.melisarala.Train;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.kumar.hemant.melisarala.R;

public class MVCSplash extends Activity
{
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_splash);
        Thread timer = new Thread()
        {
            public void run() { try { sleep(1000);
        }
        catch (InterruptedException e) { e.printStackTrace();
        }
        finally { Intent intent = new Intent("com.hemant.kumar.fms.MVCView");
            startActivity(intent);
        }
    }
    };
    timer.start();
    }
}
